package com.example.cat304project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText studentEmail, password;
    private Button btnLogIn;
    private ProgressBar loginProgress;
    private FirebaseAuth mAuth;
    //private Intent i;

    //DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        studentEmail = (EditText) findViewById(R.id.studentEmail1);
        password = (EditText) findViewById(R.id.password1);
        btnLogIn = (Button) findViewById(R.id.signIn1);
        //DB = new DBHelper(this);
        //Intent i = new Intent(this, com.example.cat304project.LoginActivity.class);
        //startActivity(i);
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //loginProgress.setVisibility(View.VISIBLE);
                //btnLogIn.setVisibility(View.VISIBLE);

                final String email = studentEmail.getText().toString();
                final String password1 = password.getText().toString();
                mAuth = FirebaseAuth.getInstance();

                if (email.isEmpty() || password1.isEmpty()) {
                    displayMessage("Please enter all field!");
                    //btnLogIn.setVisibility(View.VISIBLE);
                    //loginProgress.setVisibility(View.VISIBLE);
                }
                else
                {
                    signIn(email,password1);
                }
            }
        });
    }

        private void signIn(String email, String password1) {
            mAuth.signInWithEmailAndPassword(email,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //loginProgress.setVisibility(View.INVISIBLE);
                        //btnLogIn.setVisibility(View.VISIBLE);
                        Intent i = new Intent(getApplicationContext(), BottomNavigation.class);
                        startActivity(i);
                        //updateUI();
                    }
                    else {
                        displayMessage(task.getException().getMessage());
                        //btnLogIn.setVisibility(View.VISIBLE);
                        //loginProgress.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

    //private void updateUI() {
      //  startActivity(i);
       // finish();
    //}

    private void displayMessage(String text) {
        Toast.makeText(getApplicationContext(),text, Toast.LENGTH_LONG).show();
    }

    /*@Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null) {
            //user is already connected  so we need to redirect him to home page
            //updateUI();

        }
        else {

        }
    }*/
}