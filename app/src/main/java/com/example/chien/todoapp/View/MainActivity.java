package com.example.chien.todoapp.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configLayout();
        configRecyclerView();
        getViewModel();
    }


//    private void creatDatabase()
//    {
//        new AsyncTask<Void, Void, TodoDatabase>() {
//            @Override
//            protected TodoDatabase doInBackground(Void... voids) {
//                return TodoDatabase.getInstance(getApplicationContext());
//            }
//
//            @Override
//            protected void onPostExecute(TodoDatabase database) {
//                super.onPostExecute(database);
//
//
//                todoVM.setTodoRepositor(todoRepository);
//                todoVM.initData();
//            }
//        }.execute();
//
//
//    }

    private void configLayout()
    {
        recyclerView =(RecyclerView)findViewById(R.id.recyclerView);
        btnAddTodo =(Button)findViewById(R.id.addTodo);
        btnDelete =(Button)findViewById(R.id.btnDelete);

    }

    private void configRecyclerView()
    {
//        recyclerView.setHasFixedSize(true);
        adapter = new ToDoAdapter(MainActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter.setHandler(MainActivity.this);

    }



    private void getViewModel()
    {

        todoVM = ViewModelProviders.of(MainActivity.this).get(TodoViewModel.class);
        todoVM.getAllTodos().observe(MainActivity.this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(@Nullable List<Todo> list) {
                listTodo = list;
                adapter.setList(list);
            }
        });
    }

    @Override
    public void onItemClick(Todo todo) {

    }

    @Override
    public void onCheckChange(Boolean status) {

    }
}
