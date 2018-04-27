package com.example.chien.todoapp.DataResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpTime {

    @SerializedName("ts")
    @Expose
    private String ts;
    @SerializedName("t")
    @Expose
    private Integer t;

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public Integer getT() {
        return t;
    }

    public void setT(Integer t) {
        this.t = t;
    }

}