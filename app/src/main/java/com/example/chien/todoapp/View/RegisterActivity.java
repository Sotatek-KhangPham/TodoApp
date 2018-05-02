package com.example.chien.todoapp.View;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

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

//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Please Wait"); // set message
//        progressDialog.show();
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
//        progressDialog.dismiss();
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
        intent.putExtra("Email",viewModel.getEmail());
        intent.putExtra("Password",viewModel.getPassword());
        setResult(200,intent);
        disposable.dispose();
         finish();
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


}
