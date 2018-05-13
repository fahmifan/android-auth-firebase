package com.example.fahmifan.authwithfirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText mPassword;
    private EditText mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail = findViewById(R.id.signup_email_input);
        mPassword = findViewById(R.id.signup_password_input);
        mAuth = FirebaseAuth.getInstance();

        onRegisterHandler();
        onLoginHandler();
    }

    public void registerUser() {
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

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {

                    try {
                        // check if succesfull

                        if(task.isSuccessful()) {
                            // User is successfully registered and logged in
                            // start Profile Activity
                            Toast.makeText(MainActivity.this,
                                    "Registeration successfull", Toast.LENGTH_SHORT)
                                    .show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));

                        } else {
                            Toast.makeText(MainActivity.this,
                                    "Coludn't register, please try again", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    } catch(Exception e) {
                        e.printStackTrace();
                    }

                });
    }

    private void onRegisterHandler() {
        findViewById(R.id.button_register).setOnClickListener(view -> {
            registerUser();
        });
    }

    private void onLoginHandler() {
        findViewById(R.id.button_login).setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });
    }

}
