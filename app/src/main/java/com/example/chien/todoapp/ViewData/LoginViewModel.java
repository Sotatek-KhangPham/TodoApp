package com.example.chien.todoapp.ViewData;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.chien.todoapp.DataResponse.SignInResponse;

public class LoginViewModel extends ViewModel {
    private String email;
    private String password;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
