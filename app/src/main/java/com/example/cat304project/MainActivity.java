package com.example.cat304project;

//import static android.os.Build.VERSION_CODES.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
//import com.example.cat304project.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    EditText studentName, password, password2, studentEmail;
    Button createAccount, logIn1;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentName = (EditText) findViewById(R.id.studentName);
        studentEmail = (EditText) findViewById(R.id.studentEmail);
        password = (EditText) findViewById(R.id.password);
        password2 = (EditText) findViewById(R.id.confirmPassword);
        createAccount = (Button) findViewById(R.id.createAccount);
        logIn1 = (Button) findViewById(R.id.logIn1);
        DB = new DBHelper(this);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userEmail = studentEmail.getText().toString();
                String userName = studentName.getText().toString();
                String pass = password.getText().toString();
                String conPass = password2.getText().toString();

                if(userEmail.equals("")||userName.equals("")||pass.equals("")||conPass.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all the fields!", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(conPass)){
                        Boolean checkuser = DB.checkStudentEmail(userEmail);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(userEmail, userName, pass);
                            if(insert==true){
                                Toast.makeText(MainActivity.this, "Account has been created successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), BottomNavigation.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "User already exists! Please go to LOG IN page!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Password does not matched!", Toast.LENGTH_SHORT).show();
                    }
                } }
        });

        logIn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });
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

    public void launchReport(View v){
        //launch report activity
        Intent i = new Intent(this, ReportActivity.class);
        startActivity(i);
    }
}
