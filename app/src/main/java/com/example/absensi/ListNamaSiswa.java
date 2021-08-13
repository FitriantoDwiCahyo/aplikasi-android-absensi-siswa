package com.example.absensi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ListNamaSiswa extends AppCompatActivity {

    LinearLayout btn_back;
    TextView xkelas,xtgl,nama_guru,nama1,nama2;
    Button btn_createpdf;
    ImageView photottd;
    DatabaseReference reference,reference2,reference3;
    int pageWidth = 1200;

    Bitmap bmp,scaleBitmap,bmp2,scaleBitmap2,bmp3,scaleBitmap3,scaleBitmap4;

    RecyclerView list_siswa_place;
    ArrayList<ListModelSiswa> list;
    TableViewAdapter tableViewAdapter;

    String USERNAME_KEY="usernamekey";
    String username_key="";

    String KLS_KEY="klskey";
    String kls_key="";

    String kelas="";
    String tanggal="";

    String TGL ="tglkey";
    String tgl_key ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nama_siswa);

        getUsernameLocal();
        getUsernameLocal1();
        getUsernameLocal2();

        btn_back = findViewById(R.id.btn_back);
        xkelas = findViewById(R.id.xkelas);
        xtgl = findViewById(R.id.xtgl);
        btn_createpdf = findViewById(R.id.btn_createpdf);
        photottd = findViewById(R.id.photo_ttd);
        nama_guru=findViewById(R.id.nama_guru);
        nama1 = findViewById(R.id.nama1);
        nama2 = findViewById(R.id.nama2);

        bmp = BitmapFactory.decodeResource(getResources(),R.drawable.logo);
        scaleBitmap = Bitmap.createScaledBitmap(bmp,350,350,false);

        bmp2 = BitmapFactory.decodeResource(getResources(),R.drawable.ttd_kepsek);
        scaleBitmap2 = Bitmap.createScaledBitmap(bmp2,250,200,false);




        list_siswa_place = findViewById(R.id.list_siswa_place);
        list_siswa_place.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<ListModelSiswa>();


        xkelas.setText(kls_key);
        xtgl.setText(tgl_key);

        reference2 = FirebaseDatabase.getInstance().getReference().child("User").child(username_key);
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama_guru.setText(dataSnapshot.child("Nama_lengkap").getValue().toString());
                Picasso.with(ListNamaSiswa.this).load(dataSnapshot.child("ttd").getValue().toString()).centerCrop().fit().into(photottd);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference3 = FirebaseDatabase.getInstance().getReference().child("Hasil_Absen_backup").child(username_key).child(kls_key+tgl_key);
        reference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama1.setText(dataSnapshot.child("nama1").getValue().toString());
                nama2.setText(dataSnapshot.child("nama2").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        nama2.setVisibility(View.GONE);
        nama1.setVisibility(View.GONE);



//        Bitmap bitmap= BitmapFactory.decodeFile();


        photottd.setVisibility(View.GONE);
        nama_guru.setVisibility(View.GONE);

//        bmp3=BitmapFactory.decodeResource(getResources(),R.id.photo_ttd);
//        photottd.setImageBitmap(bmp3);
//        scaleBitmap3 = Bitmap.createScaledBitmap(bmp3,250,200,false);



        //mengambil data dari intent
        Bundle bundle = getIntent().getExtras();
        final String makelas = bundle.getString("id_absen");



        reference = FirebaseDatabase.getInstance().getReference().child("Hasil_Absen").child(username_key).child(makelas);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    ListModelSiswa p = dataSnapshot1.getValue(ListModelSiswa.class);

                    //Mengambil Primary Key, digunakan untuk proses Update dan Delete
                    list.add(p);

                }

                //mereplace
                tableViewAdapter = new TableViewAdapter(ListNamaSiswa.this, list);
                //mensetting adapter
                list_siswa_place.setAdapter(tableViewAdapter);


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

        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        createPDF();


    }

    private void createPDF(){

        btn_createpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Report Absen Sukses Disimpan",Toast.LENGTH_LONG).show();
                PdfDocument mypdfdocument = new PdfDocument();
                Paint paint = new Paint();

                PdfDocument.PageInfo mypageinfo1 =new  PdfDocument.PageInfo.Builder(1200,2010,1).create();
                PdfDocument.Page mypage1 = mypdfdocument.startPage(mypageinfo1);

                Canvas canvas =mypage1.getCanvas();

                canvas.drawBitmap(scaleBitmap,5,5,paint);

                paint.setColor(Color.BLACK);
                paint.setTextSize(50f);
                paint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText("YAYASAN PENDIDIKAN ISLAM", 1060, 160, paint);

                paint.setColor(Color.BLACK);
                paint.setTextSize(50f);
                paint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText("SMK AL-BASYARIAH", 960, 220, paint);

                paint.setColor(Color.BLACK);
                paint.setTextSize(30f);
                paint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText("Jl.Pabuaran,Rw.Panjang,Kec.Bojong Gede - Bogor", 1040, 260, paint);
                canvas.drawText("Tlp.02187982715", 840, 310, paint);

                int startXPosition = 40;
                int endXPosition = mypageinfo1.getPageWidth()-40;
                int startYPosition = 360;

                for (int i=0;i<2;i++){
                    canvas.drawLine(startXPosition,startYPosition+3,endXPosition,startYPosition+3,paint);
                    startYPosition+=10;
                }

                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(2);
                canvas.drawRect(40, 460, pageWidth - 40, 540, paint);

                paint.setTextAlign(Paint.Align.LEFT);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawText("Nis.", 50, 510, paint);
                canvas.drawText("Nama Siswa", 230, 510, paint);
                canvas.drawText("Kelas", 700, 510, paint);
                canvas.drawText("JK", 860, 510, paint);
                canvas.drawText("Absen", 1020, 510, paint);

                canvas.drawLine(200, 460, 200, 540, paint);
                canvas.drawLine(680, 460, 680, 540, paint);
                canvas.drawLine(840, 460, 840, 540, paint);
                canvas.drawLine(1000, 460, 1000, 540, paint);

                canvas.drawBitmap(scaleBitmap2,800,1650,paint);

                paint.setColor(Color.BLACK);
                paint.setTextSize(35f);
                paint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText("( Helmi Awagiri.SE )", 1100, 1870, paint);
                canvas.drawLine(800, 1890, 1090, 1890, paint);
                canvas.drawText("Wakil Kepala Sekolah", 1110, 1930, paint);

                paint.setColor(Color.BLACK);
                paint.setTextSize(30f);
                paint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText(xkelas.getText().toString(), 250, 440, paint);
                canvas.drawText("Kelas : ", 150, 440, paint);
                canvas.drawText("Tanggal :", 1000, 440, paint);
                canvas.drawText(xtgl.getText().toString(), 1130, 440, paint);

                paint.setColor(Color.BLACK);
                paint.setTextSize(30f);
                paint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText(nama1.getText().toString(), 750, 580, paint);
                canvas.drawText(nama2.getText().toString(), 550, 660, paint);



//                canvas.drawBitmap(bmp3,400,1650,paint);


//                int startXPosition1 = 40;
//                int endXPosition1 = mypageinfo1.getPageWidth()-40;
//                int startYPosition1 = 600;
//
//                for (int i=0;i<20;i++){
//
//                    canvas.drawLine(startXPosition1,startYPosition1+3,endXPosition1,startYPosition1+3,paint);
//                    startYPosition+=20;
//                }



                mypdfdocument.finishPage(mypage1);

                File file = new File(Environment.getExternalStorageDirectory(),"/Data Absen SMK-AL Basyariah.pdf");

                try {
                    mypdfdocument.writeTo(new FileOutputStream(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mypdfdocument.close();
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
