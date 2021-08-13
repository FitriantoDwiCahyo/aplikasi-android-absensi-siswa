package com.example.absensi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DaftarNamaSiswa extends AppCompatActivity {

    TextView nama_siswa,nama_siswa2,nama_siswa3,nama_siswa4,nama_siswa5,nama_siswa6,view_tgl,Kelas,kelas;
    TextView hadir,alfa,izin,sakit;
    Button btn_tgl,btn_selesai;
    RadioButton rb_hadir,rb_alfa,rb_izin,rb_sakit;
    RadioButton rb_hadir2,rb_alfa2,rb_izin2,rb_sakit2;
    RadioButton rb_hadir3,rb_alfa3,rb_izin3,rb_sakit3;
    RadioButton rb_hadir4,rb_alfa4,rb_izin4,rb_sakit4;
    RadioButton rb_hadir5,rb_alfa5,rb_izin5,rb_sakit5;
    RadioButton rb_hadir6,rb_alfa6,rb_izin6,rb_sakit6;
    Spinner list_kelas;
    LinearLayout btn_back;

    Integer no=1,no2=2,no3=3,no4=4,no5=5,no6=6;


    String valuekehadiran,valuekehadiran2,valuekehadiran3,valuekehadiran4,valuekehadiran5,valuekehadiran6 ;

    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;

    DatabaseReference reference,reference2;

    String USERNAME_KEY="usernamekey";
    String username_key="";

    String KELAS_KEY="kelaskey";
    String kelas_key = "";
    String kelas_key_new = "";







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_nama_siswa);
        getUsernameLocal();


        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);


        btn_back = findViewById(R.id.btn_back);
        btn_tgl = findViewById(R.id.btn_tgl);
        btn_selesai = findViewById(R.id.btn_selesai);


//        rg_keterangan3 = findViewById(R.id.rg_keterangan3);
//        rg_keterangan4 = findViewById(R.id.rg_keterangan4);
//        rg_keterangan5 = findViewById(R.id.rg_keterangan5);
//        rg_keterangan6 = findViewById(R.id.rg_keterangan6);
//        rg_keterangan.setOnCheckedChangeListener(this);
        rb_hadir = findViewById(R.id.rb_hadir);
        rb_alfa = findViewById(R.id.rb_alfa);
        rb_izin = findViewById(R.id.rb_izin);
        rb_sakit = findViewById(R.id.rb_sakit);

        rb_hadir2 = findViewById(R.id.rb_hadir2);
        rb_alfa2 = findViewById(R.id.rb_alfa2);
        rb_izin2 = findViewById(R.id.rb_izin2);
        rb_sakit2 = findViewById(R.id.rb_sakit2);

        rb_hadir3 = findViewById(R.id.rb_hadir3);
        rb_alfa3 = findViewById(R.id.rb_alfa3);
        rb_izin3 = findViewById(R.id.rb_izin3);
        rb_sakit3 = findViewById(R.id.rb_sakit3);

        rb_hadir4 = findViewById(R.id.rb_hadir4);
        rb_alfa4 = findViewById(R.id.rb_alfa4);
        rb_izin4 = findViewById(R.id.rb_izin4);
        rb_sakit4 = findViewById(R.id.rb_sakit4);

        rb_hadir5 = findViewById(R.id.rb_hadir5);
        rb_alfa5 = findViewById(R.id.rb_alfa5);
        rb_izin5 = findViewById(R.id.rb_izin5);
        rb_sakit5 = findViewById(R.id.rb_sakit5);

        rb_hadir6 = findViewById(R.id.rb_hadir6);
        rb_alfa6 = findViewById(R.id.rb_alfa6);
        rb_izin6 = findViewById(R.id.rb_izin6);
        rb_sakit6 = findViewById(R.id.rb_sakit6);

        nama_siswa = findViewById(R.id.nama_siswa);
        nama_siswa2 = findViewById(R.id.nama_siswa2);
        nama_siswa3 = findViewById(R.id.nama_siswa3);
        nama_siswa4 = findViewById(R.id.nama_siswa4);
        nama_siswa5 = findViewById(R.id.nama_siswa5);
        nama_siswa6 = findViewById(R.id.nama_siswa6);

        view_tgl = findViewById(R.id.view_tgl);
        Kelas = findViewById(R.id.Kelas);
        kelas = findViewById(R.id.kelas);
