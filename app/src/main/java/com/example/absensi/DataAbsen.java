package com.example.absensi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DataAbsen extends AppCompatActivity {

    LinearLayout btn_back;

    DatabaseReference reference;

    RecyclerView my_kelas_place;
    ArrayList<Mykelas> list;
    Kelas_Adapter kelas_adapter;

    String USERNAME_KEY="usernamekey";
    String username_key="";

    String KLS_KEY="klskey";
    String kls_key="";

    String TGL ="tglkey";
    String tgl_key ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_absen);

        getUsernameLocal1();
        getUsernameLocal();
        getUsernameLocal2();

        btn_back = findViewById(R.id.btn_back);

        my_kelas_place = findViewById(R.id.my_kelas_place);
        my_kelas_place.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Mykelas>();


        reference = FirebaseDatabase.getInstance().getReference().child("Hasil_Absen").child(username_key).child(kls_key+tgl_key);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Mykelas p = dataSnapshot1.getValue(Mykelas.class);
                    list.add(p);
                }
                //mereplace
                kelas_adapter = new Kelas_Adapter(DataAbsen.this,list);
                //mensetting adapter
                my_kelas_place.setAdapter(kelas_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(KLS_KEY, MODE_PRIVATE);
        kls_key = sharedPreferences.getString(kls_key, "");

    }
    public void getUsernameLocal1() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key = sharedPreferences.getString(username_key, "");

    }

    public void getUsernameLocal2() {
        SharedPreferences sharedPreferences = getSharedPreferences(TGL, MODE_PRIVATE);
        tgl_key = sharedPreferences.getString(tgl_key, "");

    }
}
