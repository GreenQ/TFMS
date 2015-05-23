package com.dailyappslab.tfms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by GreenQ on 23.05.2015.
 */
public class AboutUsActivity extends Activity {
    TextView txtGold;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Preferences preferences = new Preferences(this);

    }

    public void StartGameshop(View view)
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(AboutUsActivity.this, MarketActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
            }
        }, 220);
    }

    public void GoBack(View view)
    {
        finish();
    }
}
