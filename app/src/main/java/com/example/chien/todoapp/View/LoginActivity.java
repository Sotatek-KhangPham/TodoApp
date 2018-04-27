package com.example.chien.todoapp.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chien.todoapp.Common.Common;
import com.example.chien.todoapp.DataResponse.LoginResponse;
import com.example.chien.todoapp.DataResponse.SignInResponse;
import com.example.chien.todoapp.R;
import com.example.chien.todoapp.ViewData.LoginViewModel;
import com.example.chien.todoapp.WebService.Api;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView txtEmail;
    private EditText edtPassword;
    Button btnSingIn;
    Button btnSignUp;
    Disposable disposable;
    LoginViewModel viewModel;
    boolean loginStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        configLayout();
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent, 200);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==200)
        {
            edtPassword.setText(data.getStringExtra("Password"));
            txtEmail.setText(data.getStringExtra("Email"));
        }
    }

    private void configLayout()
    {
        txtEmail=(AutoCompleteTextView)findViewById(R.id.email);
        edtPassword=(EditText)findViewById(R.id.password);
        btnSingIn =(Button)findViewById(R.id.email_sign_in_button);
        btnSignUp =(Button)findViewById(R.id.email_sign_up_button);
    }
    private void login()
    {

        disposable = Api.getClient().login(txtEmail.getText().toString(),edtPassword.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::success,this::error, this::complete);
    }
    private void success(LoginResponse loginResponse)
    {
        Common common = Common.getInstance();

        if(loginResponse.getSuccess())
        {
            loginStatus = true;
            Api.isUserLoggedIn = true;
            common.token = loginResponse.getData().getAccessToken();
            common.userName = loginResponse.getData().getUser().getName();
            Toast.makeText(this,"Đăng nhập thành công", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loginStatus = false;
            Toast.makeText(this,"Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();

        }

    }
    private void error(Throwable throwable)
    {
        Toast.makeText(this,throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }
    private void complete()
    {
        if(loginStatus)
        {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

    }
}
