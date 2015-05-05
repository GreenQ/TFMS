package com.dailyappslab.tfms;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.WindowManager;

/**
 * Created by GreenQ on 05.05.2015.
 */
public class LoadActivity extends ActionBarActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
        setContentView(R.layout.load);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(LoadActivity.this, MainActivity.class);
                startActivity(i);

                //overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
                finish();
            }
        }, 1500);
    }
}
