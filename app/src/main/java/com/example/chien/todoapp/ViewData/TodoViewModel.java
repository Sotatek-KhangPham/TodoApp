package com.example.chien.todoapp.ViewData;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.chien.todoapp.DBLocal.Models.Todo;
import com.example.chien.todoapp.Repositorys.TodoRepository;
import com.example.chien.todoapp.WebService.Api;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {

    private LiveData<List<Todo>> listTodo;
    private TodoRepository todoRepository;
    public TodoViewModel(Application application) {
        super(application);
        todoRepository = new TodoRepository(Api.getClient(),application);
        listTodo = todoRepository.getAllToDo();
    }
    public LiveData<List<Todo>> getAllTodos()
    {
        return  listTodo;
    }

    public void inserst(Todo todo)
    {
        todoRepository.insert(todo);
    }

}
