package com.example.chien.todoapp.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.chien.todoapp.Common.Common;
import com.example.chien.todoapp.DataResponse.LoginResponse;
import com.example.chien.todoapp.Handler;
import com.example.chien.todoapp.R;
import com.example.chien.todoapp.WebService.Api;
import com.example.chien.todoapp.WebService.WebService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SplashScreenActivity extends AppCompatActivity {

    Common common = Common.getInstance();
    boolean loginStatus = false;
    String email ;
    String password;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        SharedPreferences sharedPreferences = SplashScreenActivity.this.getSharedPreferences("LoginInfo",MODE_PRIVATE);

        token = sharedPreferences.getString("token", "");
        email =sharedPreferences.getString("email", "");
        password = sharedPreferences.getString("password", "");
        Common common = Common.getInstance();
        String userId = sharedPreferences.getString("userId","");

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if( sharedPreferences == null || email.equals(""))
                {
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Api.getClient(token);
                    common.id = userId;
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },2000);

    }


}
