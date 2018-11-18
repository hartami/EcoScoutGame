package com.dummies.ecoscoutgame;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Opening2Level1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening2_level1);

        TextView textHallo2;
        textHallo2 = (TextView)  findViewById(R.id.textHallo2);
        Typeface type = Typeface.createFromAsset(getAssets(),"comicsans.ttf");
        textHallo2.setTypeface(type);


        Thread thread = new Thread(){
            public void run(){
                try{
                    sleep(7000);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // go to Level1
                    startActivity(new Intent(Opening2Level1Activity.this, MainLevel1Part1Activity.class));
                    finish();
                }
            }
        };
        thread.start();
    }
}
