package com.dailyappslab.tfms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
        txtGold.findViewById(R.id.txtGold);
        txtGold.setText(String.valueOf(preferences.GetCurrentGold()));
    }

    public void StartGameshop(View view)
    {
        Intent i = new Intent(AboutUsActivity.this, MarketActivity.class);
        startActivity(i);
    }

    public void GoBack(View view)
    {
        finish();
    }
}
