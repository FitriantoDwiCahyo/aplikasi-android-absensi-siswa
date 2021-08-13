package com.example.absensi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class InputSiswaPm extends AppCompatActivity {

    Button btn_add_photo,btn_selesai;
    Spinner spinner,spinner2;
    TextView jurusan,outputspinner,outputspinner2;
    EditText nis_siswa,nama_lengkap;
    ImageView register_photo_user,btn_back;
    ArrayAdapter<CharSequence> adapter,adapter2;

    DatabaseReference reference,reference2;
    StorageReference storage;

    Uri photo_location;
    Integer photo_max=1;

    String JURUSAN_KEY="jurusankey";
    String jurusan_key ="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_siswa_pm);

        btn_add_photo = findViewById(R.id.btn_add_photo);
        btn_selesai = findViewById(R.id.btn_selesai);
        btn_back = findViewById(R.id.btn_back);
        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        jurusan = findViewById(R.id.jurusan);
        nis_siswa = findViewById(R.id.nis_siswa);
        nama_lengkap = findViewById(R.id.nama_lengkap);
        register_photo_user = findViewById(R.id.register_photo_user);
        outputspinner=findViewById(R.id.textspinner);
        outputspinner2 =findViewById(R.id.textspinner2);

        adapter = ArrayAdapter.createFromResource(this,R.array.Daftar_Kelas_PM,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                outputspinner.setText(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        adapter2 = ArrayAdapter.createFromResource(this,R.array.JK,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                outputspinner2.setText(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPhoto();
            }
        });


        Bundle bundle = getIntent().getExtras();
        final String pilih_jurusan = bundle.getString("pilih_jurusan");

        reference = FirebaseDatabase.getInstance().getReference().child("jenis_jurusan").child(pilih_jurusan);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                jurusan.setText(dataSnapshot.child("jurusan").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btn_selesai.setEnabled(false);
                btn_selesai.setText("tunggu...");

                SharedPreferences sharedPreferences = getSharedPreferences(JURUSAN_KEY,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit() ;
                editor.putString(jurusan_key,jurusan.getText().toString());
                editor.apply();

                reference2 = FirebaseDatabase.getInstance().getReference().child("jurusan")
                        .child(jurusan.getText().toString()).child(outputspinner.getText().toString()).child(nis_siswa.getText().toString());
                storage= FirebaseStorage.getInstance().getReference().child("PhotoSiswa").child(nis_siswa.getText().toString());

                if (photo_location !=null){
                    final StorageReference storageReference1= storage.child(System.currentTimeMillis()+"."+
                            getFileExtension(photo_location));

                    storageReference1.putFile(photo_location).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            storageReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String uri_photo = uri.toString();
                                    reference2.getRef().child("url_photo_profile").setValue(uri_photo);
                                    reference2.getRef().child("nama_lengkap").setValue(nama_lengkap.getText().toString());
                                    reference2.getRef().child("nis").setValue(nis_siswa.getText().toString());
                                    reference2.getRef().child("kelas").setValue(outputspinner.getText().toString());
                                    reference2.getRef().child("jk").setValue(outputspinner2.getText().toString());
                                }
                            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    Intent conti= new Intent(InputSiswaPm.this,Home_Admin.class);
                                    Toast.makeText(getApplicationContext(),"Sukses Input Siswa",Toast.LENGTH_LONG).show();
                                    startActivity(conti);
                                }
                            });
                        }
                    });
                }

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



    }

    String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    public void findPhoto(){
        Intent pic = new Intent();
        pic.setType("image/*");
        pic.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(pic,photo_max);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==photo_max && resultCode == RESULT_OK && data !=null && data.getData() != null){
            photo_location = data.getData();
            Picasso.with(this).load(photo_location).centerCrop().fit().into(register_photo_user);
        }
    }
}
