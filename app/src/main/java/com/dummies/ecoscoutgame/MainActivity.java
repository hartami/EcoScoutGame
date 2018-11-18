package com.dummies.ecoscoutgame;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageButton pindah, keluar;
    MediaPlayer mp, suaraopening;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mp = MediaPlayer.create(this, R.raw.suarakliktombol);
        suaraopening = MediaPlayer.create(getBaseContext(), R.raw.suaraopening);
        suaraopening.start();

        keluar =(ImageButton) findViewById(R.id.tombolKeluar);
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        pindah = (ImageButton) findViewById(R.id.tombolMulaiMain);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraopening.stop();
                mp.start();
                Intent intent = new Intent(MainActivity.this,PilihLevelActivity.class);
                startActivity(intent);

            }
        });

        pindah = (ImageButton) findViewById(R.id.tombolCaraMainBesar);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaraopening.stop();
                mp.start();
                Intent intent = new Intent(MainActivity.this,CaraMain1Activity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        suaraopening.stop();
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }



}

