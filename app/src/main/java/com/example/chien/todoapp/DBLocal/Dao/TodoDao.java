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

import java.util.List;

import io.reactivex.Flowable;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
@TypeConverters(DateConverter.class)
public interface TodoDao {

    @Insert(onConflict = REPLACE)
    void add(Todo todo);

    @Query("SELECT * FROM todo WHERE Id = :id")
    Flowable<Todo> load(String id);

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

    @Query("SELECT * FROM todo WHERE Id = :id")
    Todo getByID(String id);

    @Query("SELECT * FROM todo where owner= :owner")
    LiveData<List<Todo>> getByOwner(String owner);
}

