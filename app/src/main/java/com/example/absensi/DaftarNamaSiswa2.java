package com.example.absensi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class DaftarNamaSiswa2 extends AppCompatActivity implements AdapterContent.dataListener {

    Spinner list_kelas;
    TextView view_tgl,Jurusan,Kelas;
    LinearLayout btn_back;
    Button btn_tgl,btn_selesai,btn_savetgl;
    ArrayAdapter<CharSequence> adapter;

    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;

    DatabaseReference reference;
    DatabaseReference reference2;
    Task<Void> reference3;

    RecyclerView List_Siswa;
    ArrayList<AdapterListNama> list;
    AdapterContent content;

    FirebaseAuth auth;

    String USERNAME_KEY="usernamekey";
    String username_key="";

    String JURUSAN_KEY="jurusankey";
    String jurusan_key = "";

    String TGL ="tglkey";
    String tgl_key ="";


    String KELAS_KEY="kelaskey";
    String kelas_key ="";




    Integer angka0 =0,angka1=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_nama_siswa2);


        view_tgl = findViewById(R.id.view_tgl);
        Kelas =findViewById(R.id.Kelas);

        btn_tgl  = findViewById(R.id.btn_tgl);
        btn_selesai =findViewById(R.id.btn_selesai);
        btn_back = findViewById(R.id.btn_back);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);



        btn_tgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });
        view_tgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Data= view_tgl.getText().toString();
                Intent intent = new Intent(DaftarNamaSiswa2.this,DetaildanAbsen.class);
                intent.putExtra("tgl",Data);
            }
        });


        getUsernameLocal();
        getUsernameLocal1();
        getUsernameLocal2();
        auth =FirebaseAuth.getInstance();

        List_Siswa = findViewById(R.id.List_Siswa);
        List_Siswa.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<AdapterListNama>();



        getData1();


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

        SharedPreferences sharedPreferences = getSharedPreferences(TGL,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit() ;
        editor.putString(tgl_key,view_tgl.getText().toString());
        editor.apply();
    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key = sharedPreferences.getString(username_key, "");

    }

    public void getUsernameLocal1() {
        SharedPreferences sharedPreferences = getSharedPreferences(JURUSAN_KEY, MODE_PRIVATE);
        jurusan_key = sharedPreferences.getString(jurusan_key, "");

    }

    public void getUsernameLocal2() {
        SharedPreferences sharedPreferences = getSharedPreferences(KELAS_KEY, MODE_PRIVATE);
        kelas_key = sharedPreferences.getString(kelas_key, "");

    }



//    public void getEdit(){
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        LayoutInflater inflater = getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.edit_data,null);
//
//        builder.setView(dialogView);
//        builder.setCancelable(true);
//
//        final AlertDialog dialog = builder.create();
//
//        final EditText namaEdit = dialogView.findViewById(R.id.tnama);
//        final EditText nisEdit = dialogView.findViewById(R.id.tnis);
//
//        Button btnUpdate = dialogView.findViewById(R.id.btnUpdate);
//    }

    public void getData1(){


        Bundle bundle = getIntent().getExtras();
        final String jenis_kelas_pilih = bundle.getString("jenis_kelas");

        Bundle bundle2 = getIntent().getExtras();
        final String jenis_kelas_pilih1 = bundle2.getString("jenis_jurusan");

        Bundle bundle3 = getIntent().getExtras();
        final String jurusan = bundle3.getString("jurusan");

        reference2 = FirebaseDatabase.getInstance().getReference().child("jenis_kelas").child(jenis_kelas_pilih1);
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Kelas.setText(dataSnapshot.child("kelas").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference = FirebaseDatabase.getInstance().getReference().child("jurusan").child(jurusan).child(jenis_kelas_pilih);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    AdapterListNama p = dataSnapshot1.getValue(AdapterListNama.class);

                    //Mengambil Primary Key, digunakan untuk proses Update dan Delete
                    p.setKey(dataSnapshot.getKey());
                    list.add(p);

                }

                //mereplace
                content = new AdapterContent(DaftarNamaSiswa2.this, list);
                //mensetting adapter
                List_Siswa.setAdapter(content);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onDeleteData(AdapterListNama data, int position) {
        Bundle bundle2 = getIntent().getExtras();
        final String jenis_kelas_pilih1 = bundle2.getString("jenis_jurusan");

        Bundle bundle3 = getIntent().getExtras();
        final String jurusan = bundle3.getString("jurusan");

        reference3 =FirebaseDatabase.getInstance().getReference().child(jurusan).child(jenis_kelas_pilih1).removeValue();
        reference3.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(DaftarNamaSiswa2.this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
            }
        });

//        if(reference != null){
//            reference.child("Jurusan")
//                    .child(jurusan_key)
//                    .child(kelas_key)
//                    .removeValue()
//                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            Toast.makeText(DaftarNamaSiswa2.this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        }
    }
}
