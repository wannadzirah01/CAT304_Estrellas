package com.example.cat304project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateOffer extends AppCompatActivity {

    EditText startpoint, endpoint, date, time, fares, userGender, phoneNum;
    Button doneBtn;

    DatabaseReference offerDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_offer);

        startpoint = findViewById(R.id.startPoint);
        endpoint = findViewById(R.id.endPoint);
        date = findViewById(R.id.editTextDate);
        time = findViewById(R.id.editTextTime);
        fares = findViewById(R.id.price);
        userGender = findViewById(R.id.gender);
        phoneNum = findViewById(R.id.editTextPhone);
        doneBtn = findViewById(R.id.submitBtn);

        offerDB = FirebaseDatabase.getInstance().getReference().child("Offer");

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertOfferData();
            }
        });
    }

    private void insertOfferData(){
        String sPoint = startpoint.getText().toString();
        String ePoint = endpoint.getText().toString();
        String bookingDate = date.getText().toString();
        String bookingTime = time.getText().toString();
        String bookingFares = fares.getText().toString();
        String driverGender = userGender.getText().toString();
        String pNum = phoneNum.getText().toString();

        CarpoolOfferList OfferCarpool = new CarpoolOfferList(sPoint, ePoint, bookingDate, bookingTime, bookingFares, driverGender, pNum);

        if(sPoint.isEmpty() || ePoint.isEmpty() || bookingDate.isEmpty() ||
        bookingTime.isEmpty() || bookingFares.isEmpty() || driverGender.isEmpty() || pNum.isEmpty()){
            Toast.makeText(this, "Please enter all fields!", Toast.LENGTH_LONG).show();
        }else{
            offerDB.push().setValue(OfferCarpool);
            Toast.makeText(this, "Carpool offer has been published!", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}