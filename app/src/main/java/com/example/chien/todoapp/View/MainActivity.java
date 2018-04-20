package com.example.chien.todoapp.View;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.chien.todoapp.DBLocal.Dao.TodoDao;
import com.example.chien.todoapp.DBLocal.TodoDatabase;
import com.example.chien.todoapp.R;
import com.example.chien.todoapp.Repositorys.TodoRepository;
import com.example.chien.todoapp.WebService.Api;

public class MainActivity extends AppCompatActivity implements ListTodoFragment.OnFragmentInteractionListener {

    private TodoDao todoDao;
    private TodoDatabase db;
    private TodoRepository todoRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListTodoFragment todoFragment= ListTodoFragment.newInstance();

        creatDatabase();
        inject();
        todoFragment.setTodoRepository(todoRepository);
        replaceFragment(todoFragment);

    }

    private void replaceFragment(Fragment fragment)
    {
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(fragment.getTag());
        transaction.commit();

    }

    @Override
    public void onButtonAddClick() {

    }

    private void creatDatabase()
    {
        db=TodoDatabase.getInstance(getApplicationContext());
        todoDao = db.todoDao();
    }

    private void inject()
    {

        todoRepository =new TodoRepository(Api.getClient(),todoDao);

    }
}
