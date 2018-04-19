package com.example.chien.todoapp.DBLocal;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.chien.todoapp.DBLocal.Dao.UserDao;
import com.example.chien.todoapp.DBLocal.Models.User;

@Database(entities = {User.class}, version = 1)
public abstract class TodoDatabase extends RoomDatabase {

    private static volatile TodoDatabase INSTANCE;

    public abstract UserDao userDao();

    public static TodoDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TodoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodoDatabase.class, "Todo.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
