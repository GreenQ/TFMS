package com.dailyappslab.tfms;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by GreenQ on 05.05.2015.
 */
public class MainActivity extends ActionBarActivity {
  //обявлення анімації
    private AnimationDrawable mAnimationDrawable;

    ImageButton btnPlay;
    TextView txtTickets;
    TextView txtGold;
    Preferences preferences;
    CountDownTimer globalTimer;
    long timeLeft;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //verridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
        setContentView(R.layout.main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        preferences = new Preferences(this);
        btnPlay = (ImageButton) findViewById(R.id.btnPlay);

        txtTickets = (TextView) findViewById(R.id.txtTickets);
        txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");

        txtGold = (TextView) findViewById(R.id.txtGold);



        GetTicketsDueTime();
    }

    public void StartGame(View view)
    {

        //початок анімації
        ImageView imageView = (ImageView) findViewById(R.id.btnPlay);
        imageView.setBackgroundResource(R.drawable.btnanim);

        mAnimationDrawable = (AnimationDrawable) imageView.getBackground();

        mAnimationDrawable.start();
        mAnimationDrawable.run();



        //кінець анімації

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(MainActivity.this, PackageActivity.class);
                startActivity(i);

               // overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
                //finish();
            }
        }, 300);
    }

    public void StartGameshop(View view)
    {
        Intent i = new Intent(MainActivity.this, MarketActivity.class);
        startActivity(i);
    }

    public void CountTime(String instruction)
    {
        //txtTime = (TextView) findViewById( R.id.txtTime );
        switch (instruction) {
            case "new":
                globalTimer = new CountDownTimer(420000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        Globals.TimeLeft = millisUntilFinished;

                        //txtTime.setText(new SimpleDateFormat("mm:ss").format(new Date( millisUntilFinished)));
                        // if(((millisUntilFinished) % 420000) < 1000)
                        //{
//                    preferences.EditTickets(preferences.GetCurrentTickets() + 1);
//                    txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                        // FixTicketsAmount();
                        //}
                        timeLeft = millisUntilFinished;
                    }

                    public void onFinish() {
                        if (preferences.GetCurrentTickets() < 5) {
                            preferences.EditTickets(preferences.GetCurrentTickets() + 1);
                            txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                            if (preferences.GetCurrentTickets() < 5)
                                CountTime("continue");
                            //txtTime.setText("");
                        }
                    }
                }.start();
                break;
            case "continue":
                try {
                    globalTimer.cancel();
                }
                catch(Exception ex)
                {}
                globalTimer = new CountDownTimer(420000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        //txtTime.setText(new SimpleDateFormat("mm:ss").format(new Date( millisUntilFinished)));
                        Globals.TimeLeft = millisUntilFinished;

                        if(((millisUntilFinished) % 420000) < 1000)
                        {
                            preferences.EditTickets(preferences.GetCurrentTickets() + 1);
                            txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                            // FixTicketsAmount();
                            //}
                            timeLeft = millisUntilFinished;
                        }
                    }

                    public void onFinish() {
                        if(preferences.GetCurrentTickets() < 5)
                        {
                            preferences.EditTickets(preferences.GetCurrentTickets() + 1);
                            txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                            if (preferences.GetCurrentTickets() < 5)
                                CountTime("continue");
                            //txtTime.setText("");
                        }
                    }
                }.start();
                break;
        }
    }

    public void GetTicketsDueTime()
    {
        long lastTicketUsage = preferences.GetTicketUsageTime();
        long currentTime = System.currentTimeMillis();

        long differenceTime = currentTime - lastTicketUsage;

        long restTime = differenceTime % 420000;

        long moduledTime = differenceTime - restTime;

        int possibleTickets = (int) (moduledTime/420000);


        int temp = preferences.GetCurrentTickets() + possibleTickets;
        Toast.makeText(getBaseContext(), String.valueOf(temp), Toast.LENGTH_SHORT).show();

        if(temp < 5)
        {
            preferences.EditTickets(temp);
            txtTickets.setText(String.valueOf(temp) + "/5");

            globalTimer = new CountDownTimer(restTime, 1000) {

                public void onTick(long millisUntilFinished) {
                    Globals.TimeLeft = millisUntilFinished;

                    //txtTime.setText(new SimpleDateFormat("mm:ss").format(new Date( millisUntilFinished)));
                    // if(((millisUntilFinished) % 420000) < 1000)
                    //{
//                    preferences.EditTickets(preferences.GetCurrentTickets() + 1);
//                    txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                    // FixTicketsAmount();
                    //}
                    timeLeft = millisUntilFinished;
                }

                public void onFinish() {
                    if (preferences.GetCurrentTickets() < 6) {
                        preferences.EditTickets(preferences.GetCurrentTickets() + 1);
                        txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                        if (preferences.GetCurrentTickets() < 5)
                            CountTime("continue");
                        //txtTime.setText("");
                    }
                }
            }.start();
        }
        else {
            preferences.EditTickets(5);
            txtTickets.setText(String.valueOf(5) + "/5");
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
    }


    @Override
    public void onBackPressed() {
          super.onBackPressed();
    }
}
