package com.nitish.paymatrixandroidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class PayRentActivity extends AppCompatActivity {

    private ImageView image;
    private TextView text;
    private Button addproperty;
    ImageView navigation;

    private TextView notify_user;

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_rent);

        image = findViewById(R.id.imageView);
        addproperty = findViewById(R.id.button);
        navigation = findViewById(R.id.navigation1);

        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NavigationActivity.class));
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PayRentActivity.this,PayRentArchiveActivity.class));
            }
        });

        addproperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PayRentActivity.this, AddPropertyActivity.class));
            }
        });

        notify_user=findViewById(R.id.notify);

        final RecyclerView rentPaidList = findViewById(R.id.rentPaymentList);

        rentPaidList.setLayoutManager(new LinearLayoutManager(this));

        db.collection("" +
                "ADD PROPERTY")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    ArrayList<HashMap> map=new ArrayList<>();
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                                HashMap<String,String> ans= new HashMap<>();
                                ans.put("pincode",document.get("pincode").toString());
                                ans.put("property address",document.get("property address").toString());
                                ans.put("property name",document.get("property name").toString());

                                map.add((HashMap) ans.clone());
                            }
                            if(map.size()!=0)
                                notify_user.setVisibility(View.INVISIBLE);
                            rentPaidList.setAdapter(new RentPaymentAdapter(map));
                        } else {
                            //Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }


    public void openArchiveList(View v){
        Intent i=new Intent(this,PayRentArchiveActivity.class);
        startActivity(i);
    }
    public void goBack(View v){
        finish();
    }
}
