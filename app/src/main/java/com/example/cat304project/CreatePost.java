package com.example.cat304project;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class CreatePost extends AppCompatActivity {

    EditText postDetails;
    Button buttonCreate;

    DatabaseReference postDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        postDetails = findViewById(R.id.endPoint);
        buttonCreate = findViewById(R.id.submitBtn);

        postDB = FirebaseDatabase.getInstance().getReference().child("Posts");

        buttonCreate.setOnClickListener(view -> insertPostData());
    }

    private void insertPostData(){
        String postDescription = postDetails.getText().toString();
        Posts posts = new Posts(postDescription);

        if(postDescription.isEmpty()){
            Toast.makeText(this, "Post cannot be empty!", Toast.LENGTH_LONG).show();
        }else{
            postDB.push().setValue(posts);
            Toast.makeText(this, "Post has been published!", Toast.LENGTH_LONG).show();
        }
    }
}