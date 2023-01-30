package com.example.cat304project;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DisplayOfferActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_offer);

        listView = findViewById(R.id.ListView);
        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item2, list);
        listView.setAdapter(adapter);

        DatabaseReference offerDB = FirebaseDatabase.getInstance().getReference().child("Offer");
        offerDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                for (DataSnapshot offerSnapshot : snapshot.getChildren()){
                    Offer offerList = offerSnapshot.getValue(Offer.class);
                    String txt = "StartPoint: " + offerList.getStartPoint() + "\nEndpoint: " + offerList.getEndPoint() + "\n" +
                            offerList.getDate() + "\n" + offerList.getTime() + "\n" + offerList.getFares()
                            + "\n" + offerList.getGender() + "\n" + offerList.getPhoneNum() + "\n";
                    list.add(txt);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}