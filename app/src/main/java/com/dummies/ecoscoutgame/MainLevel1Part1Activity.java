package com.dummies.ecoscoutgame;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainLevel1Part1Activity extends AppCompatActivity implements View.OnDragListener {

    ImageButton pindah;
    MediaPlayer mp, suaragesersampah, suaratongbenar, suaratongsalah, suaraending, suarakicauburung;
    ImageView sampahhijau1,sampahhijau2, sampahhijau3, sampahbiru1, sampahbiru2, sampahkuning1, sampahkuning2, sampahkuning3, sampahmerah1, sampahmerah2, karakterDapatScore, karakterTidakDapatTopi;
    LinearLayout droplayoutsampahhijau, droplayoutsampahbiru, droplayoutsampahkuning, droplayoutsampahmerah, droplayoutkosong1, droplayoutkosong2, droplayoutkosong3;
    int score;
    int time = 50;

    private long mStopTimeInFuture;
    private long mPauseTime;
    private boolean mCancelled = false;
    private boolean mPaused = false;

    TextView scoreCounter;
    TextView timeCounter;
    TextView textCongrats;
    TextView textCongrats2;
    TextView textScore;

    private static final String TAG = MainLevel1Part1Activity.class.getSimpleName();
    private static final String HIJAU_TAG = "HIJAU";
    private static final String BIRU_TAG = "BIRU";
    private static final String KUNING_TAG = "KUNING";
    private static final String MERAH_TAG = "MERAH";
    private static final String KOSONG_TAG = "KOSONG";

    public MainLevel1Part1Activity() {
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        suarakicauburung.stop();
                        suaraending.stop();

                        MainLevel1Part1Activity.this.finish();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_level1_part1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mp = MediaPlayer.create(this, R.raw.suarakliktombol);
        suaragesersampah = MediaPlayer.create(this, R.raw.suaragesersampah);
        suaratongbenar = MediaPlayer.create(this, R.raw.suarabenar);
        suaratongsalah = MediaPlayer.create(this, R.raw.suarasalah);
        suaraending = MediaPlayer.create(this, R.raw.suaraending);
        suarakicauburung = MediaPlayer.create(this, R.raw.suarakicauburung);
        suarakicauburung.start();

        findViews();
        implementEvents();

        // to count down time
        new CountDownTimer(50000, 1000) {

            public void onTick(long millisUntilFinished) {

                long s1 = millisUntilFinished;
                if (s1>0000){
                    time--;
                    timeCounter.setText("Time :  " + checkDigit(time) + " s");
                } else {
                    onFinish();
                }
            }

            public void onResume1() {
                onResume();
            }

            public void onPause1() {
                onPause();
            }

            public void onFinish() {
                timeCounter.setText("Time's up!");
                suarakicauburung.stop();
                suaraending.start();

                // to get the final score
                final int totalscore = score;

                // to set a popup dialog
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainLevel1Part1Activity.this);
                final View mView = getLayoutInflater().inflate(R.layout.popup_selamat, null);

                // to show score on the popup dialog
                textCongrats = (TextView)  mView.findViewById(R.id.teksSelamat);
                textCongrats2 = (TextView)  mView.findViewById(R.id.teksSelamat2);
                textScore = (TextView) mView.findViewById(R.id.finalScore);

                // to modify the fonts of Textview
                Typeface type2 = Typeface.createFromAsset(getAssets(),"comicsans.ttf");
                textCongrats.setTypeface(type2);
                textCongrats2.setTypeface(type2);
                textScore.setTypeface(type2);

                String finalScore = String.valueOf(totalscore);

                if (totalscore>=50){
                    textCongrats.setText("You got a hat because your score is");
                    textCongrats2.setVisibility(View.INVISIBLE);
                    textScore.setText(" "+finalScore+" ");
                    karakterDapatScore = (ImageView) mView.findViewById(R.id.karakterDapatTopi);
                    karakterTidakDapatTopi = (ImageView) mView.findViewById(R.id.karakterTidakDapatTopi);
                    karakterDapatScore.setVisibility(View.VISIBLE);
                    karakterTidakDapatTopi.setVisibility(View.INVISIBLE);
                } else {
                    textCongrats2.setText("Your score is");
                    textCongrats.setVisibility(View.INVISIBLE);
                    textScore.setText(" "+finalScore+" ");
                    karakterDapatScore = (ImageView) mView.findViewById(R.id.karakterDapatTopi);
                    karakterTidakDapatTopi = (ImageView) mView.findViewById(R.id.karakterTidakDapatTopi);
                    karakterDapatScore.setVisibility(View.INVISIBLE);
                    karakterTidakDapatTopi.setVisibility(View.VISIBLE);
                }

                // go to the next level
                ImageButton buttonNextLevel = (ImageButton) mView.findViewById(R.id.tombolNextLevel);
                buttonNextLevel.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // buttonNextLevel has been clicked
                        mp.start();
                        Intent intent = new Intent(MainLevel1Part1Activity.this, PilihLevelActivity.class);
                        startActivity(intent);
                    }
                });

                ImageButton buttonReplay = (ImageButton) mView.findViewById(R.id.tombolReplay);
                buttonReplay.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // buttonReplay has been clicked
                        mp.start();
                        Intent intent = new Intent(MainLevel1Part1Activity.this, Opening1Level1Activity.class);
                        startActivity(intent);
                    }
                });

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                suaraending.stop();





            }

        }.start();

        // to go back to home page
        pindah = (ImageButton) findViewById(R.id.tombolBeranda);
        pindah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suarakicauburung.stop();
                suaraending.stop();
                mp.start();
                
                Intent intent = new Intent(MainLevel1Part1Activity.this, PilihLevelActivity.class);
                startActivity(intent);
            }
        });


    }

   

    private void implementEvents() {
        sampahhijau1.setOnTouchListener(new ChoiceTouchListener());
        sampahhijau2.setOnTouchListener(new ChoiceTouchListener());
        sampahhijau3.setOnTouchListener(new ChoiceTouchListener());
        sampahbiru1.setOnTouchListener(new ChoiceTouchListener());
        sampahbiru2.setOnTouchListener(new ChoiceTouchListener());
        sampahkuning1.setOnTouchListener(new ChoiceTouchListener());
        sampahkuning2.setOnTouchListener(new ChoiceTouchListener());
        sampahkuning3.setOnTouchListener(new ChoiceTouchListener());
        sampahmerah1.setOnTouchListener(new ChoiceTouchListener());
        sampahmerah2.setOnTouchListener(new ChoiceTouchListener());

        droplayoutsampahkuning.setOnDragListener(this);
        droplayoutsampahhijau.setOnDragListener(this);
        droplayoutsampahbiru.setOnDragListener(this);
        droplayoutsampahmerah.setOnDragListener(this);
        droplayoutkosong1.setOnDragListener(this);
        droplayoutkosong2.setOnDragListener(this);
        droplayoutkosong3.setOnDragListener(this);

    }

    private void findViews() {

        // to modify the fonts of Textview
        Typeface type = Typeface.createFromAsset(getAssets(),"comicsans.ttf");
        scoreCounter = (TextView)  findViewById(R.id.scoreCounter);
        scoreCounter.setTypeface(type);
        timeCounter = (TextView)  findViewById(R.id.timer);
        timeCounter.setTypeface(type);


        sampahhijau1 = (ImageView) findViewById(R.id.sampahHijau1);
        sampahhijau1.setTag(HIJAU_TAG);
        sampahhijau2 = (ImageView) findViewById(R.id.sampahHijau2);
        sampahhijau2.setTag(HIJAU_TAG);
        sampahhijau3 = (ImageView) findViewById(R.id.sampahHijau3);
        sampahhijau3.setTag(HIJAU_TAG);
        sampahbiru1 = (ImageView) findViewById(R.id.sampahBiru1);
        sampahbiru1.setTag(BIRU_TAG);
        sampahbiru2 = (ImageView) findViewById(R.id.sampahBiru2);
        sampahbiru2.setTag(BIRU_TAG);
        sampahkuning1 = (ImageView) findViewById(R.id.sampahKuning1);
        sampahkuning1.setTag(KUNING_TAG);
        sampahkuning2 = (ImageView) findViewById(R.id.sampahKuning2);
        sampahkuning2.setTag(KUNING_TAG);
        sampahkuning3 = (ImageView) findViewById(R.id.sampahKuning3);
        sampahkuning3.setTag(KUNING_TAG);
        sampahmerah1 = (ImageView) findViewById(R.id.sampahMerah1);
        sampahmerah1.setTag(MERAH_TAG);
        sampahmerah2 = (ImageView) findViewById(R.id.sampahMerah2);
        sampahmerah2.setTag(MERAH_TAG);

        droplayoutsampahkuning = (LinearLayout) findViewById(R.id.layoutTongSampahKuning);
        droplayoutsampahkuning.setTag(KUNING_TAG);
        droplayoutsampahhijau = (LinearLayout) findViewById(R.id.layoutTongSampahHijau);
        droplayoutsampahhijau.setTag(HIJAU_TAG);
        droplayoutsampahbiru = (LinearLayout) findViewById(R.id.layoutTongSampahBiru);
        droplayoutsampahbiru.setTag(BIRU_TAG);
        droplayoutsampahmerah = (LinearLayout) findViewById(R.id.layoutTongSampahMerah);
        droplayoutsampahmerah.setTag(MERAH_TAG);

        droplayoutkosong1 = (LinearLayout) findViewById(R.id.layoutKosong1);
        droplayoutkosong1.setTag(KOSONG_TAG);
        droplayoutkosong2 = (LinearLayout) findViewById(R.id.layoutKosong2);
        droplayoutkosong2.setTag(KOSONG_TAG);
        droplayoutkosong3 = (LinearLayout) findViewById(R.id.layoutKosong3);
        droplayoutkosong3.setTag(KOSONG_TAG);
    }


    private final class ChoiceTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);

                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.VISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean onDrag(View view, DragEvent event) {
        int action = event.getAction();

        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:

                if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    suaragesersampah.start();
                    return true;
                }
                return false;
            case DragEvent.ACTION_DRAG_ENTERED:
                return true;
            case DragEvent.ACTION_DRAG_LOCATION:



                return true;
            case DragEvent.ACTION_DRAG_EXITED:
                View v2 = (View) event.getLocalState();
                v2.setVisibility(View.VISIBLE);
                return true;
            case DragEvent.ACTION_DROP:

                ClipData.Item item = event.getClipData().getItemAt(0);
                String dragData = item.getText().toString();
                //Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();
                View v = (View) event.getLocalState();
                ViewGroup owner = (ViewGroup) v.getParent();
                owner.removeView(v);//remove the dragged view
                LinearLayout container = (LinearLayout) view;//caste the view into LinearLayout as our drag acceptable layout is LinearLayout
                container.addView(v);//Add the dragged view
                v.setVisibility(View.VISIBLE);


                String labelTong = (String) container.getTag();
                if (labelTong.equals(dragData)) {
                    score += 10;

                    //to show Score on TextView
                    String scr = String.valueOf(score);
                    scoreCounter.setText("Score : "+scr+" ");

                    suaratongbenar.start();
                    Toast.makeText(this, "A right bin! Thank you! You got 10 points.", Toast.LENGTH_SHORT).show();
                }
                else {
                        if (labelTong.equals("KOSONG")){
                            score += 0;
                            String scr = String.valueOf(score);
                            scoreCounter.setText("Score : "+scr+" ");
                        }
                        else {
                            score += 1;
                            //to show Score on TextView
                            String scr = String.valueOf(score);
                            scoreCounter.setText("Score : "+scr+" ");
                            suaratongsalah.start();
                            Toast.makeText(this, "Oh, a wrong trash bin! You got 1 point", Toast.LENGTH_SHORT).show();

                        }
                            }

                // Returns true. DragEvent.getResult() will return true.
                return true;

            case DragEvent.ACTION_DRAG_ENDED:

                // returns true; the value is ignored.
                return true;


            default:
                Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
                break;
        }
        return false;
    }

    // to call checkDigit method for counting down time
    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

}


// Does a getResult(), and displays what happened.
               /* if (event.getResult())
                        {
                        break;
                        }
                        else {
                        Toast.makeText(this, "Coba Lagi!", Toast.LENGTH_SHORT).show();
                        int x_cord = (int) event.getX();
                        int y_cord = (int) event.getY();
                        View v2 = (View) event.getLocalState();
                        v2.setVisibility(View.VISIBLE);
                        }
                        // returns true; the value is ignored.
                        return true;





               */