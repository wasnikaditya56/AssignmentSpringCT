package com.example.assignmentspringct;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    EditText edtLoginUsername,edtLoginPassword;
    Button btnLogin;
    String phone,email;
    String userName, password;
    JSONArray jsonArray1;
    JSONObject jsonObject, jsonObject1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtLoginUsername = findViewById(R.id.edt_login_user);
        edtLoginPassword = findViewById(R.id.edt_login_password);
        btnLogin = findViewById(R.id.btn_login);

        login();

    }

    private void login()
    {
        btnLogin.setOnClickListener(new View.OnClickListener()
        {;
            @Override
            public void onClick(View v)
            {

                    userName = edtLoginUsername.getText().toString().trim(); //email
                    password = edtLoginPassword.getText().toString().trim(); //phone

                if (userName.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
                }
                 else if(password.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    // Enter the correct url for your api service site
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Api.URL_LOGIN, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response)
                                {
                                    try
                                    {
                                        JSONObject jsonObject = response;
                                        System.out.println("response:::: "+response);

                                        email = jsonObject.getString("email");
                                        phone = jsonObject.getString("phone");

                                        System.out.println("email:::: "+email);
                                        System.out.println("phone:::: "+phone);

                                        if((userName.equals(email)) && (password.equals(phone)))
                                        {
                                            Toast.makeText(MainActivity.this, "Login Successfully !!", Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);

                                            Bundle myData = new Bundle();
                                            myData.putString("Email", email);
                                            myData.putString("Phone", phone);
                                            intent.putExtras(myData);
                                            startActivity(intent);
                                            sharedPref();
                                        }
                                        else
                                        {
                                            Toast.makeText(MainActivity.this, "Something went wrong !!", Toast.LENGTH_SHORT).show();
                                        }


                                    }
                                    catch (JSONException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            error.printStackTrace();
                            VolleyLog.d("Error", "Error: " + error.getMessage());
                            Toast.makeText(MainActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    });
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(jsonObjectRequest);

                }
            }
        });

    }

    private void sharedPref()
    {
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Registered", true);
        editor.putString("Username", email);
        editor.putString("Password", phone);
        editor.apply();
    }
}