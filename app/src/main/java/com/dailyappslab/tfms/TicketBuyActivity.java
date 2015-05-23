package com.dailyappslab.tfms;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;

/**
 * Created by GreenQ on 07.05.2015.
 */
public class TicketBuyActivity extends Activity {
    CountDownTimer countDownTimer;
    CountDownTimer globalTimer;
    Button btnClose;
    public static TextView txtTime;
    public static TextView txtTickets;
    Preferences preferences;
    RelativeLayout rltvTicket1;
    RelativeLayout rltvTicket2;
    RelativeLayout rltvTicket5;
    long timeLeft;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_market);

//        String fontPath = "fonts/Paint Peel Initials.ttf";
//
//        TextView text = (TextView) findViewById(R.id.txtQuestion);
//
//        // Font Face
//        Typeface typeface = Typeface.createFromAsset(getAssets(), fontPath);
//
//        // Applying font
//        text.setTypeface(typeface);
        
        preferences = new Preferences(this);
        btnClose = (Button) findViewById(R.id.btnClose);

        rltvTicket1 = (RelativeLayout) findViewById(R.id.rltvTicket1);
        rltvTicket1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(preferences.GetCurrentGold() >=10) {
                    ConfirmTicketBuyActivity.TicketsToBuy = 1;
                    ConfirmTicketBuyActivity.Message = "Вы уверены, что хотите приобрести 1 билет?";
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(TicketBuyActivity.this, ConfirmTicketBuyActivity.class);
                            startActivity(i);
                            overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
                        }
                    }, 220);
                }
                else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(TicketBuyActivity.this, MarketActivity.class);
                            startActivity(i);
                            overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
                        }
                    }, 220);
                }
            }
        });
        rltvTicket2 = (RelativeLayout) findViewById(R.id.rltvTicket2);
        rltvTicket2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(preferences.GetCurrentGold() >= 20)
                {
                ConfirmTicketBuyActivity.TicketsToBuy = 2;
                ConfirmTicketBuyActivity.Message = "Вы уверены, что хотите приобрести 2 билета?";
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(TicketBuyActivity.this, ConfirmTicketBuyActivity.class);
                            startActivity(i);
                            overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
                        }
                    }, 220);
                }
                else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(TicketBuyActivity.this, MarketActivity.class);
                            startActivity(i);
                            overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
                        }
                    }, 220);
                }
            }
        });
        rltvTicket5 = (RelativeLayout) findViewById(R.id.rltvTicket5);
        rltvTicket5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(preferences.GetCurrentGold() >= 50) {
                    ConfirmTicketBuyActivity.TicketsToBuy = 5;
                    ConfirmTicketBuyActivity.Message = "Вы уверены, что хотите приобрести 5 билетов?";
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(TicketBuyActivity.this, ConfirmTicketBuyActivity.class);
                            startActivity(i);
                            overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
                        }
                    }, 220);}
                else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(TicketBuyActivity.this, MarketActivity.class);
                            startActivity(i);
                            overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
                        }
                    }, 220);
                }

            }
        });
