package com.example.chien.todoapp.DataResponse;

import com.google.gson.annotations.Expose;
 import com.google.gson.annotations.SerializedName;

public class SignInResponse {

    @SerializedName("accessToken")
    @Expose
    private String accessToken;
    @SerializedName("user")
    @Expose
    private UserResponse user;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

}