package com.example.absensi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class About_us extends AppCompatActivity {

    ImageView photo_profile,photo_profile2,photo_profile3,photo_profile4,photo_profile5,photo_profile6;
    Button report;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        photo_profile = findViewById(R.id.photo_profile);
        photo_profile2 = findViewById(R.id.photo_profile2);
        photo_profile3 = findViewById(R.id.photo_profile3);
        photo_profile4 = findViewById(R.id.photo_profile4);
        photo_profile5 = findViewById(R.id.photo_profile5);
        photo_profile6 = findViewById(R.id.photo_profile6);

//        report = findViewById(R.id.btn_report);

        reference = FirebaseDatabase.getInstance().getReference().child("photo_bojong");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Picasso.with(About_us.this).load(dataSnapshot.child("photorian").getValue().toString()).centerCrop().fit().into(photo_profile);
                Picasso.with(About_us.this).load(dataSnapshot.child("photomiftah").getValue().toString()).centerCrop().fit().into(photo_profile2);
                Picasso.with(About_us.this).load(dataSnapshot.child("photofahmi").getValue().toString()).centerCrop().fit().into(photo_profile3);
                Picasso.with(About_us.this).load(dataSnapshot.child("photoaa").getValue().toString()).centerCrop().fit().into(photo_profile4);
                Picasso.with(About_us.this).load(dataSnapshot.child("photobre").getValue().toString()).centerCrop().fit().into(photo_profile5);
                Picasso.with(About_us.this).load(dataSnapshot.child("photodeny").getValue().toString()).centerCrop().fit().into(photo_profile6);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rep = new Intent(About_us.this,ReadPdfAbsen.class);
                startActivity(rep);
            }
        });
    }
}
