package com.example.absensi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_guru extends AppCompatActivity {

    Button btn_login;
    EditText NRG;
    LinearLayout btn_admin;
    DatabaseReference reference;

    String USERNAME_KEY="usernamekey";
    String username_key="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_guru);

        btn_login = findViewById(R.id.btn_login);

        NRG = findViewById(R.id.NRG);

        btn_admin = findViewById(R.id.btn_admin);

        btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent admin = new Intent(Login_guru.this, LoginAdmin.class);
                startActivity(admin);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btn_login.setEnabled(false);
                btn_login.setText("Wait...");
                final String username = NRG.getText().toString();

                if (username.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Nomor Registrasi Kosong", Toast.LENGTH_SHORT).show();
                    btn_login.setEnabled(true);
                    btn_login.setText("SIGN IN");
                } else {
                    reference = FirebaseDatabase.getInstance().getReference().child("User").child(username);

                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {

                                //ambil data NRG dari firedatabase
                                String NRGFromFirebase = dataSnapshot.child("username").getValue().toString();

                                //validasi NRG dengan NRG di firebase
                                if (username.equals(NRGFromFirebase)) {
                                    //Menyimpan username (key) kepada lokal
                                    SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString(username_key, NRG.getText().toString());
                                    editor.apply();

                                    //pindah activity
                                    Intent sign = new Intent(Login_guru.this, Home.class);
                                    startActivity(sign);
                                }


                            } else {
                                Toast.makeText(getApplicationContext(), "Nomor Registrasi Tidak Ada", Toast.LENGTH_SHORT).show();

                                btn_login.setEnabled(true);
                                btn_login.setText("SIGN IN");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                            Toast.makeText(getApplicationContext(), "Database Eror", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }





        });


    }
}
