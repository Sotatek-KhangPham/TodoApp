package com.example.chien.todoapp.DataResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataDeleteResponse {

    @SerializedName("n")
    @Expose
    private Integer n;
    @SerializedName("opTime")
    @Expose
    private OpTime opTime;
    @SerializedName("electionId")
    @Expose
    private String electionId;
    @SerializedName("ok")
    @Expose
    private Integer ok;

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public OpTime getOpTime() {
        return opTime;
    }

    public void setOpTime(OpTime opTime) {
        this.opTime = opTime;
    }

    public String getElectionId() {
        return electionId;
    }

    public void setElectionId(String electionId) {
        this.electionId = electionId;
    }

    public Integer getOk() {
        return ok;
    }

    public void setOk(Integer ok) {
        this.ok = ok;
    }

}