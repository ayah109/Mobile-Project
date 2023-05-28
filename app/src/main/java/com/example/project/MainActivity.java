package com.example.project;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    SharedPrefManager sharedPrefManager;
    SignUp signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPrefManager =SharedPrefManager.getInstance(MainActivity.this);
        signUp = new SignUp();
        // Check whether the user is logged in or not.
        if(sharedPrefManager.readString("email", "none").equals("none")){
            Intent intent = new
                    Intent(this, SignIn.class);
            this.startActivity(intent);
        }
        // User is logged in, switch to the Home page
        else{
            Intent intent = new
                    Intent(this, HomePage.class);
            this.startActivity(intent);
        }

    }
}