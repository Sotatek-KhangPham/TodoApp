package com.example.chien.todoapp.DBLocal;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.chien.todoapp.DBLocal.Dao.TodoDao;
import com.example.chien.todoapp.DBLocal.Dao.UserDao;
import com.example.chien.todoapp.DBLocal.Models.Todo;
import com.example.chien.todoapp.DBLocal.Models.User;

@Database(entities = {User.class, Todo.class}, version = 1)
public abstract class TodoDatabase extends RoomDatabase {

    private static volatile TodoDatabase INSTANCE;

    public abstract UserDao userDao();

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
        private final UserDao mUserDao;

        PopulateDbAsync(TodoDatabase db) {
            mTodoDao = db.todoDao();
            mUserDao =  db.userDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            mTodoDao.deleteAll();
            mUserDao.deleteAll();

            mUserDao.add(new User("user1","Khang Pham","khang96nd@gmail.com","123456789"));

            mTodoDao.add(new Todo("Todo1","Công việc 1","user1"));
            mTodoDao.add(new Todo("Todo2","Công việc 2","user1"));
            mTodoDao.add(new Todo("Todo3","Công việc 3","user1"));
            mTodoDao.add(new Todo("Todo4","Công việc 4","user1"));
            return null;
        }
    }

}
