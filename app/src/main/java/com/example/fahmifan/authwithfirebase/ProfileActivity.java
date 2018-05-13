package com.example.fahmifan.authwithfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private TextView mEmail;
    private TextView mUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mEmail = findViewById(R.id.profile_email);
        mUid = findViewById(R.id.profile_uid);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        if(mUser != null) {
            String email = mUser.getEmail();
            String uid = mUser.getUid();
            mEmail.setText("Email : " + email);
            mUid.setText("UID : " + uid);
        }

        onLogOutHandler();
    }

    private void onLogOutHandler() {
        findViewById(R.id.button_logout).setOnClickListener(view -> {
            if(mUser != null) {
                mAuth.signOut();
                finish();
            }
        });
     }
}
