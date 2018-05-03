package com.example.chien.todoapp.View;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.chien.todoapp.Common.Common;
import com.example.chien.todoapp.DataResponse.LoginResponse;
import com.example.chien.todoapp.DataResponse.SignUpResponse;
import com.example.chien.todoapp.R;
import com.example.chien.todoapp.ViewData.LoginViewModel;
import com.example.chien.todoapp.WebService.Api;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText edtPassword;
    private EditText edtName;

    Button btnRegister;
    Disposable disposable;
    LoginViewModel viewModel;
    Boolean loginStatus = false;
    Common common = Common.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        configLayout();

         btnRegister.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(checkEmail(txtEmail) && checkPassword(edtPassword) && validate(edtName))
                 {
                     register();
                 }

             }
         });
    }

    private void configLayout()
    {
        txtEmail=(EditText)findViewById(R.id.email_register);
        edtPassword=(EditText)findViewById(R.id.password_register);
        btnRegister =(Button)findViewById(R.id.email_sign_in_button);
        edtName=(EditText)findViewById(R.id.name_register);

    }

    private void register()
    {


        String name = String.valueOf(edtName.getText());
        String password =String.valueOf(edtPassword.getText());
        String email= String.valueOf(txtEmail.getText());
        disposable= Api.getClient("").registration(name, email, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::success,this::error, this::complete);



    }
    private void error(Throwable error)
    {
        Toast.makeText(this,"Không thể đăng ký tài khoản", Toast.LENGTH_SHORT).show();
    }

    private void success(SignUpResponse signUpResponse)
    {

        if(signUpResponse.getSuccess())
        {

            viewModel= ViewModelProviders.of(RegisterActivity.this).get(LoginViewModel.class);
            viewModel.setEmail(signUpResponse.getData().getEmail());
            viewModel.setPassword(edtPassword.getText().toString());
        }
        else
            Toast.makeText(this,signUpResponse.getMessage(), Toast.LENGTH_SHORT).show();

    }
    private void complete()
    {
        Toast.makeText(this,"Đăng ký thành công.", Toast.LENGTH_SHORT).show();
        Intent intent =new Intent(RegisterActivity.this,LoginActivity.class);
//        intent.putExtra("Email",viewModel.getEmail());
//        intent.putExtra("Password",viewModel.getPassword());
//        setResult(200,intent);
//        disposable.dispose();
//         finish();
        login();

    }

    public  boolean checkEmail(EditText edtEmail)
    {
        String email = edtEmail.getText().toString().trim();
        if (!TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }
        txtEmail.setError("Email is not valid.");
        txtEmail.requestFocus();
        return false;

    }
    public  boolean checkPassword(EditText edtPassword)
    {
        String password=edtPassword.getText().toString().trim();
        if(password.length() < 8)
        {
            edtPassword.setError("Mật khẩu có ít nhất 8 ký tự");
            edtPassword.requestFocus();
            return  false;
        }
        return  true;
    }
    public boolean validate(EditText editText) {

        if (editText.getText().toString().trim().length() > 0) {
            return true;
        }
        editText.setError("Không được để trống");
        editText.requestFocus();
        return false;
    }

    private void login()
    {

        SharedPreferences sharedPreferences = this.getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        disposable = Api.getClient(token).login(txtEmail.getText().toString(),edtPassword.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::success1,this::error1, this::complete1);
    }
    private void success1(LoginResponse loginResponse){

        if(loginResponse.getSuccess())
        {
            loginStatus = true;
            Api.isUserLoggedIn = true;

            common.token = loginResponse.getData().getAccessToken();
            common.email = loginResponse.getData().getUser().getEmail();
            common.password = edtPassword.getText().toString();
            common.id = loginResponse.getData().getUser().getId();

        }
        else
        {
            loginStatus = false;
            Toast.makeText(this,"Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();

        }

    }
    private void error1(Throwable throwable)
    {
        Toast.makeText(this,throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }
    private void complete1()
    {
        if(loginStatus)
        {
            SharedPreferences sharedPreferences= this.getSharedPreferences("LoginInfo", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("email", common.email);
            editor.putString("password", common.password);
            editor.putString("token", common.token);
            editor.putString("userId", common.id);
            editor.apply();
            Api.createNewRetorfit(common.token);
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        }

    }


}
