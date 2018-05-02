package com.example.chien.todoapp.Repositorys;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;


import com.example.chien.todoapp.Common.Common;
import com.example.chien.todoapp.DBLocal.Dao.TodoDao;
import com.example.chien.todoapp.DBLocal.Models.Todo;
import com.example.chien.todoapp.DBLocal.TodoDatabase;
import com.example.chien.todoapp.DataResponse.CreateTodoResponse;
import com.example.chien.todoapp.DataResponse.DeleteResponse;
import com.example.chien.todoapp.DataResponse.GetAllResponse;
import com.example.chien.todoapp.DataResponse.TodoResponse;
import com.example.chien.todoapp.View.MainActivity;
import com.example.chien.todoapp.View.NewTodoActivity;
import com.example.chien.todoapp.WebService.Api;
import com.example.chien.todoapp.WebService.WebService;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class TodoRepository {

    WebService service;
    Observable<List<Todo>> listTodo;
    TodoDao todoDao;

    public TodoRepository(Application application)
    {
        TodoDatabase database = TodoDatabase.getInstance(application);
        todoDao = database.todoDao();

        this.service = Api.getClient(Common.token);

        listTodo = todoDao.getAll().toObservable();

    }



    public void getAllToDo() {
        listTodo = service.getAllTodo()
                .flatMap(response -> {
                    return Observable.defer(() -> Observable.just(getListTodo(response)));

                });

    }

    public void  getAutoUpdateFromDb()
    {
        listTodo = todoDao.getAll().toObservable();
    }

    public Observable<List<Todo>> getTodoList()
    {
        return listTodo;
    }

    public List<Todo> getListTodo(GetAllResponse response)
    {
        List<Todo> list = new ArrayList<>();

        for (TodoResponse todo:
             response.getData()) {
            list.add(mapToModel(todo));
        }
        return list;
    }

    public void insert (Todo todo) {
        new InsertAsyncTask(todoDao).execute(todo);
    }

    public void update(List<Todo> list)
    {
        new DeleteAsyncTask(todoDao);
        for (Todo todo:
             list) {
             insert(todo);
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Void, Void, Void> {

        private TodoDao mAsyncTaskDao;

        DeleteAsyncTask(TodoDao dao) {
            mAsyncTaskDao = dao;
        }



        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class DeleteSingleAsyncTask extends AsyncTask<String, Void, Void> {

        private TodoDao mAsyncTaskDao;

        DeleteSingleAsyncTask(TodoDao dao) {
            mAsyncTaskDao = dao;
        }



        @Override
        protected Void doInBackground(String... params) {
            Todo todo = mAsyncTaskDao.getByID(params[0]);
            mAsyncTaskDao.delete(todo);
            return null;
        }
    }
    private static class UpdateAsyncTask extends AsyncTask<Todo, Void, Void> {

        private TodoDao mAsyncTaskDao;

        UpdateAsyncTask(TodoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Todo... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class InsertAsyncTask extends AsyncTask<Todo, Void, Void> {

        private TodoDao mAsyncTaskDao;

        InsertAsyncTask(TodoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Todo... params) {
            mAsyncTaskDao.add(params[0]);
            return null;
        }
    }

    private Todo mapToModel(TodoResponse response)
    {
        Todo todo= new Todo();
        todo.setId(response.getId());
        todo.setOwner(response.getOwner());
        todo.setTitle(response.getTitle());
        todo.setCreatedDate(formatDate(response.getCreated()));
        todo.setCompleted(response.getCompleted());
        return todo;
    }
    public Date formatDate(String str)
    {
        String date = str.substring(0,10);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try{
        return format.parse(date);
    }catch(Exception ex)
    {
        return new Date();
    }
}

    public void updateTodo(String id, String title)
    {
        service.updateTodo(id, title)
                .map(response ->{
                    return mapToModel(response.getData());
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response ->{
                    new UpdateAsyncTask(todoDao).execute(response);
                });
    }

    public void createTodoServer(Context context, Todo todo)
    {
        service.createTodo(todo.getTitle())
                .map(response ->{
                    return mapToModel(response.getData());
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response ->{
                    insert(response);
                },throwable -> {
                    Toast.makeText(context,"Thêm mới không thành công",Toast.LENGTH_SHORT).show();
                });
    }


    public void delete(Context context , String id)
    {
        service.delete(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response ->{
                    if(response.getSuccess())
                    {
                        Toast.makeText(context,"Đã xóa thành công.",Toast.LENGTH_SHORT).show();
                        new DeleteSingleAsyncTask(todoDao).execute(id);
                    }

                },throwable -> {
                    Toast.makeText(context,"Xóa không thành công",Toast.LENGTH_SHORT).show();
                });
    }



}
