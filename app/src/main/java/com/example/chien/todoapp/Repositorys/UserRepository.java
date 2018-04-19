package com.example.chien.todoapp.Repositorys;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.chien.todoapp.DBLocal.Dao.UserDao;
import com.example.chien.todoapp.DBLocal.Models.User;
import com.example.chien.todoapp.DataResponse.SignInResponse;
import com.example.chien.todoapp.DataResponse.SignUpResponse;
import com.example.chien.todoapp.DataResponse.UserResponse;
import com.example.chien.todoapp.WebService.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    WebService service;
    UserDao userDao;

    public UserRepository(WebService service, UserDao dao) {
        this.service = service;
        this.userDao = dao;

    }
}
