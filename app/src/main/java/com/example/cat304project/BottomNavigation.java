package com.example.cat304project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cat304project.databinding.BottomNavigationBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class BottomNavigation extends AppCompatActivity {

    private BottomNavigationBinding binding;
    private Button logout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = BottomNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_booking, R.id.navigation_community, R.id.navigation_user)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_bottom_navigation);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    public void launchReport(View v){
        //launch report section
        Intent i = new Intent(this, ReportActivity.class);
        startActivity(i);
    }

    public void launchPost(View v){
        //launch post section
        Intent i = new Intent(this, PostActivity.class);
        startActivity(i);
    }

    public void launchProfile(View v){
        //launch post section
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

    public void launchPassengerPage(View v){
        //launch post section
        Intent i = new Intent(this, DisplayOfferActivity.class);
        startActivity(i);
    }

    public void launchDriverPage(View v){
        //launch post section
        Intent i = new Intent(this, CreateOffer.class);
        startActivity(i);
    }

    public void launchMap(View v){
        //launch post section
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);
    }

    public void logout(View v){
        //launch post section
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void launchEWallet(View v){
        // launch eWallet section
        Intent i = new Intent(this, EWalletActivity.class);
        startActivity(i);
    }

}