package com.example.cat304project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cat304project.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchHomePage(View v) {
        // launch home page activity
        Intent i = new Intent(this, BottomNavigation.class);
        startActivity(i);
    }

    public void launchLogin(View v) {
        // launch log in activity
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}
