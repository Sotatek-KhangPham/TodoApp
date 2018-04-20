package com.example.chien.todoapp.DataResponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<TodoResponse> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<TodoResponse> getData() {
        return data;
    }

    public void setData(List<TodoResponse> data) {
        this.data = data;
    }

}