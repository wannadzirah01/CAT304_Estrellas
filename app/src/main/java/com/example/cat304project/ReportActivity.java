package com.example.cat304project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReportActivity extends AppCompatActivity {

    EditText reportDetails;
    Button buttonSubmit;

    DatabaseReference reportDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        reportDetails = findViewById(R.id.reportDetails);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        reportDB = FirebaseDatabase.getInstance().getReference().child("Reports");

        buttonSubmit.setOnClickListener(view -> insertReportData());
    }

    private void insertReportData(){
        String reportDescription = reportDetails.getText().toString();
        Reports reports = new Reports(reportDescription);

        if(reportDescription.isEmpty()){
            Toast.makeText(this, "Report cannot be empty!", Toast.LENGTH_LONG).show();
        }else{
            reportDB.push().setValue(reports);
            Toast.makeText(this, "Report has been submitted!", Toast.LENGTH_LONG).show();
        }
    }

    /*public void handleText(View v){
        EditText t = findViewById(R.id.reportDetails);
        String input = t.getText().toString();
        //((TextView)findViewById(R.id.output)).setText(input);

        if(input.isEmpty()){
            Toast.makeText(this, "Field cannot be empty!", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(this, "Report is successfully submitted!", Toast.LENGTH_LONG).show();
        }
    }*/
}