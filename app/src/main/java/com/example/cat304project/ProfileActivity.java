package com.example.cat304project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    TextView name;
    TextView email;
    TextView phone;
    Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String uid = FirebaseAuth.getInstance().getUid();

        if (uid!=null) {
            DatabaseReference dbRef = database.getReference().child("profile").child(uid);
            dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull  DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        users = snapshot.getValue(Users.class);
                        if (users!=null) {
                            name.setText(users.name);
                            email.setText(users.email);
                            phone.setText(users.phone);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull  DatabaseError error) {

                }


            });
        }
    }


}

