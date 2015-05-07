package com.dailyappslab.tfms;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by GreenQ on 07.05.2015.
 */
public class TicketBuyActivity extends Activity {
    Button btnClose;
    TextView txtTime;
    Preferences preferences;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_market);
        preferences = new Preferences(this);
        btnClose = (Button) findViewById(R.id.btnClose);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CountTime();
    }

    public void CountTime()
    {
        txtTime = (TextView) findViewById( R.id.txtTime );
        new CountDownTimer(TicketsManager.GetTimeLeft(preferences.GetTicketUsageTime(),getResources().getConfiguration().locale, getBaseContext()), 1000) {

            public void onTick(long millisUntilFinished) {
                txtTime.setText(new SimpleDateFormat("mm:ss").format(new Date( millisUntilFinished)));
            }

            public void onFinish() {
                txtTime.setText("done!");
            }
        }.start();
    }

}
