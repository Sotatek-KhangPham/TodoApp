package com.example.chien.todoapp.WebService;



import com.example.chien.todoapp.DBLocal.Models.Todo;
import com.example.chien.todoapp.DBLocal.Models.User;
import com.example.chien.todoapp.DataResponse.LoginResponse;
import com.example.chien.todoapp.DataResponse.SignInResponse;
import com.example.chien.todoapp.DataResponse.SignUpResponse;
import com.example.chien.todoapp.DataResponse.TodoResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebService {

    @FormUrlEncoded
    @POST("/register")
    Observable<SignUpResponse> registration(@Field("name") String name,
                                            @Field("email") String email,
                                            @Field("password") String password);


    // In registration method @Field used to set the keys and String data type is representing its a string type value and callback is used to get the response from api and it will set it in our POJO class

    @FormUrlEncoded
    @POST("/login")
    Observable<LoginResponse> login(@Field("email") String email,
                                    @Field("password") String password);

    @GET("/todo")
    Observable<List<TodoResponse>> getAllTodo(@Header("Authorization") String token);
}
