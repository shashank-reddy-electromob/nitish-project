package com.nitish.paymatrixandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RentAgreementActivity extends AppCompatActivity {

    Button upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_agreement);

        upload = findViewById(R.id.uploadfile);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),UploadFileActivity.class));
            }
        });
    }

    public void navigationDrawer1(View v){
        Intent i=new Intent(this,NavigationActivity.class);
        startActivity(i);
    }

    public void goBack(View v){
        finish();
    }
}
