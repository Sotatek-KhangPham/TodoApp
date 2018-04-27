package com.example.chien.todoapp.ViewData;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.chien.todoapp.DBLocal.Models.Todo;
import com.example.chien.todoapp.Repositorys.TodoRepository;
import com.example.chien.todoapp.WebService.Api;
import com.example.chien.todoapp.WebService.WebService;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TodoViewModel extends AndroidViewModel {

    private MutableLiveData<List<Todo>> listTodo = new MutableLiveData<>();
    private static TodoRepository todoRepository;

    public TodoViewModel(Application application) {
        super(application);
        todoRepository = new TodoRepository(application);

    }
    public LiveData<List<Todo>> getData()
    {
        return  listTodo;
    }

    public void getAllTodo()
    {
        todoRepository.getAllToDo();
        todoRepository.getTodoList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                   listTodo.setValue(list);

                },throwable -> {},()->{

                    todoRepository.update(listTodo.getValue());
                });

    }

    public void getAllTodoDB()
    {
        todoRepository.getAutoUpdateFromDb();
        todoRepository.getTodoList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Todo>>() {
                    @Override
                    public void accept(List<Todo> todos) throws Exception {
                        listTodo.setValue(todos);
                    }
                });
    }
    public void inserst(Todo todo)
    {
        todoRepository.createTodoServer(getApplication().getApplicationContext(),todo);
    }


    public void update(String id, String title)
    {
        todoRepository.updateTodo(id, title);
    }

    public void delete(String id)
    {
        todoRepository.delete(getApplication().getApplicationContext(),id);
    }


}
