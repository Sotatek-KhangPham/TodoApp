package com.example.chien.todoapp.DataResponse;



 import com.google.gson.annotations.Expose;
 import com.google.gson.annotations.SerializedName;

public class DeleteResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private DataDeleteResponse data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public DataDeleteResponse getData() {
        return data;
    }

    public void setData(DataDeleteResponse data) {
        this.data = data;
    }

}