//        list_kelas = findViewById(R.id.list_kelas);

        hadir = findViewById(R.id.hadir);
        alfa = findViewById(R.id.alfa);
        sakit = findViewById(R.id.sakit);
        izin = findViewById(R.id.izin);





        btn_tgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });




        //mengambil data dari intent
        Bundle bundle = getIntent().getExtras();
        final String jenis_kelas_pilih = bundle.getString("Jenis_kelas");

        SharedPreferences sharedPreferences = getSharedPreferences(KELAS_KEY,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit() ;
        editor.putString(kelas_key,Kelas.getText().toString());
        editor.apply();




        //mangambil data dari firebase berdasarkan intent
        reference = FirebaseDatabase.getInstance().getReference().child("Kelas").child(kelas_key).child(jenis_kelas_pilih);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                kelas.setText(dataSnapshot.child("Kelas").getValue().toString());
                Kelas.setText(dataSnapshot.child("kelaskey").getValue().toString());
                nama_siswa.setText(dataSnapshot.child("Nama1").getValue().toString());
                nama_siswa2.setText(dataSnapshot.child("Nama2").getValue().toString());
                nama_siswa3.setText(dataSnapshot.child("Nama3").getValue().toString());
                nama_siswa4.setText(dataSnapshot.child("Nama4").getValue().toString());
                nama_siswa5.setText(dataSnapshot.child("Nama5").getValue().toString());
                nama_siswa6.setText(dataSnapshot.child("Nama6").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//        rg_keterangan3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//
//                keteranganOption3 = rg_keterangan3.findViewById(i);
//
//                switch (i){
//                    case R.id.rb_hadir3:
//                        valuekehadiran3 = keteranganOption3.getText().toString();
//                    case R.id.rb_alfa3:
//                        valuekehadiran3 = keteranganOption3.getText().toString();
//                    case R.id.rb_izin3:
//                        valuekehadiran3 = keteranganOption3.getText().toString();
//                    case R.id.rb_sakit3:
//                        valuekehadiran3 = keteranganOption3.getText().toString();
//                }
//
//            }
//        });
//
//        rg_keterangan4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//
//                keteranganOption4 = rg_keterangan4.findViewById(i);
//
//                switch (i){
//                    case R.id.rb_hadir4:
//                        valuekehadiran4 = keteranganOption4.getText().toString();
//                    case R.id.rb_alfa4:
//                        valuekehadiran4 = keteranganOption4.getText().toString();
//                    case R.id.rb_izin4:
//                        valuekehadiran4 = keteranganOption4.getText().toString();
//                    case R.id.rb_sakit4:
//                        valuekehadiran4 = keteranganOption4.getText().toString();
//                }
//
//            }
//        });
//
//        rg_keterangan5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//
//                keteranganOption5 = rg_keterangan5.findViewById(i);
//
//                switch (i){
//                    case R.id.rb_hadir5:
//                        valuekehadiran5 = keteranganOption5.getText().toString();
//                    case R.id.rb_alfa5:
//                        valuekehadiran5 = keteranganOption5.getText().toString();
//                    case R.id.rb_izin5:
//                        valuekehadiran5 = keteranganOption5.getText().toString();
//                    case R.id.rb_sakit5:
//                        valuekehadiran5 = keteranganOption5.getText().toString();
//                }
//
//            }
//        });
//
//        rg_keterangan6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//
//                keteranganOption6 = rg_keterangan6.findViewById(i);
//
//                switch (i){
//                    case R.id.rb_hadir6:
//                        valuekehadiran6 = keteranganOption6.getText().toString();
//                    case R.id.rb_alfa6:
//                        valuekehadiran6 = keteranganOption6.getText().toString();
//                    case R.id.rb_izin6:
//                        valuekehadiran6 = keteranganOption6.getText().toString();
//                    case R.id.rb_sakit6:
//                        valuekehadiran6 = keteranganOption6.getText().toString();
//                }
//
//            }
//        });





//        btn_hadir.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                valuehadir ="Hadir";
//                btn_hadir.setEnabled(false);
//            }
//        });

        btn_selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                //radiobutton pertama
                if (rb_hadir.isChecked()){
                    valuekehadiran = " Hadir";
                }else if (rb_alfa.isChecked()){
                    valuekehadiran = " Alfa";
                }else if (rb_sakit.isChecked()){
                    valuekehadiran = " Sakit";
                }else if (rb_izin.isChecked()){
                    valuekehadiran = " Izin";
                }

                //radiobutton kedua
                if (rb_hadir2.isChecked()){
                    valuekehadiran2 = " Hadir";
                }else if (rb_alfa2.isChecked()){
                    valuekehadiran2 = " Alfa";
                }else if (rb_sakit2.isChecked()){
                    valuekehadiran2 = " Sakit";
                }else if (rb_izin2.isChecked()){
                    valuekehadiran2 = " Izin";
                }

                //radiobutton ketiga
                if (rb_hadir3.isChecked()){
                    valuekehadiran3 = " Hadir";
                }else if (rb_alfa3.isChecked()){
                    valuekehadiran3 = " Alfa";
                }else if (rb_sakit3.isChecked()){
                    valuekehadiran3 = " Sakit";
                }else if (rb_izin3.isChecked()){
                    valuekehadiran3 = " Izin";
                }

                //radiobutton keempat
                if (rb_hadir4.isChecked()){
                    valuekehadiran4 = " Hadir";
                }else if (rb_alfa4.isChecked()){
                    valuekehadiran4 = " Alfa";
                }else if (rb_sakit4.isChecked()){
                    valuekehadiran4 = " Sakit";
                }else if (rb_izin4.isChecked()){
                    valuekehadiran4 = " Izin";
                }

                //radiobutton kelima
                if (rb_hadir5.isChecked()){
                    valuekehadiran5 = " Hadir";
                }else if (rb_alfa5.isChecked()){
                    valuekehadiran5 = " Alfa";
                }else if (rb_sakit5.isChecked()){
                    valuekehadiran5 = " Sakit";
                }else if (rb_izin5.isChecked()){
                    valuekehadiran5 = " Izin";
                }

                //radiobutton keenam
                if (rb_hadir6.isChecked()){
                    valuekehadiran6 = " Hadir";
                }else if (rb_alfa6.isChecked()){
                    valuekehadiran6 = " Alfa";
                }else if (rb_sakit6.isChecked()){
                    valuekehadiran6 = " Sakit";
                }else if (rb_izin6.isChecked()){
                    valuekehadiran6 = " Izin";
                }


                reference =  FirebaseDatabase.getInstance().getReference().child("Daftar_Absen").child(username_key).child(kelas_key_new)
                        .child(kelas.getText().toString()+view_tgl.getText().toString());
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        reference.getRef().child("kelas").setValue(Kelas.getText().toString());
                        reference.getRef().child("id_data").setValue(kelas.getText().toString()+view_tgl.getText().toString());
                        reference.getRef().child("nama_siswa1").setValue(nama_siswa.getText().toString()+"("+valuekehadiran+")");
                        reference.getRef().child("nama_siswa2").setValue(nama_siswa2.getText().toString()+"("+valuekehadiran2+")");
                        reference.getRef().child("nama_siswa3").setValue(nama_siswa3.getText().toString()+"("+valuekehadiran3+")");
                        reference.getRef().child("nama_siswa4").setValue(nama_siswa4.getText().toString()+"("+valuekehadiran4+")");
                        reference.getRef().child("nama_siswa5").setValue(nama_siswa5.getText().toString()+"("+valuekehadiran5+")");
                        reference.getRef().child("nama_siswa6").setValue(nama_siswa6.getText().toString()+"("+valuekehadiran6+")");
                        reference.getRef().child("tanggal").setValue(view_tgl.getText().toString());

                        Intent succes = new Intent(DaftarNamaSiswa.this,NotifSelesai.class);
                        startActivity(succes);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });



        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void showDateDialog(){

        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                view_tgl.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

         datePickerDialog.show();
    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key = sharedPreferences.getString(username_key, "");

    }

