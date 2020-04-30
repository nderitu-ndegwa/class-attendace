package com.example.classregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    EditText password;
    EditText email;
    Button Login;
    Button signupbutton;

    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();

        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        Login = findViewById(R.id.login);
        signupbutton = findViewById(R.id.signupbutton);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser != null) {
                    String name = mFirebaseUser.getDisplayName();
                    String email = mFirebaseUser.getEmail();
                    boolean emailVerified = mFirebaseUser.isEmailVerified();
                    String uid = mFirebaseUser.getUid();
                    Toast.makeText(MainActivity.this, "you are logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),qrgenerator.class);
                    startActivity(i);
                    setContentView(R.layout.activity_qrgenerator);
                }
                else{
                    Toast.makeText(MainActivity.this,"please check yor credentials and try again", Toast.LENGTH_SHORT).show();

                }
            }
        };

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailaddress = email.getText().toString();
                String pwd = password.getText().toString();
                if(emailaddress.isEmpty()) {
                    email.setError("please enter your email address");
                    email.requestFocus();
                }
                else if (pwd.isEmpty()){
                    password.setError("please enter your passwordd");
                    password.requestFocus();
                }
                else if(emailaddress.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();

                }
                else if(!(emailaddress.isEmpty() && pwd.isEmpty())) {
                    mFirebaseAuth.signInWithEmailAndPassword(emailaddress, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "error occurred", Toast.LENGTH_SHORT).show();

                            }
                            else {
                                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                                Intent rover = new Intent(getApplicationContext(),qrgenerator.class);
                                startActivity(rover);
                                setContentView(R.layout.activity_qrgenerator);
                            }
                        }
                    });
                }
            }
        });

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent audi = new Intent(getApplicationContext(),classifier.class);
                startActivity(audi);
                setContentView(R.layout.activity_classifier);
            }
        });

    }

    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }



}