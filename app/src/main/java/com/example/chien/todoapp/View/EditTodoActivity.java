package com.example.chien.todoapp.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.chien.todoapp.R;

public class EditTodoActivity extends AppCompatActivity {

    private EditText edtTitile;
    private Button btnSave;
    private Button btnDelete;
    public   final static String TITLE_KEY = "title edit text";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);
        configLayout();
        Intent intent = getIntent();

        String title = intent.getStringExtra("todo_title");
        edtTitile.setText(title);
        String id = intent.getStringExtra("todo_id");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra(TITLE_KEY,edtTitile.getText().toString());
                setResult(RESULT_OK,intent1);
                finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(15);
                finish();
            }
        });

    }

    private void configLayout()
    {
        btnSave = (Button) findViewById(R.id.btn_edit_save);
        btnDelete = (Button) findViewById(R.id.btn_edit_delete);
        edtTitile = (EditText) findViewById(R.id.edt_edit_title);

    }
}
