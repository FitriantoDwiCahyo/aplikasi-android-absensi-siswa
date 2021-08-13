package com.example.absensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Pilih_Jurusan extends AppCompatActivity {

    Button btn_tkj,btn_rpl,btn_ap,btn_akutansi,btn_pemasaran,btn_multimedia;
    LinearLayout btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih__jurusan);

        btn_akutansi = findViewById(R.id.btn_akutansi);
        btn_ap = findViewById(R.id.btn_ap);
        btn_multimedia = findViewById(R.id.btn_multimedia);
        btn_rpl = findViewById(R.id.btn_rpl);
        btn_pemasaran = findViewById(R.id.btn_pemasaran);
        btn_tkj = findViewById(R.id.btn_tkj);

        btn_back = findViewById(R.id.btn_back);


        btn_akutansi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent akutansi = new Intent(Pilih_Jurusan.this, InputSiswaAk.class);
                akutansi.putExtra("pilih_jurusan","jurusan3");
                startActivity(akutansi);
            }
        });

        btn_ap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ap = new Intent(Pilih_Jurusan.this, InputSiswaAp.class);
                ap.putExtra("pilih_jurusan","jurusan4");
                startActivity(ap);
            }
        });
//
        btn_multimedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent multimedia = new Intent(Pilih_Jurusan.this, InputSiswaMM.class);
                multimedia.putExtra("pilih_jurusan","jurusan5");
                startActivity(multimedia);
            }
        });

        btn_pemasaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pemasaran = new Intent(Pilih_Jurusan.this, InputSiswaPm.class);
                pemasaran.putExtra("pilih_jurusan","jurusan6");
                startActivity(pemasaran);
            }
        });

        btn_tkj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tkj = new Intent(Pilih_Jurusan.this, InputSiswa.class);
                tkj.putExtra("pilih_jurusan","jurusan1");
                startActivity(tkj);
            }
        });

        btn_rpl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rpl = new Intent(Pilih_Jurusan.this, InputSiswaRpl.class);
                rpl.putExtra("pilih_jurusan","jurusan2");
                startActivity(rpl);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
