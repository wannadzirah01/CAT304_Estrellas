package com.example.cat304project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
    }

    public void launchCreate(View v){
        //launch create post
        Intent i = new Intent(this, CreatePost.class);
        startActivity(i);
    }
}