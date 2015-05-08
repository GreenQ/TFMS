package com.dailyappslab.tfms;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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
