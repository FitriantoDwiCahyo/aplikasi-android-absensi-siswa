package com.example.absensi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Home extends AppCompatActivity {

    LinearLayout btn_eabsen,btn_data_absen,btn_logout,btn_about_us;
    ImageView photo_profile;
    TextView Nama_lengkap,jenis_pengajar;

    DatabaseReference reference;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getUsernameLocal();

        btn_eabsen = findViewById(R.id.btn_eabsen);
        btn_data_absen = findViewById(R.id.btn_data_absen);
        btn_logout = findViewById(R.id.btn_logout);
        btn_about_us = findViewById(R.id.btn_about_us);

        photo_profile = findViewById(R.id.photo_profile);
        Nama_lengkap = findViewById(R.id.Nama_lengkap);
        jenis_pengajar = findViewById(R.id.jenis_pengajar);

        reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Nama_lengkap.setText(dataSnapshot.child("Nama_lengkap").getValue().toString());
                jenis_pengajar.setText(dataSnapshot.child("Pengajar").getValue().toString());
                Picasso.with(Home.this).load(dataSnapshot.child("Photo_url").getValue().toString()).centerCrop().fit().into(photo_profile);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_eabsen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ebasen = new Intent(Home.this,Jurusan_ListKelas.class);
                startActivity(ebasen);
            }
        });

        btn_data_absen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent databsen = new Intent(Home.this,DataAbsen.class);
                startActivity(databsen);
            }
        });

        btn_about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent about = new Intent(Home.this,About_us.class);
                startActivity(about);
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit() ;
                editor.putString(username_key,null);
                editor.apply();

                Intent signout =  new Intent(Home.this,Login_guru.class);
                startActivity(signout);
                finish();

            }
        });

    }
    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key = sharedPreferences.getString(username_key, "");

    }
}
