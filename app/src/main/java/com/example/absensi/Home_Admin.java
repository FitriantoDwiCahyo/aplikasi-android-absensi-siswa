package com.example.absensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Home_Admin extends AppCompatActivity {

    LinearLayout btn_input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__admin);

        btn_input = findViewById(R.id.btn_input);

        btn_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent input = new Intent(Home_Admin.this, Pilih_Jurusan.class);
                startActivity(input);
            }
        });



    }
}
