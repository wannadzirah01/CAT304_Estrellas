package com.example.cat304project;

//import static android.os.Build.VERSION_CODES.R;

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
//import com.example.cat304project.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    ImageView ImgUserPhoto;
    static int PReqCode = 1 ;
    static int REQUESCODE = 1 ;
    Uri pickedImgUri ;

    private EditText userName, userPassword, userPassword2, userEmail;
    private Button createAccountBtn;
    private ProgressBar loadingProgress;
    private FirebaseAuth mAuth;
    //DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.studentName);
        userEmail = (EditText) findViewById(R.id.studentEmail);
        userPassword = (EditText) findViewById(R.id.password);
        userPassword2 = (EditText) findViewById(R.id.confirmPassword);
        createAccountBtn = (Button) findViewById(R.id.createAccount);
        Button logIn1Btn = (Button) findViewById(R.id.logIn1);

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

                if( email.isEmpty() || name.isEmpty() || password1.isEmpty()  || !password1.equals(password2)) {
                    // display error message
                    displayMessage("Please enter all fields!") ;
                    createAccountBtn.setVisibility(View.VISIBLE);
                    //loadingProgress.setVisibility(View.INVISIBLE);
                }
                else {
                    // CreateUserAccount method will try to create the user if the email is valid
                    CreateUserAccount(email,name,password1);
                }
            }

        });

        /*logIn1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), BottomNavigation.class);
                startActivity(i);
            }
        });*/

        //ImgUserPhoto = findViewById(R.id.regUserPhoto) ;
/*        ImgUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= 22) {
                    checkAndRequestForPermission();
                }
                else
                {
                    openGallery();
                }
            }
        });*/
    }

    private void CreateUserAccount(String email, final String name, String password) {
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

    // update user photo and name
    /*private void updateUserInfo(final String name, Uri pickedImgUri, final FirebaseUser currentUser) {

        // first we need to upload user photo to firebase storage and get url
        StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("users_photos");
        final StorageReference imageFilePath = mStorage.child(pickedImgUri.getLastPathSegment());
        imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                // image uploaded succesfully
                // now we can get our image url

                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        // uri contain user image url

                        UserProfileChangeRequest profleUpdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .setPhotoUri(uri)
                                .build();

                        currentUser.updateProfile(profleUpdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            // user info updated successfully
                                            displayMessage("Register Complete");
                                            updateUI();
                                        }
                                    }
                                });
                    }
                });
            }
        });

    }*/

    private void updateUI() {
        Intent i = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(i);
        finish();
    }

    // simple method to show toast message
    private void displayMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }

    /*private void openGallery() {
        //TODO: open gallery intent and wait for user to pick an image !
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);
    }*/

    /*private void checkAndRequestForPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(MainActivity.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();
            }
            else
            {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);
            }
        }
        else
            openGallery();
    }*/

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

    /*public void launchHomePage(View v) {
        // launch home page activity
        Intent i = new Intent(this, BottomNavigation.class);
        startActivity(i);
    }*/

    public void launchLogIn(View v) {
        // launch log in activity
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    /*public void launchBottomNavigation(View v) {
        // launch log in activity
        Intent i = new Intent(this, BottomNavigation.class);
        startActivity(i);
        finish();
    }*/
}
