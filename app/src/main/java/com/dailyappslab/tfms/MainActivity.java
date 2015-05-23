package com.dailyappslab.tfms;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.startad.lib.SADView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by GreenQ on 05.05.2015.
 */
public class MainActivity extends ActionBarActivity {
  //обявлення анімації
    private AnimationDrawable mAnimationDrawable;

    ImageButton btnPlay;
    public static TextView txtTickets;
    public static TextView txtGold;
    static Preferences preferences;
    public static CountDownTimer globalTimer;
    static long timeLeft;
    SADView sadView;

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
        txtGold.setText(String.valueOf(preferences.GetCurrentGold()));



        //GetTicketsDueTime();
        //Globals.preferences = preferences;
        GetTicketsDueTime();

        try
        {
            sadView = new SADView(this, "555f43a15b7f5a2100000000");
            LinearLayout layout = (LinearLayout)findViewById(R.id.admob);

            // Add the adView to it
            layout.addView(this.sadView);
            sadView.loadAd(SADView.LANGUAGE_RU);
        }
        catch (Exception ex) {
            Globals.DisplayAlert(this, ex.getMessage(), "Error occured");
        }
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

    public void StartAboutUs(View view)
    {
        Intent i = new Intent(MainActivity.this, AboutUsActivity.class);
        startActivity(i);
    }

    public void StartTicketShop(View view)
    {
        Intent i = new Intent(MainActivity.this, TicketBuyActivity.class);
        startActivity(i);
    }

    public static void CountTime(String instruction)
    {
        //txtTime = (TextView) findViewById( R.id.txtTime );
        switch (instruction) {
            case "new":
                globalTimer = new CountDownTimer(420000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        // txtTime.setText(new SimpleDateFormat("mm:ss").format(new Date( millisUntilFinished)));
                        // if(((millisUntilFinished) % 15000) < 1000)
                        //{
//                    preferences.EditTickets(preferences.GetCurrentTickets() + 1);
//                    txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                        // FixTicketsAmount();
                        //}
                        try {
                            if(TicketBuyActivity.txtTime !=null)
                                TicketBuyActivity.txtTime.setText(new SimpleDateFormat("mm:ss").format(new Date(millisUntilFinished)));
                        }
                        catch (Exception ex){}
                        timeLeft = millisUntilFinished;
                    }

                    public void onFinish() {
                        if (preferences.GetCurrentTickets() < 6) {
                            preferences.EditTickets(preferences.GetCurrentTickets() + 1);try {
                                MainActivity.txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                                PackageActivity.txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                                TicketBuyActivity.txtTickets.setText(String.valueOf(preferences.GetCurrentTickets() + "/5"));
                                TicketBuyActivity.txtTime.setText("");
                            }
                            catch (Exception ex){
                                Log.d("TimerError: ", ex.getMessage());}
                            if (preferences.GetCurrentTickets() < 5)
                                CountTime("continue");
                            else
                                globalTimer = null;
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
//                        if(((millisUntilFinished) % 15000) < 1000)
//                        {
//                            preferences.EditTickets(preferences.GetCurrentTickets() + 1);
//                            txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
//                            // FixTicketsAmount();
//                            //}
                        try {
                            if(TicketBuyActivity.txtTime !=null)
                                TicketBuyActivity.txtTime.setText(new SimpleDateFormat("mm:ss").format(new Date(millisUntilFinished)));
                        }
                        catch (Exception ex){}
                        timeLeft = millisUntilFinished;
//                        }
                    }

                    public void onFinish() {
                        if(preferences.GetCurrentTickets() < 6)
                        {
                            preferences.EditTickets(preferences.GetCurrentTickets() + 1);
                            try {
                                MainActivity.txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                                PackageActivity.txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                                TicketBuyActivity.txtTickets.setText(String.valueOf(preferences.GetCurrentTickets() + "/5"));
                                TicketBuyActivity.txtTime.setText("");
                            }
                            catch (Exception ex){
                                Log.d("TimerError: ", ex.getMessage());
                            }
                            if (preferences.GetCurrentTickets() < 5)
                                CountTime("continue");
                            else
                                globalTimer = null;
                            //txtTime.setText("");
                        }
                    }
                }.start();
                break;
        }
    }

    public static void GetTicketsDueTime()
    {
        long lastTicketUsage = preferences.GetLastTicketUsageTime();
        long currentTime = System.currentTimeMillis();

        long differenceTime = currentTime - lastTicketUsage;

        long restTime = differenceTime % 420000;

        long moduledTime = differenceTime - restTime;

        int possibleTickets = (int) (moduledTime/420000);


        int temp = preferences.GetTicketsAtLastUsage() + possibleTickets;
        //Toast.makeText(getBaseContext(), String.valueOf(temp), Toast.LENGTH_SHORT).show();

        if(temp < 5)
        {
            preferences.EditTickets(temp);
            //txtTickets.setText(String.valueOf(temp));

            globalTimer = new CountDownTimer((420000-restTime), 1000) {

                public void onTick(long millisUntilFinished) {
                    try {
                        if(TicketBuyActivity.txtTime !=null)
                        TicketBuyActivity.txtTime.setText(new SimpleDateFormat("mm:ss").format(new Date(millisUntilFinished)));
                    }
                    catch (Exception ex){}
                    // if(((millisUntilFinished) % 15000) < 1000)
                    //{
//                    preferences.EditTickets(preferences.GetCurrentTickets() + 1);
//                    txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                    // FixTicketsAmount();
                    //}
                    timeLeft = millisUntilFinished;
                }

                public void onFinish() {
                    if (preferences.GetCurrentTickets() < 6) {
                        preferences.EditTickets(preferences.GetCurrentTickets() + 1);try {
                            MainActivity.txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                            PackageActivity.txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                            TicketBuyActivity.txtTickets.setText(String.valueOf(preferences.GetCurrentTickets() + "/5"));
                            TicketBuyActivity.txtTime.setText("");
                        }
                        catch (Exception ex){}
                        if (preferences.GetCurrentTickets() < 5)
                            CountTime("continue");
                        //txtTime.setText("");
                    }
                }
            }.start();
        }
        else {
            preferences.EditTickets(5);
            try {
                MainActivity.txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                PackageActivity.txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                TicketBuyActivity.txtTickets.setText(String.valueOf(preferences.GetCurrentTickets() + "/5"));
                TicketBuyActivity.txtTime.setText("");
            }
            catch (Exception ex){
                Log.d("TimerError: ", ex.getMessage());}
        }
    }


    protected void onResume()
    {
        super.onResume();
        //GetDelayedTickets();
        txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");

    }

    public void GetDelayedTickets()
    {
        long lastTicketUsage = preferences.GetTicketUsageTime();
        long currentTime = System.currentTimeMillis();

        long differenceTime = currentTime - lastTicketUsage;

        long restTime = differenceTime % 30000;

        long moduledTime = differenceTime - restTime;

        int possibleTickets = (int) (moduledTime/30000);


        int temp = preferences.GetCurrentTickets() + possibleTickets;
        Toast.makeText(getBaseContext(), String.valueOf(temp), Toast.LENGTH_SHORT).show();

        if(temp < 5)
        {
            preferences.EditTickets(temp);
            txtTickets.setText(String.valueOf(temp) + "/5");

        }

        else {
            preferences.EditTickets(5);
            txtTickets.setText(String.valueOf(5) + "/5");
        }
    }

    @Override
    public void onBackPressed() {
          super.onBackPressed();
    }
}
