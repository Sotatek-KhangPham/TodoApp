package com.example.chien.todoapp.DBLocal.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.chien.todoapp.DBLocal.Models.User;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {

    @Insert(onConflict = REPLACE)
    void add(User user);

    @Query("SELECT * FROM users WHERE id = :userId")
    LiveData<User> getUser(String userId);

    @Delete
    void deleteUser(User user);

    @Query("DELETE FROM todo")
    void deleteAll();
}
