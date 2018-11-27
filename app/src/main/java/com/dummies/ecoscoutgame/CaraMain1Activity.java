package com.dummies.ecoscoutgame;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class CaraMain1Activity extends AppCompatActivity {
    ImageButton pindah;
    MediaPlayer mp;
    TextView caramain, caramain1b, caramain1c, caramain1d, caramain1e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_cara_main1);
        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 600)
        {
            setContentView(R.layout.activity_cara_main1_tablet);
        }
        else
        {
            setContentView(R.layout.activity_cara_main1);
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mp = MediaPlayer.create(this, R.raw.suarakliktombol);
        caramain = (TextView) findViewById(R.id.instruction1);
        caramain1b = (TextView) findViewById(R.id.instruction1b);
        caramain1c = (TextView) findViewById(R.id.instruction1c);
        caramain1d = (TextView) findViewById(R.id.instruction1d);
        caramain1e = (TextView) findViewById(R.id.instruction1e);
        Typeface type3 = Typeface.createFromAsset(getAssets(),"comicsans.ttf");
        caramain.setTypeface(type3);
        caramain1b.setTypeface(type3);
        caramain1c.setTypeface(type3);
        caramain1d.setTypeface(type3);
        caramain1e.setTypeface(type3);

        pindah = (ImageButton) findViewById(R.id.tombolBeranda2);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent = new Intent(CaraMain1Activity.this, PilihLevelActivity.class);
                startActivity(intent);
            }
        });

        pindah = (ImageButton) findViewById(R.id.tombolKeluar3);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent = new Intent(CaraMain1Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        pindah = (ImageButton) findViewById(R.id.tombolkeCaraMain2);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent = new Intent(CaraMain1Activity.this, CaraMain2Activity.class);
                startActivity(intent);
            }
        });

    }
}
