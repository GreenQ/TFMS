package com.dailyappslab.tfms;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by GreenQ on 05.05.2015.
 */
public class MainActivity extends ActionBarActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //verridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
        setContentView(R.layout.main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


    }

    public void StartGame(View view)
    {
        Intent i = new Intent(MainActivity.this, PackageActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
          super.onBackPressed();
    }
}
