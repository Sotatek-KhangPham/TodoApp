package com.example.chien.todoapp.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.chien.todoapp.DBLocal.Models.Todo;
import com.example.chien.todoapp.R;
import com.example.chien.todoapp.ViewData.TodoViewModel;

public class NewTodoActivity extends AppCompatActivity {

    private Button btnAddTodo;
    private Button btnCancel;
    private EditText edtTitle;
    public final  static String KEY = "TodoTitle";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_todo);
        configLayout();

        Intent intent = new Intent(NewTodoActivity.this, MainActivity.class);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setResult(RESULT_CANCELED);
                finish();
            }
        });

        btnAddTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra(KEY , edtTitle.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private void configLayout()
    {
        btnAddTodo =(Button) findViewById(R.id.btn_add_new_todo);
        btnCancel =(Button) findViewById(R.id.btn_cancel_new_todo);
        edtTitle =(EditText) findViewById(R.id.edt_newtodo_title);
    }
}
