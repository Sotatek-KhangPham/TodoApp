package com.example.chien.todoapp.WebService;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static Retrofit retrofit = null;
    public static Boolean isUserLoggedIn = false;


    public static  OkHttpClient getHeader(final String authorizationValue ) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        Request request = original.newBuilder()
                                .header("Content-Type", "application/x-www-form-urlencoded")
                                .header("Authorization", authorizationValue)
                                .method(original.method(), original.body())
                                .build();

                            return chain.proceed(request);
                    }
                })
                .build();
        return  httpClient;

    }
    public static void createNewRetorfit(String token)
    {
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://uetcc-todo-app.herokuapp.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getHeader(token))
                .build();
    }

    public static WebService getClient(String token) {
        Gson gson = new GsonBuilder().setLenient().create();
            if(retrofit == null)
            {
                retrofit = new Retrofit.Builder()
                        .baseUrl("https://uetcc-todo-app.herokuapp.com/")
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(getHeader(token))
                        .build();
            }



        WebService api = retrofit.create(WebService.class);
        return api;
    }
}
