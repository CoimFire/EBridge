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
import com.sxc.ebridge.database.LoginDBHelper;
import com.sxc.ebridge.utils.LoginResult;
import com.sxc.ebridge.utils.Utils;

public class SignUpActivity extends AppCompatActivity {

    EditText etId,etPassword,etReTypePassword;
    String id,password,reTypePassword;
    TextView tvSignup;

    LoginDBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etId=findViewById(R.id.et_student_id);
        etPassword=findViewById(R.id.et_password);
        etReTypePassword=findViewById(R.id.et_re_type_password);
        tvSignup=findViewById(R.id.tv_sign_up);
        DB=new LoginDBHelper(this);

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.hideKeyboard(SignUpActivity.this);
                id=etId.getText().toString().trim();
                password=etPassword.getText().toString().trim();
                reTypePassword=etReTypePassword.getText().toString().trim();

                if (TextUtils.isEmpty(id)&&TextUtils.isEmpty(password)&&TextUtils.isEmpty(reTypePassword)){
                    Toast.makeText(SignUpActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }else {
                    if (password.length()<6&&etReTypePassword.length()<6){
                        Toast.makeText(SignUpActivity.this, "Password should contins at least 6 characters", Toast.LENGTH_SHORT).show();
                    }else {
                        if (password.equals(reTypePassword)) {
                            String checkSignup=DB.signup(id,password);
                            validateSignUp(checkSignup);

                        } else {
                            Toast.makeText(SignUpActivity.this, "Check your password", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

    }

    private void validateSignUp(String checkUpdate) {
        switch (checkUpdate){
            case LoginResult.WRONG_ID:
                Toast.makeText(this, "Already created. Please return to login", Toast.LENGTH_LONG).show();
                break;
            case LoginResult.FAILED:
                Toast.makeText(this, "Your Id is not registered.\n Please contact ADMIN", Toast.LENGTH_LONG).show();
                break;
            case LoginResult.OK:
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
                SharedPreferences.Editor editor=getSharedPreferences("LOGIN",MODE_PRIVATE).edit();
                editor.putBoolean("isLoggedIn",true);
                editor.apply();
                Intent intent=new Intent(SignUpActivity.this,BottomBarActivity.class);
                startActivity(intent);
                break;
            case "NULL":
                Toast.makeText(this, "Your Id is not registered.\n Please contact ADMIN", Toast.LENGTH_LONG).show();
        }
    }
}