package com.example.absensi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginAdmin extends AppCompatActivity {

    Button btn_login;
    DatabaseReference reference;
    EditText no_admin;

    String ADMIN_KEY="admin_key";
    String admin_key="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        btn_login= findViewById(R.id.btn_login);
        no_admin = findViewById(R.id.No_admin);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btn_login.setEnabled(false);
                btn_login.setText("Wait...");
                final String admin = no_admin.getText().toString();

                if (admin.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Nomor Admin Kosong", Toast.LENGTH_SHORT).show();
                    btn_login.setEnabled(true);
                    btn_login.setText("SIGN IN");
                } else {
                    reference = FirebaseDatabase.getInstance().getReference().child("Admin").child(admin);

                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {

                                //ambil data NRG dari firedatabase
                                String NRGFromFirebase = dataSnapshot.child("no_admin").getValue().toString();

                                //validasi NRG dengan NRG di firebase
                                if (admin.equals(NRGFromFirebase)) {
                                    //Menyimpan username (key) kepada lokal
                                    SharedPreferences sharedPreferences = getSharedPreferences(ADMIN_KEY, MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString(admin_key, no_admin.getText().toString());
                                    editor.apply();

                                    //pindah activity
                                    Intent sign = new Intent(LoginAdmin.this, Home_Admin.class);
                                    startActivity(sign);
                                }


                            } else {
                                Toast.makeText(getApplicationContext(), "Nomor Admin Tidak Ada", Toast.LENGTH_SHORT).show();

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
