package com.example.fahmifan.authwithfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private EditText mPassword;
    private EditText mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mEmail = findViewById(R.id.login_email_input);
        mPassword = findViewById(R.id.login_password_input);

        onLoginHandler();
    }

    private void loginUser() {
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        currentUser = mAuth.getCurrentUser();
                        finish();
                        startActivity(new Intent(LoginActivity.this,
                                ProfileActivity.class));
                    } else{
                        Toast.makeText(this, "Login failed, please try again", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void onLoginHandler() {
        findViewById(R.id.button_login).setOnClickListener(view -> {
            loginUser();
        });
    }

}
