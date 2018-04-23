package com.example.chien.todoapp.Repositorys;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
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

    public void insertTodo(Todo todo)
    {


    }
//    public MutableLiveData<List<Todo>> getAllTodo()
//    {
//        service.getAllTodo(token)
//                .subscribeOn(Schedulers.io())
//                .subscribe(this::success, this::error,this::complete);
//
//        return todoDao.getAll();
//    }
//
//    public MutableLiveData<List<Todo>> getAllTodo1()
//    {
//        service.getAllTodo(token)
//                .subscribeOn(Schedulers.io())
//                .subscribe(this::success1, this::error1,this::complete1);
//
//
//        return data;
//    }
//
//    public LiveData<Todo> getTodo(String id)
//    {
//        return  todoDao.load(id);
//    }
    public void delete(Todo todo)
    {
        todoDao.delete(todo);
    }
    public void add(Todo todo)
    {
         todoDao.add(todo);
    }
    public void update(Todo todo)
    {
        todoDao.update(todo);
    }

//    private void success(GetAllResponse response)
//    {
//        for (TodoResponse responseTodo: response.getData()) {
//            list.add(MapToModel(responseTodo));
//        }
//
//    }
//    private void error(Throwable throwable)
//    {
//        //throw ssssssss
//    }
//    private void complete()
//    {
//        if(todoDao.getRowNumber() == 0)
//        {
//            for (Todo todo:
//                    list) {
//                todoDao.add(todo);
//            }
//        }
//    }
    private Todo MapToModel(TodoResponse response)
    {
        Todo todo= new Todo();
        todo.setId(response.getId());
        todo.setOwner(response.getOwner());
        todo.setTitle(response.getTitle());
        return todo;
    }






}
