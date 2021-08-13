package com.example.absensi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import static android.text.TextUtils.isEmpty;

public class UpdateData extends AppCompatActivity {

    EditText tnama,tnis;
    Button update;
    DatabaseReference database;
    FirebaseAuth auth;
    String cekNama,cekNis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        tnama =  findViewById(R.id.tnama);
        tnis = findViewById(R.id.tnis);
        update = findViewById(R.id.btnUpdate);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        getData();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekNama = tnama.getText().toString();
                cekNis =tnis.getText().toString();

                if (isEmpty(cekNama)||isEmpty(cekNis)){
                    Toast.makeText(UpdateData.this,"Data Tidak Boleh Kosong",Toast.LENGTH_SHORT).show();
                }else {
                    AdapterListNama setSiswa = new AdapterListNama();
                    setSiswa.setNama_lengkap(tnama.getText().toString());
                    setSiswa.setNis(tnis.getText().toString());
                    updateSiswa(setSiswa);
                }
            }
        });
    }
    private  boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }

    //Menampilkan data yang akan di update
    private void getData(){

        final String getNama= getIntent().getExtras().getString("dataNama");
        final String getNis= getIntent().getExtras().getString("dataNis");
        tnama.setText(getNama);
        tnis.setText(getNis);
    }

    //proses update data yang sudah ditentukan
    private void updateSiswa(AdapterListNama siswa){
        Bundle bundle = getIntent().getExtras();
        final String jenis_kelas_pilih = bundle.getString("jenis_kelas");
        String getKey = getIntent().getExtras().getString("getPrimaryKey");
        database.child("Jurusan").child("Jurusan_TKJ").child("TKJ_X_Satu").child(getKey).setValue(siswa).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                tnama.setText("");
                tnis.setText("");
                Toast.makeText(UpdateData.this, "Data Berhasil diubah", Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }
}
