package com.example.chien.todoapp.DataResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreateTodoResponse {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private TodoResponse data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public TodoResponse getData() {
        return data;
    }

    public void setData(TodoResponse data) {
        this.data = data;
    }

}
