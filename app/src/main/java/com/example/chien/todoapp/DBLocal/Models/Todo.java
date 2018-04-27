package com.example.chien.todoapp.DBLocal.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.security.acl.Owner;
import java.util.Calendar;
import java.util.Date;

@Entity(tableName = "todo")
@TypeConverters(DateConverter.class)
public class Todo {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String Id;

    @ColumnInfo(name = "title")
    private String Title;

    @ColumnInfo(name = "createdDate")
    private Date CreatedDate;

    @ColumnInfo(name = "updatedDate")
    private Date UpdatedDate;

    @ColumnInfo(name = "owner")
    private String Owner;

    @ColumnInfo(name = "completed")
    private Boolean Completed;



    @Ignore
    public Todo(@NonNull String id, String title, String owner) {
        Id = id;
        Title = title;
        Owner = owner;
        Completed = false;
        CreatedDate = new Date();
    }

    public Todo()
    {

    }



    @NonNull
    public String getId() {
        return Id;

    }

    public void setId(@NonNull String id) {
        Id = id;
    }

    public Date getUpdatedDate() {
        return UpdatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        UpdatedDate = updatedDate;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        this.Owner = owner;
    }

    public Boolean getCompleted() {
        return Completed;
    }

    public void setCompleted(Boolean completed) {
        this.Completed = completed;
    }

    public String getTitle() {
        return Title;

    }

    public void setTitle(String title) {
        Title = title;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }


}
