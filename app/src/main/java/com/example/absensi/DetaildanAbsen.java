package com.example.absensi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetaildanAbsen extends AppCompatActivity {

    TextView nama,nis,kelas,tgl,jk;
    ImageView photo_profile;
    EditText keterangan;
    RadioButton rb_hadir,rb_alfa,rb_izin,rb_sakit;
    DatabaseReference database,database2,database3,database4;
    FirebaseAuth auth;
    Button btn_selesai;
    Date datetime;
    DateFormat dateFormat;

    String valuekehadiran;

    String USERNAME_KEY="usernamekey";
    String username_key="";

    String TGL ="tglkey";
    String tgl_key ="";

    String KLS_KEY="klskey";
    String kls_key="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaildan_absen);

        nama = findViewById(R.id.nama);
        nis = findViewById(R.id.nis);
        kelas = findViewById(R.id.kelas);
        keterangan = findViewById(R.id.keterangan);
        tgl = findViewById(R.id.view_tgl);
        jk = findViewById(R.id.jk);
        photo_profile =findViewById(R.id.photo_profile);
        rb_alfa = findViewById(R.id.rb_alfa);
        rb_hadir = findViewById(R.id.rb_hadir);
        rb_izin = findViewById(R.id.rb_izin);
        rb_sakit = findViewById(R.id.rb_sakit);
        btn_selesai = findViewById(R.id.btn_selesai);


        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        getData();
        tanggal();
        getUsernameLocal2();

        SharedPreferences sharedPreferences = getSharedPreferences(TGL,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit() ;
        editor.putString(tgl_key,tgl.getText().toString());
        editor.apply();

        SharedPreferences sharedPreferences1 = getSharedPreferences(KLS_KEY,MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferences1.edit() ;
        editor1.putString(kls_key,kelas.getText().toString());
        editor1.apply();

        btn_selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rb_hadir.isChecked()){
                    valuekehadiran = " Hadir";
                }else if (rb_alfa.isChecked()){
                    valuekehadiran = " Alfa";
                }else if (rb_sakit.isChecked()){
                    valuekehadiran = " Sakit";
                }else if (rb_izin.isChecked()){
                    valuekehadiran = " Izin";
                }

                database =  FirebaseDatabase.getInstance().getReference().child("Hasil_Absen").child(username_key)
                        .child(kelas.getText().toString()+tgl.getText().toString()).child(nis.getText().toString());
                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        database.getRef().child("nama").setValue(nama.getText().toString());
                        database.getRef().child("nis").setValue(nis.getText().toString());
                        database.getRef().child("kelas").setValue(kelas.getText().toString());
                        database.getRef().child("kehadiran").setValue(valuekehadiran);
                        database.getRef().child("jk").setValue(jk.getText().toString());
                        database.getRef().child("keterangan").setValue(keterangan.getText().toString());
                        database.getRef().child("tgl_absen").setValue(tgl.getText().toString());
                        database.getRef().child("id_absen").setValue(kelas.getText().toString()+tgl.getText().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                database2 =  FirebaseDatabase.getInstance().getReference().child("Hasil_Absen_backup").child(username_key)
                        .child(kelas.getText().toString()+tgl_key);
                database2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            database2.getRef().child("nama"+1).setValue(nama.getText().toString() + "     " + valuekehadiran);
//                        }else if (dataSnapshot.exists()){
//                            database2.getRef().child("nama"+2).setValue(nama.getText().toString() + "     " + valuekehadiran);
//                        }else if (dataSnapshot.exists()) {
//                            database2.getRef().child("nama" +3).setValue(nama.getText().toString() + "     " + valuekehadiran);
//                        }else if (dataSnapshot.exists()) {
//                            database2.getRef().child("nama" +4).setValue(nama.getText().toString() + "     " + valuekehadiran);
//                        }else {
//                            database2.getRef().child("nama" +5).setValue(nama.getText().toString() + "     " + valuekehadiran);
                        }

//                        for (int i =0;dataSnapshot.equals();i++){
//                            database2.getRef().child("nama"+i).setValue(nama.getText().toString() + "     " + valuekehadiran);
//                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Toast.makeText(getApplicationContext(),"Sukses Input Absen",Toast.LENGTH_LONG).show();


            }
        });

    }

    public void getData(){
        getUsernameLocal1();
        final String getNama= getIntent().getExtras().getString("nama");
        final String getNis= getIntent().getExtras().getString("nis");
        final String getKelas= getIntent().getExtras().getString("kelas");
        final String getJK= getIntent().getExtras().getString("jk");
        final String getFoto= getIntent().getExtras().getString("dataPhoto");
//        final String getTgl= tgl_key;
        nama.setText(getNama);
        nis.setText(getNis);
        kelas.setText(getKelas);
        jk.setText(getJK);
//        Bundle bn =getIntent().getExtras();
//        String tgl1 = bn.getString("tgl");
//        tgl.setText(String.valueOf(tgl1));
        Picasso.with(DetaildanAbsen.this).load(getFoto).centerCrop().fit().into(photo_profile);

    }

    public void getUsernameLocal1() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key = sharedPreferences.getString(username_key, "");

    }

    public void getUsernameLocal2() {
        SharedPreferences sharedPreferences = getSharedPreferences(TGL, MODE_PRIVATE);
        tgl_key = sharedPreferences.getString(tgl_key, "");

    }

    public void tanggal(){
        datetime = new Date();

        dateFormat = new SimpleDateFormat("dd-MM-yy");

        tgl.setText(dateFormat.format(datetime));

    }
}
