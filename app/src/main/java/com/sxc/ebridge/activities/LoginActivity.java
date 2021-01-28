package com.sxc.ebridge.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sxc.ebridge.R;
import com.sxc.ebridge.admin.AdminHomeActivity;
import com.sxc.ebridge.database.LoginDBHelper;
import com.sxc.ebridge.utils.LoginResult;
import com.sxc.ebridge.utils.Utils;

public class LoginActivity extends AppCompatActivity {
    EditText etId,etPassword;
    String id,password;
    TextView tvLogin,tvSignup;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    LoginDBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etId=findViewById(R.id.et_student_id);
        etPassword=findViewById(R.id.et_password);
        tvLogin=findViewById(R.id.tv_login);
        tvSignup=findViewById(R.id.tv_sign_up);
        DB=new LoginDBHelper(this);
        preferences=getSharedPreferences("LOGIN",MODE_PRIVATE);
        editor=preferences.edit();
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.hideKeyboard(LoginActivity.this);
                id=etId.getText().toString().trim();
                password=etPassword.getText().toString().trim();

                if (TextUtils.isEmpty(id)&&TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
                }else {
                    if (id.equals(LoginResult.ADMIN_ID)&&password.equals(LoginResult.ADMIN_PASSWORD)){
                        editor.putBoolean("isAdmin",true);
                        editor.commit();
                        Intent intent=new Intent(LoginActivity.this, AdminHomeActivity.class);
                        startActivity(intent);
                    }else {
                        String checklogin=DB.signup(id,password);
                        validateLogin(checklogin);
                    }

                }
            }
        });
        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void validateLogin(String checklogin) {

        switch (checklogin){
            case LoginResult.WRONG_ID:
                Toast.makeText(this, "No record found.. Please Signup", Toast.LENGTH_LONG).show();
                break;
            case LoginResult.WRONG_PASSWORD:
                Toast.makeText(this, "Wrong Password", Toast.LENGTH_LONG).show();
                break;
            case LoginResult.OK:
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
                editor.putBoolean("isLoggedIn",true);
                editor.apply();
                Intent intent=new Intent(LoginActivity.this,BottomBarActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show();
        }
    }
}