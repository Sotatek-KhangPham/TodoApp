package com.example.chien.todoapp.DBLocal;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.chien.todoapp.DBLocal.Dao.TodoDao;
import com.example.chien.todoapp.DBLocal.Models.Todo;

@Database(entities = {Todo.class}, version = 2)
public abstract class TodoDatabase extends RoomDatabase {

    private static volatile TodoDatabase INSTANCE;



    public abstract TodoDao todoDao();

    public static TodoDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TodoDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodoDatabase.class, "todo_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();

                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final TodoDao mTodoDao;


        PopulateDbAsync(TodoDatabase db) {
            mTodoDao = db.todoDao();

        }

        @Override
        protected Void doInBackground(final Void... params) {

            return null;
        }
    }

}
