package com.example.absensi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadPdfAbsen extends AppCompatActivity {

    Button button;

    TextView nama,nama2,kelas;
    DatabaseReference reference;

    String USERNAME_KEY="usernamekey";
    String username_key="";

    String KLS_KEY="klskey";
    String kls_key="";

    String TGL ="tglkey";
    String tgl_key ="";

    Bitmap bmp,scaleBitmap;

    String[] infoArray = new String[]{"Name","Company Name","Address","Phone","Email"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_pdf_absen);

        button =findViewById(R.id.button);
        bmp = BitmapFactory.decodeResource(getResources(),R.drawable.logo);
        scaleBitmap = Bitmap.createScaledBitmap(bmp,200,200,false);

        nama = findViewById(R.id.nama);
        nama2 = findViewById(R.id.nama2);
        kelas = findViewById(R.id.kelas);

        getUsernameLocal();
        getUsernameLocal1();
        getUsernameLocal2();

//        getData();

        reference = FirebaseDatabase.getInstance().getReference().child("Hasil_Absen_backup").child(username_key).child(kls_key+tgl_key);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama.setText(dataSnapshot.child("nama1").getValue().toString());
                nama2.setText(dataSnapshot.child("nama2").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ActivityCompat.requestPermissions(this,new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        createPDF();
    }

//    private void getData(){
//
//        final String getNama= getIntent().getExtras().getString("dataNama");
//        final String getNis= getIntent().getExtras().getString("getNis");
//        final String getKelas= getIntent().getExtras().getString("getKelas");
//        nama.setText(getNama);
//        nis.setText(getNis);
//        kelas.setText(getKelas);
//    }

    private void createPDF(){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Report Absen Sukses Disimpan",Toast.LENGTH_LONG).show();
                PdfDocument mypdfdocument = new PdfDocument();
                Paint myPaint = new Paint();

                PdfDocument.PageInfo mypageinfo1 =new  PdfDocument.PageInfo.Builder(250,400,1).create();
                PdfDocument.Page mypage1 = mypdfdocument.startPage(mypageinfo1);

                Canvas canvas =mypage1.getCanvas();

//                canvas.drawBitmap(scaleBitmap,40,50,myPaint);
//
//                mypdfdocument.finishPage(mypage1);


                //percobaan Kedua pdf dengan table
                myPaint.setTextAlign(Paint.Align.CENTER);
                myPaint.setTextSize(12.0f);
                canvas.drawText("Bojong.CO",mypageinfo1.getPageWidth()/2,30,myPaint);

                myPaint.setTextSize(6.0f);
                myPaint.setColor(Color.rgb(122,119,119));
                canvas.drawText("Jalan Komplek Bpn Blok A13 No 08",mypageinfo1.getPageWidth()/2,40,myPaint);

                myPaint.setTextAlign(Paint.Align.LEFT);
                myPaint.setTextSize(9.0f);
                myPaint.setColor(Color.rgb(122,119,119));
                canvas.drawText("Customer Information",10,70,myPaint);

                myPaint.setTextAlign(Paint.Align.LEFT);
                myPaint.setTextSize(8.0f);
                myPaint.setColor(Color.BLACK);

                int startXPosition = 10;
                int endXPosition = mypageinfo1.getPageWidth()-10;
                int startYPosition = 100;

                for (int i=0;i<=4;i++){
                    canvas.drawText(infoArray[i],startXPosition,startYPosition,myPaint);
                    canvas.drawLine(startXPosition,startYPosition+3,endXPosition,startYPosition+3,myPaint);
                    startYPosition+=20;
                }

                canvas.drawLine(80,92,80,190,myPaint);

                myPaint.setStyle(Paint.Style.STROKE);
                myPaint.setStrokeWidth(2);
                canvas.drawRect(10,200,mypageinfo1.getPageWidth()-10,300,myPaint);
                canvas.drawLine(85,200,85,300,myPaint);
                canvas.drawLine(163,200,163,300,myPaint);
                myPaint.setStrokeWidth(0);
                myPaint.setStyle(Paint.Style.FILL);

                canvas.drawText(nama.getText().toString(),35,250,myPaint);
                canvas.drawText(nama2.getText().toString(),110,250,myPaint);
                canvas.drawText("Photo",190,250,myPaint);

                canvas.drawText("Note:",10,320,myPaint);
                canvas.drawLine(35,325,mypageinfo1.getPageWidth()-10,325,myPaint);
                canvas.drawLine(10,345,mypageinfo1.getPageWidth()-10,345,myPaint);
                canvas.drawLine(10,365,mypageinfo1.getPageWidth()-10,365,myPaint);




                mypdfdocument.finishPage(mypage1);
//
//
//                 //Percobaan Pertama
//                canvas.drawText("INI PDF PERTAMA GUA!!",40,50,myPaint);
//                mypdfdocument.finishPage(mypage1);
//
//                //Ini PDF Page Kedua
//                PdfDocument.PageInfo mypageinfo2 =new  PdfDocument.PageInfo.Builder(250,400,1).create();
//                PdfDocument.Page mypage2 = mypdfdocument.startPage(mypageinfo2);
//
//                Canvas canvas2 =mypage2.getCanvas();
//
//                canvas2.drawText("INI PDF KEDUA GUA!!",40,50,myPaint);
//                mypdfdocument.finishPage(mypage2);

                File file = new File(Environment.getExternalStorageDirectory(),"/FirstPDF.pdf");

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
