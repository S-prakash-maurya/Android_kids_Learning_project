package com.example.kids_learning_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;


public class splashScreen extends AppCompatActivity {
    Handler handler;
    ImageView
            imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imageView = findViewById(R.id.splashscreen);
        Glide.with(this).asGif().load(R.drawable.artist).into(imageView);


        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashScreen.this, OptionPage.class);
                startActivity(intent);
                finish();
            }
        }, 8000);

    }
}