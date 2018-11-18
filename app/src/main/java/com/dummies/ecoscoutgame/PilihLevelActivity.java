package com.dummies.ecoscoutgame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class PilihLevelActivity extends AppCompatActivity {
    ImageButton pindah;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_level);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mp = MediaPlayer.create(this, R.raw.suarakliktombol);

        pindah = (ImageButton) findViewById(R.id.tombolKeluarMenu);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent = new Intent(PilihLevelActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        pindah = (ImageButton) findViewById(R.id.tombolCaraMain);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent = new Intent(PilihLevelActivity.this,CaraMain1Activity.class);
                startActivity(intent);
            }
        });

        pindah = (ImageButton) findViewById(R.id.bglevel1);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                // go to Splash Opening 1
                Intent intent = new Intent(PilihLevelActivity.this,Opening1Level1Activity.class);
                startActivity(intent);

            }
        });

        pindah = (ImageButton) findViewById(R.id.tombolLevel1);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                // go to Splash Opening 1
                Intent intent = new Intent(PilihLevelActivity.this,Opening1Level1Activity.class);
                startActivity(intent);
            }
        });
    }
}
