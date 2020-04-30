package com.example.classregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class classifier extends AppCompatActivity {
    Button student;
    Button teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classifier);

        student = findViewById(R.id.stbtn);
        teacher = findViewById(R.id.trbtn);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rover = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(rover);
                setContentView(R.layout.activity_signup);
            }
        });

        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chrysler = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(chrysler);
                setContentView(R.layout.activity_signup);
            }
        });
    }
}
