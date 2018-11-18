package com.dummies.ecoscoutgame;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class CaraMain2Activity extends AppCompatActivity {
    ImageButton pindah;
    MediaPlayer mp;
    TextView caramain2, petunjuksampahhijau, petunjuksampahbiru, petunjuksampahkuning, petunjuksampahmerah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cara_main2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mp = MediaPlayer.create(this, R.raw.suarakliktombol);
        caramain2 = (TextView) findViewById(R.id.instruction2);
        petunjuksampahhijau = (TextView) findViewById(R.id.petunjukSampahHijau);
        petunjuksampahbiru = (TextView) findViewById(R.id.petunjukSampahBiru);
        petunjuksampahkuning = (TextView) findViewById(R.id.petunjukSampahKuning);
        petunjuksampahmerah = (TextView) findViewById(R.id.petunjukSampahMerah);
        Typeface type3 = Typeface.createFromAsset(getAssets(),"comicsans.ttf");
        caramain2.setTypeface(type3);
        petunjuksampahhijau.setTypeface(type3);
        petunjuksampahbiru.setTypeface(type3);
        petunjuksampahkuning.setTypeface(type3);
        petunjuksampahmerah.setTypeface(type3);

        pindah = (ImageButton) findViewById(R.id.tombolBeranda3);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent = new Intent(CaraMain2Activity.this, PilihLevelActivity.class);
                startActivity(intent);
            }
        });

        pindah = (ImageButton) findViewById(R.id.tombolKeluar4);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent = new Intent(CaraMain2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        pindah = (ImageButton) findViewById(R.id.tombolkeCaraMain1);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent = new Intent(CaraMain2Activity.this, CaraMain1Activity.class);
                startActivity(intent);
            }
        });

    }
}
