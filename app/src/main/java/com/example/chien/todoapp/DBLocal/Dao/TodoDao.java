package com.example.chien.todoapp.DBLocal.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.chien.todoapp.DBLocal.Models.Todo;
import com.example.chien.todoapp.DBLocal.Models.User;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface TodoDao {

    @Insert(onConflict = REPLACE)
    void add(Todo todo);

    @Query("SELECT * FROM Todo WHERE Id = :id")
    LiveData<Todo> load(String id);

    @Update
    LiveData<Todo> update(Todo todo);

    @Delete
    LiveData<Todo> delete(Todo todo);
}