//
        txtTickets  = (TextView) findViewById(R.id.txtTickets);
        txtTime = (TextView) findViewById(R.id.txtTime);
        txtTime.setText("");
        txtTickets.setText(preferences.GetCurrentTickets() + "/5");
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //globalTimer.cancel();
                finish();
            }
        });

        //GetTicketsDueTime();


    }

    public void CountTime(String instruction)
    {
        //txtTime = (TextView) findViewById( R.id.txtTime );
        switch (instruction) {
            case "new":
                globalTimer = new CountDownTimer(15000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        txtTime.setText(new SimpleDateFormat("mm:ss").format(new Date( millisUntilFinished)));
                        if(((millisUntilFinished) % 15000) < 1000)
                        {
//                    preferences.EditTickets(preferences.GetCurrentTickets() + 1);
//                    txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                        // FixTicketsAmount();
                        }
                        timeLeft = millisUntilFinished;
                    }

                    public void onFinish() {
                        if (preferences.GetCurrentTickets() < 5) {
                            //preferences.EditTickets(preferences.GetCurrentTickets() + 1);
                            //txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                            GetDelayedTickets();
                            if (preferences.GetCurrentTickets() < 5)
                                CountTime("continue");
                            txtTime.setText("");
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
                globalTimer = new CountDownTimer(15000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        txtTime.setText(new SimpleDateFormat("mm:ss").format(new Date( millisUntilFinished)));
                        if(((millisUntilFinished) % 15000) < 5000)
                        {
                            //preferences.EditTickets(preferences.GetCurrentTickets() + 1);
                            txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                            // FixTicketsAmount();
                            //}
                            timeLeft = millisUntilFinished;
                        }
                    }

                    public void onFinish() {
                        if(preferences.GetCurrentTickets() < 5)
                        {
                            //preferences.EditTickets(preferences.GetCurrentTickets() + 1);
                            //txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                            GetDelayedTickets();
                            if (preferences.GetCurrentTickets() < 5)
                                CountTime("continue");
                            txtTime.setText("");
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

        long restTime = differenceTime % 15000;

        long moduledTime = differenceTime - restTime;

        int possibleTickets = (int) (moduledTime/15000);


        int temp = preferences.GetCurrentTickets() + possibleTickets;
        Toast.makeText(getBaseContext(), String.valueOf(temp), Toast.LENGTH_SHORT).show();

        if(temp < 5)
        {
            //preferences.EditTickets(temp);
            txtTickets.setText(String.valueOf(temp) + "/5");

            globalTimer = new CountDownTimer(15000 - restTime, 1000) {

                public void onTick(long millisUntilFinished) {
                    txtTime.setText(new SimpleDateFormat("mm:ss").format(new Date( millisUntilFinished)));
                     if(((millisUntilFinished) % 15000) < 2000)
                    {
//                    preferences.EditTickets(preferences.GetCurrentTickets() + 1);
                    txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                    // FixTicketsAmount();
                    }
                    timeLeft = millisUntilFinished;
                }

                public void onFinish() {
                    if (preferences.GetCurrentTickets() < 6) {
                        //preferences.EditTickets(preferences.GetCurrentTickets() + 1);
                        //txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                        GetDelayedTickets();
                        if (preferences.GetCurrentTickets() < 5)
                            CountTime("continue");
                        txtTime.setText("");
                    }
                }
            }.start();
        }
        else {
            preferences.EditTickets(5);
            txtTickets.setText(String.valueOf(5) + "/5");
        }
    }

    public void GetDelayedTickets()
    {
        long lastTicketUsage = preferences.GetTicketUsageTime();
        long currentTime = System.currentTimeMillis();

        long differenceTime = currentTime - lastTicketUsage;

        long restTime = differenceTime % 15000;

        long moduledTime = differenceTime - restTime;

        int possibleTickets = (int) (moduledTime/15000);


        int temp = preferences.GetCurrentTickets() + possibleTickets;
        Toast.makeText(getBaseContext(), String.valueOf(temp), Toast.LENGTH_SHORT).show();

        if(temp < 5)
        {
            txtTickets.setText(String.valueOf(temp) + "/5");
        }
        else {
            txtTickets.setText(String.valueOf(5) + "/5");
        }
    }


    public void FixTicketsAmount()
    {
        long timeleft = TicketsManager.GetTimeLeft(preferences.GetTicketUsageTime(),getResources().getConfiguration().locale, getBaseContext(), preferences.GetCurrentTickets());
        if(timeleft <= 0)
            preferences.EditTickets(5);
        else
        if(timeleft> 0 && timeleft <15000)
            preferences.EditTickets(4);
        else if(timeleft > 15001 && timeleft < 30000)
            preferences.EditTickets(3);
        else if(timeleft> 30001 && timeleft < 45000)
            preferences.EditTickets(2);
        else if(timeleft> 45001 && timeleft < 60000)
            preferences.EditTickets(1);
        else if(timeleft> 60001 && timeleft < 75000)
            preferences.EditTickets(0);

        txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
    }

    public void Buy1Ticket(View view)
    {

    }
    @Override
    public void onBackPressed()
    {
        globalTimer.cancel();
        finish();
    }

}
