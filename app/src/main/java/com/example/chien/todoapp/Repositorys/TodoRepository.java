package com.example.chien.todoapp.Repositorys;

import android.arch.lifecycle.LiveData;
import android.widget.Toast;

import com.example.chien.todoapp.DBLocal.Dao.TodoDao;
import com.example.chien.todoapp.DBLocal.Models.Todo;
import com.example.chien.todoapp.DataResponse.TodoResponse;
import com.example.chien.todoapp.View.ListTodoFragment;
import com.example.chien.todoapp.WebService.WebService;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TodoRepository {
    WebService service;
    TodoDao todoDao;
    static final  String Token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVhZDZlNzk2MTI5MzIxMDAxNGFlMTUxOCIsImlhdCI6MTUyNDIxMDE5MCwiZXhwIjoxNTI0ODE0OTkwfQ.ookwNhkIOvb6BgifNwRq5LWHipOZNOCqmNQG4b1ZSEA";

    public TodoRepository(WebService service, TodoDao todoDao)
    {
        this.service= service;
        this.todoDao = todoDao;
    }

    public LiveData<List<Todo>> getAllTodo()
    {
        service.getAllTodo(Token).subscribeOn(Schedulers.io())
           .flatMap(list-> Observable.defer(() -> Observable.fromArray(list)))

            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<TodoResponse>()
            {

            });
    }

    public LiveData<Todo> getTodo(String id)
    {
        return  todoDao.load(id);
    }
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

    private void success(List<TodoResponse> list)
    {

    }
    private void error(Throwable throwable)
    {
        //throw ssssssss
    }
    private void complete()
    {

    }
    private Todo mapToModel(TodoResponse response)
    {
        Todo todo= new Todo();
        todo.setId(response.getId());
        todo.setOwner(response.getOwner());
        todo.setTitle(response.getTitle());
        return todo;
    }


}
