package com.example.chien.todoapp.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.chien.todoapp.Common.Common;
import com.example.chien.todoapp.DBLocal.Dao.TodoDao;
import com.example.chien.todoapp.DBLocal.Models.Todo;
import com.example.chien.todoapp.DBLocal.TodoDatabase;
import com.example.chien.todoapp.Handler;
import com.example.chien.todoapp.R;
import com.example.chien.todoapp.Repositorys.TodoRepository;
import com.example.chien.todoapp.ToDoAdapter;
import com.example.chien.todoapp.ViewData.LoginViewModel;
import com.example.chien.todoapp.ViewData.TodoViewModel;
import com.example.chien.todoapp.WebService.Api;

import java.util.Date;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableContainer;

public class MainActivity extends AppCompatActivity implements Handler{

    private Button btnAddTodo;
    private Button btnDelete;
    private RecyclerView recyclerView;

    private List<Todo> listTodo;
    private ToDoAdapter adapter;
    private TodoViewModel todoVM;
    private String todoSelectedId;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configLayout();
        configRecyclerView();
        getViewModel();

        btnAddTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewTodoActivity.class);
                startActivityForResult(intent, 200);
            }
        });
    }


    private void configLayout()
    {
        recyclerView =(RecyclerView)findViewById(R.id.recyclerView);
        btnAddTodo =(Button)findViewById(R.id.addTodo);
        btnDelete =(Button)findViewById(R.id.btnDelete);

    }

    private void configRecyclerView()
    {
        recyclerView.setHasFixedSize(true);
        adapter = new ToDoAdapter(MainActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter.setHandler(MainActivity.this);

    }



    private void getViewModel()
    {

        todoVM = ViewModelProviders.of(MainActivity.this).get(TodoViewModel.class);

        todoVM.getData().observe(MainActivity.this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(@Nullable List<Todo> list) {
                listTodo = list;
                adapter.setList(list);
            }
        });

        todoVM.getAllTodoDB();
    }

    @Override
    public void onItemClick(Todo todo) {
        todoSelectedId = todo.getId();
        Intent intent = new Intent(this , EditTodoActivity.class);
        intent.putExtra("todo_id", todo.getId());
        intent.putExtra("todo_title", todo.getTitle());
        startActivityForResult(intent, 201);
    }

    @Override
    public void onCheckChange(Boolean status) {

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 200 && resultCode == RESULT_OK) {
            String title = data.getStringExtra(NewTodoActivity.KEY);
            Todo todo = new Todo();
            todo.setTitle(title);
            todoVM.inserst(todo);
            todoVM.getAllTodoDB();
        }
        if(requestCode == 201)
        {
            if(resultCode == RESULT_OK)
            {
                String title = data.getStringExtra(EditTodoActivity.TITLE_KEY);

                todoVM.update(todoSelectedId, title);
                todoVM.getAllTodoDB();

            }
           if(resultCode == 15)
           {
               todoVM.delete(todoSelectedId);
               todoVM.getAllTodoDB();
           }

        }
    }
}
