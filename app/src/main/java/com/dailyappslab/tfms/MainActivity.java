package com.dailyappslab.tfms;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

/**
 * Created by GreenQ on 05.05.2015.
 */
public class MainActivity extends ActionBarActivity {

    AnimationDrawable animation;
    ImageButton btnPlay;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //verridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
        setContentView(R.layout.main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        animation = (AnimationDrawable) btnPlay.getBackground();
        animation.setOneShot(true);

    }

    public void StartGame(View view)
    {
        animation.start();
        animation.stop();
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

    @Override
    public void onBackPressed() {
          super.onBackPressed();
    }
}
