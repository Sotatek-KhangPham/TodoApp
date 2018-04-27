package com.example.chien.todoapp.WebService;



import com.example.chien.todoapp.DataResponse.CreateTodoResponse;
import com.example.chien.todoapp.DataResponse.DeleteResponse;
import com.example.chien.todoapp.DataResponse.GetAllResponse;
import com.example.chien.todoapp.DataResponse.LoginResponse;
import com.example.chien.todoapp.DataResponse.SignUpResponse;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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



    @GET("/todos")
    Observable<GetAllResponse> getAllTodo();


    @FormUrlEncoded
    @POST("/todos")
    Observable<CreateTodoResponse> createTodo(@Field("title") String title);

    @FormUrlEncoded
    @PUT("/todos/{id}")
    Observable<CreateTodoResponse> updateTodo(@Path("id") String todoId, @Field("title") String title);

    @DELETE("/todos/{id}")
    Observable<DeleteResponse> delete(@Path("id") String todoId);
}
