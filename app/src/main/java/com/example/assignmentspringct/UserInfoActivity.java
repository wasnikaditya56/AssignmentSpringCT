package com.example.assignmentspringct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;

public class UserInfoActivity extends AppCompatActivity
{
    String email, phone;
    EditText edtUserinfoUsername, edtUserinfoPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        edtUserinfoUsername = findViewById(R.id.edt_userinfo_username);
        edtUserinfoPassword = findViewById(R.id.edt_userinfo_password);

        //For Receiving Data From MainActivity class
       /* Intent intent2 = getIntent();
        Bundle myBundle = intent2.getExtras();
        email = myBundle.getString("Email");
        phone = myBundle.getString("Phone");*/

        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        email = sharedPref.getString("Username", "");
        phone = sharedPref.getString("Password", "");


        edtUserinfoUsername.setText(email);
        edtUserinfoPassword.setText(phone);
    }
}