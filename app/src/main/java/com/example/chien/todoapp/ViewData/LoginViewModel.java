package com.example.chien.todoapp.ViewData;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.chien.todoapp.DataResponse.SignInResponse;

public class LoginViewModel extends ViewModel {
    private LiveData<SignInResponse> user;
    public LiveData<SignInResponse> getUser()
    {
        return  user;
    }

    public void setUser(SignInResponse user)
    {

    }
}
