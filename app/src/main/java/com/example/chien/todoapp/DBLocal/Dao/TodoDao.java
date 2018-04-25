package com.example.chien.todoapp.DBLocal.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import com.example.chien.todoapp.DBLocal.Models.DateConverter;
import com.example.chien.todoapp.DBLocal.Models.Todo;
import com.example.chien.todoapp.DBLocal.Models.User;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters(DateConverter.class)
public interface TodoDao {

    @Insert(onConflict = REPLACE)
    void add(Todo todo);

    @Query("SELECT * FROM todo WHERE Id = :id")
    LiveData<Todo> load(String id);

    @Update
    void update(Todo todo);

    @Delete
    void delete(Todo todo);

    @Query("SELECT * FROM todo")
    LiveData<List<Todo>> getAll();

    @Query("SELECT COUNT(*) FROM todo")
    Integer getRowNumber();

    @Query("DELETE FROM todo")
    void deleteAll();
}

