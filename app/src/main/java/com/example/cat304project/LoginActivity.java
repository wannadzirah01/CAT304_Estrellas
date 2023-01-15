package com.example.cat304project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText studentEmail, password;
    Button btnLogIn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        studentEmail = (EditText) findViewById(R.id.studentEmail1);
        password = (EditText) findViewById(R.id.password1);
        btnLogIn = (Button) findViewById(R.id.signIn1);
        DB = new DBHelper(this);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = studentEmail.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkstudentpass = DB.checkStudentEmailPassword(user, pass);
                    if(checkstudentpass==true){
                        Toast.makeText(LoginActivity.this, "You have successfully log in", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), BottomNavigation.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}