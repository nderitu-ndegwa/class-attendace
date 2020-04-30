package com.example.classregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.vision.barcode.Barcode;

public class session extends AppCompatActivity {

        TextView result;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_session);
            result = (TextView) findViewById(R.id.result);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 100 && resultCode == RESULT_OK) {
                if (data != null) {
                    final Barcode barcode = data.getParcelableExtra("barcode");
                    result.post(new Runnable() {
                        @Override
                        public void run() {
                            result.setText(barcode.displayValue);
                        }
                    });
                }
            }
        }}


