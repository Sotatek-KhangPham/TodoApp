package com.example.chien.todoapp;

import com.example.chien.todoapp.DBLocal.Models.Todo;

public interface  Handler {
    void onItemClick(Todo todo);
    void onCheckChange(Boolean status);
}
