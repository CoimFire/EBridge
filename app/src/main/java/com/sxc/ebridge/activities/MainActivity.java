package com.sxc.ebridge.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.sxc.ebridge.R;
import com.sxc.ebridge.admin.AdminHomeActivity;

public class MainActivity extends AppCompatActivity {

    boolean loggedIn,isAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences preferences=getSharedPreferences("LOGIN",MODE_PRIVATE);
                loggedIn=preferences.getBoolean("isLoggedIn",false);
                isAdmin=preferences.getBoolean("isAdmin",false);

                if (isAdmin) {
                    Intent intent=new Intent(MainActivity.this, AdminHomeActivity.class);
                    startActivity(intent);
                }else if (loggedIn){
                    Intent intent=new Intent(MainActivity.this, BottomBarActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        }, 2000);

    }
}