package com.example.cat304project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
    }

    public void handleText(View v){
        EditText t = findViewById(R.id.reportDetails);
        String input = t.getText().toString();
        //((TextView)findViewById(R.id.output)).setText(input);
        Toast.makeText(this, "Report is successfully submitted!", Toast.LENGTH_LONG).show();
    }
}