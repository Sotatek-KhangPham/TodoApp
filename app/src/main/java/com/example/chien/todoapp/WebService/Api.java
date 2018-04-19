package com.example.chien.todoapp.WebService;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static Retrofit retrofit = null;
    public static WebService getClient() {

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://uetcc-todo-app.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        //Creating object for our interface
        WebService api = retrofit.create(WebService.class);
        return api; // return the APIInterface object
    }
}
