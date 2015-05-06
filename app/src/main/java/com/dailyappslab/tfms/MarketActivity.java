package com.dailyappslab.tfms;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by GreenQ on 06.05.2015.
 */
public class MarketActivity extends Activity {
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameshop);
    }

    public void CloseDialog(View view)
    {
        finish();
    }
}
