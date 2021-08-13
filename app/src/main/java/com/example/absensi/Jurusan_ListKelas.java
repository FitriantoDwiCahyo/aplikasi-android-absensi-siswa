package com.example.absensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Jurusan_ListKelas extends AppCompatActivity {


    LinearLayout btn_tkjx1,btn_tkjx2,btn_tkjxi1,btn_tkjxi2,btn_tkjxii1,btn_tkjxii2;
    LinearLayout btn_akx1,btn_akx2,btn_akxi1,btn_akxi2,btn_akxii1,btn_akxii2;
    LinearLayout btn_pmx1,btn_pmx2,btn_pmxi1,btn_pmxi2,btn_pmxii1,btn_pmxii2;
    LinearLayout btn_mmx1,btn_mmx2,btn_mmxi1,btn_mmxi2,btn_mmxii1,btn_mmxii2;
    LinearLayout btn_rplx1,btn_rplx2,btn_rplxi1,btn_rplxi2,btn_rplxii1,btn_rplxii2;
    LinearLayout btn_apx1,btn_apx2,btn_apxi1,btn_apxi2,btn_apxii1,btn_apxii2;

    ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jurusan__list_kelas);

        btn_akx1 = findViewById(R.id.btn_akx1);
        btn_akx2 = findViewById(R.id.btn_akx2);
        btn_akxi1 = findViewById(R.id.btn_akxi1);
        btn_akxi2 = findViewById(R.id.btn_akxi2);
        btn_akxii1 = findViewById(R.id.btn_akxii1);
        btn_akxii2 = findViewById(R.id.btn_akxii2);
        btn_apx1 = findViewById(R.id.btn_apx1);
        btn_apx2 = findViewById(R.id.btn_apx2);
        btn_apxi1 = findViewById(R.id.btn_apxi1);
        btn_apxi2 = findViewById(R.id.btn_apxi2);
        btn_apxii1 = findViewById(R.id.btn_apxii1);
        btn_apxii2 = findViewById(R.id.btn_apxii2);
        btn_rplx1 = findViewById(R.id.btn_rplx1);
        btn_rplx2 = findViewById(R.id.btn_rplx2);
        btn_rplxi1 = findViewById(R.id.btn_rplxi1);
        btn_rplxi2 = findViewById(R.id.btn_rplxi2);
        btn_rplxii1 = findViewById(R.id.btn_rplxii1);
        btn_rplxii2 = findViewById(R.id.btn_rplxii2);
        btn_pmx1 = findViewById(R.id.btn_pmx1);
        btn_pmx2 = findViewById(R.id.btn_pmx2);
        btn_pmxi1 = findViewById(R.id.btn_pmxi1);
        btn_pmxi2 = findViewById(R.id.btn_pmxi2);
        btn_pmxii1 = findViewById(R.id.btn_pmxii1);
        btn_pmxii2 = findViewById(R.id.btn_pmxii2);
        btn_mmx1 = findViewById(R.id.btn_mmx1);
        btn_mmx2 = findViewById(R.id.btn_mmx2);
        btn_mmxi1 = findViewById(R.id.btn_mmxi1);
        btn_mmxi2 = findViewById(R.id.btn_mmxi2);
        btn_mmxii1 = findViewById(R.id.btn_mmxii1);
        btn_mmxii2 = findViewById(R.id.btn_mmxii2);
        btn_tkjx1 = findViewById(R.id.btn_tkjx1);
        btn_tkjx2 = findViewById(R.id.btn_tkjx2);
        btn_tkjxi1 = findViewById(R.id.btn_tkjxi1);
        btn_tkjxi2 = findViewById(R.id.btn_tkjxi2);
        btn_tkjxii1 = findViewById(R.id.btn_tkjxii1);
        btn_tkjxii2 = findViewById(R.id.btn_tkjxii2);

        btn_back = findViewById(R.id.btn_back);


        btn_tkjx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas1");
                back.putExtra("jenis_kelas","TKJ X-1");
                back.putExtra("jurusan","Teknik Komputer Jaringan");
                startActivity(back);
            }
        });
        btn_tkjx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_kelas","TKJ X-2");
                back.putExtra("jenis_jurusan","kelas2");
                back.putExtra("jurusan","Teknik Komputer Jaringan");
                startActivity(back);
            }
        });
        btn_mmx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back1= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back1.putExtra("jenis_kelas","MM X-1");
                back1.putExtra("jenis_jurusan","kelas10");
                back1.putExtra("jurusan","Multimedia");

                startActivity(back1);
            }
        });
        btn_mmx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas11");
                back.putExtra("jenis_kelas","MM X-2");
                back.putExtra("jurusan","Multimedia");
                startActivity(back);
            }
        });
        btn_mmxi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas12");
                back.putExtra("jenis_kelas","MM XI-1");
                back.putExtra("jurusan","Multimedia");
                startActivity(back);
            }
        });
        btn_mmxi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas13");
                back.putExtra("jenis_kelas","MM XI-2");
                back.putExtra("jurusan","Multimedia");
                startActivity(back);
            }
        });
        btn_mmxii1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas14");
                back.putExtra("jenis_kelas","MM XII-1");
                back.putExtra("jurusan","Multimedia");
                startActivity(back);
            }
        });
        btn_mmxii2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas15");
                back.putExtra("jenis_kelas","MM XII-2");
                back.putExtra("jurusan","Multimedia");

                startActivity(back);
            }
        });
        btn_tkjxi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas4");
                back.putExtra("jenis_kelas","TKJ XI-1");
                back.putExtra("jurusan","Teknik Komputer Jaringan");
                startActivity(back);
            }
        });
        btn_tkjxi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas5");
                back.putExtra("jenis_kelas","TKJ XI-2");
                back.putExtra("jurusan","Teknik Komputer Jaringan");
                startActivity(back);
            }
        });
        btn_tkjxii1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas7");
                back.putExtra("jenis_kelas","TKJ XII-1");
                back.putExtra("jurusan","Teknik Komputer Jaringan");
                startActivity(back);
            }
        });
        btn_tkjxii2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas8");
                back.putExtra("jenis_kelas","TKJ XII-2");
                back.putExtra("jurusan","Teknik Komputer Jaringan");
                startActivity(back);
            }
        });
        btn_akx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas16");
                back.putExtra("jenis_kelas","AK X-1");
                back.putExtra("jurusan","Akutansi");
                startActivity(back);
            }
        });
        btn_akx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas17");
                back.putExtra("jenis_kelas","AK X-2");
                back.putExtra("jurusan","Akutansi");
                startActivity(back);
            }
        });
        btn_akxi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas18");
                back.putExtra("jenis_kelas","AK XI-1");
                back.putExtra("jurusan","Akutansi");
                startActivity(back);
            }
        });
        btn_akxi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas19");
                back.putExtra("jenis_kelas","AK XI-2");
                back.putExtra("jurusan","Akutansi");
                startActivity(back);
            }
        });
        btn_akxii1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas20");
                back.putExtra("jenis_kelas","AK XII-1");
                back.putExtra("jurusan","Akutansi");
                startActivity(back);
            }
        });
        btn_akxii2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas21");
                back.putExtra("jenis_kelas","AK XII-2");
                back.putExtra("jurusan","Akutansi");
                startActivity(back);
            }
        });
        btn_pmx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas22");
                back.putExtra("jenis_kelas","PM X-1");
                back.putExtra("jurusan","Pemasaran");
                startActivity(back);
            }
        });
        btn_pmx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas23");
                back.putExtra("jenis_kelas","PM X-2");
                back.putExtra("jurusan","Pemasaran");
                startActivity(back);
            }
        });
        btn_pmxi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas24");
                back.putExtra("jenis_kelas","PM XI-1");
                back.putExtra("jurusan","Pemasaran");
                startActivity(back);
            }
        });
        btn_pmxi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas25");
                back.putExtra("jenis_kelas","PM XI-2");
                back.putExtra("jurusan","Pemasaran");
                startActivity(back);
            }
        });
        btn_pmxii1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas26");
                back.putExtra("jenis_kelas","PM XII-1");
                back.putExtra("jurusan","Pemasaran");
                startActivity(back);
            }
        });
        btn_pmxii2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas27");
                back.putExtra("jenis_kelas","PM XII-2");
                back.putExtra("jurusan","Pemasaran");
                startActivity(back);
            }
        });
        btn_rplx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas28");
                back.putExtra("jenis_kelas","RPL X-1");
                back.putExtra("jurusan","Rekayasa Perangkat Lunak");
                startActivity(back);
            }
        });
        btn_rplx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas29");
                back.putExtra("jenis_kelas","RPL X-2");
                back.putExtra("jurusan","Rekayasa Perangkat Lunak");
                startActivity(back);
            }
        });
        btn_rplxi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas30");
                back.putExtra("jenis_kelas","RPL XI-1");
                back.putExtra("jurusan","Rekayasa Perangkat Lunak");
                startActivity(back);
            }
        });
        btn_rplxi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas31");
                back.putExtra("jenis_kelas","RPl XI-2");
                back.putExtra("jurusan","Rekayasa Perangkat Lunak");
                startActivity(back);
            }
        });
        btn_rplxii1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas32");
                back.putExtra("jenis_kelas","RPL XII-1");
                back.putExtra("jurusan","Rekayasa Perangkat Lunak");
                startActivity(back);
            }
        });
        btn_rplxii2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas33");
                back.putExtra("jenis_kelas","RPL XII-2");
                back.putExtra("jurusan","Rekayasa Perangkat Lunak");
                startActivity(back);
            }
        });
        btn_apx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas34");
                back.putExtra("jenis_kelas","AP X-1");
                back.putExtra("jurusan","Administrasi Perkantoran");
                startActivity(back);
            }
        });
        btn_apx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas35");
                back.putExtra("jenis_kelas","AP X-2");
                back.putExtra("jurusan","Administrasi Perkantoran");
                startActivity(back);
            }
        });
        btn_apxi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas36");
                back.putExtra("jenis_kelas","AP XI-1");
                back.putExtra("jurusan","Administrasi Perkantoran");
                startActivity(back);
            }
        });
        btn_apxi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas37");
                back.putExtra("jenis_kelas","AP XI-2");
                back.putExtra("jurusan","Administrasi Perkantoran");
                startActivity(back);
            }
        });
        btn_apxii1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas38");
                back.putExtra("jenis_kelas","AP XII-1");
                back.putExtra("jurusan","Administrasi Perkantoran");
                startActivity(back);
            }
        });
        btn_apxii2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Jurusan_ListKelas.this,DaftarNamaSiswa2.class);
                back.putExtra("jenis_jurusan","kelas39");
                back.putExtra("jenis_kelas","AP XII-2");
                back.putExtra("jurusan","Administrasi Perkantoran");
                startActivity(back);
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
