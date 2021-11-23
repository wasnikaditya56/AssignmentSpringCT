package com.example.assignmentspringct;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

public class SplashScreen extends Activity
{
	private static int SPLASH_TIME_OUT = 3000;
	 
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Boolean Registered;
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        Registered = sharedPref.getBoolean("Registered", false);


        
        new Handler().postDelayed(new Runnable() 
        {             
            public void run() 
            {

                if (!Registered)
                {
                    Intent i = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(i);
                }else {
                    Intent i = new Intent(SplashScreen.this,UserInfoActivity.class);
                    startActivity(i);
                }


            }
        }, SPLASH_TIME_OUT);
    }
    
    @Override
    public void onBackPressed() 
    {}
}
