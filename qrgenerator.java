package com.example.classregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class qrgenerator extends AppCompatActivity {
    EditText sessionName;
    Button generateCode;
    ImageView qrCode;
    Button scanCode;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrgenerator);

        sessionName = findViewById(R.id.sessionName);
        generateCode = findViewById(R.id.generateCode);
        qrCode = findViewById(R.id.qrCode);
        scanCode = findViewById(R.id.scanCode);
        logout = findViewById(R.id.logout);

       generateCode.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String text= sessionName.getText().toString();// Whatever you need to encode in the QR code
               MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
               try {
                   BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,200,200);
                   BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                   Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                   qrCode.setImageBitmap(bitmap);
               } catch (WriterException e) {
                   e.printStackTrace();
               }
           }
       });
        scanCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), qrscanner.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent audi = new Intent( qrgenerator.this, MainActivity.class);
                startActivity(audi);
            }
        });

    }
}
