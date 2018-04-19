package com.example.chien.todoapp.DBLocal.Models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class User{

    @NonNull
    @PrimaryKey
    @ColumnInfo(name="id")
    private String Id;

    @ColumnInfo(name = "email")
    private String Email;

    @ColumnInfo(name = "name")
    private String Name;

    @ColumnInfo(name = "password")
    private String Password;

    @Ignore
    public User(String name, String email, String password)
    {
        this.Name = name;
        this.Email = email;
        this.Password = password;
    }

    public User()
    {

    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    @NonNull
    public String getId() {
        return Id;
    }

    public void setId(@NonNull String id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}
