package com.example.chien.todoapp.Repositorys;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.widget.MultiAutoCompleteTextView;

import com.example.chien.todoapp.DBLocal.Dao.TodoDao;
import com.example.chien.todoapp.DBLocal.Models.Todo;
import com.example.chien.todoapp.DBLocal.TodoDatabase;
import com.example.chien.todoapp.DataResponse.GetAllResponse;
import com.example.chien.todoapp.DataResponse.TodoResponse;
import com.example.chien.todoapp.WebService.WebService;

import java.util.ArrayList;
import java.util.List;


import io.reactivex.schedulers.Schedulers;

public class TodoRepository {

    WebService service;
    LiveData<List<Todo>> listTodo;
    TodoDao todoDao;

    public TodoRepository(WebService service, Application application)
    {
        TodoDatabase database = TodoDatabase.getInstance(application);
        todoDao = database.todoDao();
        listTodo = todoDao.getAll();

    }


    public LiveData<List<Todo>> getAllToDo()
    {
        return listTodo;
    }

    public void insert (Todo todo) {
        new insertAsyncTask(todoDao).execute(todo);
    }

    private static class insertAsyncTask extends AsyncTask<Todo, Void, Void> {

        private TodoDao mAsyncTaskDao;

        insertAsyncTask(TodoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Todo... params) {
            mAsyncTaskDao.add(params[0]);
            return null;
        }
    }

    private Todo MapToModel(TodoResponse response)
    {
        Todo todo= new Todo();
        todo.setId(response.getId());
        todo.setOwner(response.getOwner());
        todo.setTitle(response.getTitle());
        return todo;
    }






}
