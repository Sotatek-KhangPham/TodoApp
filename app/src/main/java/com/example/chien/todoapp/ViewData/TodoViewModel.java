package com.example.chien.todoapp.ViewData;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.chien.todoapp.DBLocal.Models.Todo;
import com.example.chien.todoapp.Repositorys.TodoRepository;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {

    private MutableLiveData<List<Todo>> listTodo;
    private TodoRepository todoRepository;
    public TodoViewModel(@NonNull Application application) {
        super(application);
    }
    public void setTodoRepositor(TodoRepository todRepository)
    {
        this.todoRepository = todRepository;
    }
    public void initData()
    {
       listTodo = todoRepository.getAllTodo();
    }

    public LiveData<List<Todo>> getData()
    {
        if(listTodo == null)
        {
            listTodo =  new MutableLiveData<>();
            initData();
        }
        return  listTodo;
    }


}
