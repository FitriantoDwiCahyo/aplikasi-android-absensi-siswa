package com.example.absensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class NotifSelesai extends AppCompatActivity {

    Button btn_success;
    Animation bottom_to_top,top_to_bottom,app_splash;
    ImageView imageView3;
    TextView textView5,textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif_selesai);

        btn_success = findViewById(R.id.btn_success);
        imageView3 = findViewById(R.id.imageView3);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);

        bottom_to_top = AnimationUtils.loadAnimation(this,R.anim.bottom_to_top);
        top_to_bottom = AnimationUtils.loadAnimation(this,R.anim.top_to_bottom);
        app_splash = AnimationUtils.loadAnimation(this,R.anim.app_splash);

        imageView3.startAnimation(app_splash);
        textView5.startAnimation(top_to_bottom);
        textView6.startAnimation(top_to_bottom);

        btn_success.startAnimation(bottom_to_top);

        btn_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_Home = new Intent(NotifSelesai.this,Home.class);
                startActivity(go_Home);
            }
        });
    }
}
