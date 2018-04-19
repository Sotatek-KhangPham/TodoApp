package com.example.chien.todoapp.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.chien.todoapp.R;

public class Login extends AppCompatActivity {

    private AutoCompleteTextView txtEmail;
    private EditText edtPassword;
    Button btnSingIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    private void configLayout()
    {
        txtEmail=(AutoCompleteTextView)findViewById(R.id.email);
        edtPassword=(EditText)findViewById(R.id.password);
        btnSingIn =(Button)findViewById(R.id.email_sign_in_button);
    }
}
