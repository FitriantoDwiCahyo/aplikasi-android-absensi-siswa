package com.example.absensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Animation app_splash;

    ImageView logo;

    String USERNAME_KEY="usernamekey";
    String username_key="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app_splash = AnimationUtils.loadAnimation(this,R.anim.app_splash);

        logo = findViewById(R.id.logo);

        logo.startAnimation(app_splash);

        getUsernameLocal();

    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key = sharedPreferences.getString(username_key, "");

        if (username_key.isEmpty()){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent Tostarted = new Intent(MainActivity.this,Login_guru.class);
                    startActivity(Tostarted);
                    finish();
                }
            },2000); //2000 ms = 2 detik
        }else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent Tohome = new Intent(MainActivity.this,Home.class);
                    startActivity(Tohome);
                    finish();
                }
            },2000); //2000 ms = 2 detik

        }
    }
}
