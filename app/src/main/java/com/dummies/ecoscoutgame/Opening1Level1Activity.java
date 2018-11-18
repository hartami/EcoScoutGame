package com.dummies.ecoscoutgame;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Opening1Level1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening1_level1);

        TextView textHallo;
        textHallo = (TextView)  findViewById(R.id.textHallo);
        Typeface type = Typeface.createFromAsset(getAssets(),"comicsans.ttf");
        textHallo.setTypeface(type);

        Thread thread = new Thread(){
            public void run(){
                try{
                    sleep(5000);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // go to Splash Opening 2
                    startActivity(new Intent(Opening1Level1Activity.this, Opening2Level1Activity.class));
                    finish();
                }
            }
        };
        thread.start();
    }
}
