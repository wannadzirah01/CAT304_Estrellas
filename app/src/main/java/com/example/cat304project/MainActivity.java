package com.example.cat304project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    ImageView ImgUserPhoto;
    static int PReqCode = 1 ;
    static int REQUESCODE = 1 ;
    Uri pickedImgUri ;

    private EditText userName, userPassword, userPassword2, userEmail, phoneNum;
    private Button createAccountBtn;
    private ProgressBar loadingProgress;
    private FirebaseAuth mAuth;
    DatabaseReference mainDB;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.studentName);
        userEmail = (EditText) findViewById(R.id.studentEmail);
        userPassword = (EditText) findViewById(R.id.password);
        userPassword2 = (EditText) findViewById(R.id.confirmPassword);
        phoneNum = (EditText) findViewById(R.id.studentPhone);
        createAccountBtn = (Button) findViewById(R.id.createAccount);
        Button logIn1Btn = (Button) findViewById(R.id.logIn1);
        mainDB = FirebaseDatabase.getInstance().getReference().child("Users");


        //loadingProgress.setVisibility(View.INVISIBLE);
        mAuth = FirebaseAuth.getInstance();

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createAccountBtn.setVisibility(View.INVISIBLE);
                //loadingProgress.setVisibility(View.VISIBLE);
                final String email = userEmail.getText().toString();
                final String password1 = userPassword.getText().toString();
                final String password2 = userPassword2.getText().toString();
                final String name = userName.getText().toString();
                final String phone = phoneNum.getText().toString();
                Users users = new Users(email,name,phone);


                if( email.isEmpty() || name.isEmpty() || password1.isEmpty()  || !password1.equals(password2) || phone.isEmpty()) {
                    // display error message
                    displayMessage("Please enter all fields!") ;
                    createAccountBtn.setVisibility(View.VISIBLE);
                    //loadingProgress.setVisibility(View.INVISIBLE);
                }
                else {
                    // CreateUserAccount method will try to create the user if the email is valid
                    mainDB.push().setValue(users);
                    CreateUserAccount(email,name,password1,phone);
                }
            }

        });
    }

    private void CreateUserAccount(String email, final String name, String password, String phone) {
        // this method create user account with specific email and password
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // user account created successfully
                            displayMessage("Account has been successfully created!");
                            Intent i  = new Intent(getApplicationContext(), BottomNavigation.class);
                            startActivity(i);
                            // after we created user account we need to update his profile picture and name
                            //updateUserInfo( name ,pickedImgUri,mAuth.getCurrentUser());
                        }
                        else
                        {
                            // account creation failed
                            displayMessage("Failed to create new account!" + task.getException().getMessage());
                            createAccountBtn.setVisibility(View.VISIBLE);
                            //loadingProgress.setVisibility(View.INVISIBLE);
                        }
                    }
                });
    }

    private void updateUI() {
        Intent i = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(i);
        finish();
    }

    // method to display message to users
    private void displayMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null ) {
            // the user has successfully picked an image
            // we need to save its reference to a Uri variable
            pickedImgUri = data.getData() ;
            ImgUserPhoto.setImageURI(pickedImgUri);
        };
    }

    public void launchLogIn(View v) {
        // launch log in activity
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