//    public void radio1(){
//        rg_keterangan = findViewById(R.id.rg_keterangan);
//        rg_keterangan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//
//                keteranganOption = rg_keterangan.findViewById(i);
//
//                switch (i){
//                    case R.id.rb_hadir:
//                        valuekehadiran = keteranganOption.getText().toString();
//                        break;
//                    case R.id.rb_alfa:
//                        valuekehadiran = keteranganOption.getText().toString();
//                        break;
//                    case R.id.rb_izin:
//                        valuekehadiran = keteranganOption.getText().toString();
//                        break;
//                    case R.id.rb_sakit:
//                        valuekehadiran = keteranganOption.getText().toString();
//                        break;
//                }
//
//            }
//        });
//    }
//
//
//    public void radio2(){
//        rg_keterangan2 = findViewById(R.id.rg_keterangan2);
//        rg_keterangan2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//
//                keteranganOption = rg_keterangan2.findViewById(i);
//
//                switch (i){
//                    case R.id.rb_hadir2:
//                        valuekehadiran2 = keteranganOption.getText().toString();
//                        break;
//                    case R.id.rb_alfa2:
//                        valuekehadiran2 = keteranganOption.getText().toString();
//                        break;
//                    case R.id.rb_izin2:
//                        valuekehadiran2 = keteranganOption.getText().toString();
//                        break;
//                    case R.id.rb_sakit2:
//                        valuekehadiran2 = keteranganOption.getText().toString();
//                        break;
//                }
//
//            }
//        });
//    }

//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        if (checkedId == R.id.rb_hadir) {
//            valuekehadiran = "Hadir";
//        }
//        if (checkedId == R.id.rb_alfa) {
//            valuekehadiran = "Alfa";
//        }
//        if (checkedId == R.id.rb_izin) {
//            valuekehadiran = "Izin";
//        }
//        if (checkedId == R.id.rb_sakit) {
//            valuekehadiran = "Sakit";
//        }
//
//    }


}


