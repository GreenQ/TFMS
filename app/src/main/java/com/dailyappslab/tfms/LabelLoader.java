package com.dailyappslab.tfms;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.WindowManager;
import android.widget.ImageView;


/**
 * Created by GreenQ on 05.05.2015.
 */
public class LabelLoader extends ActionBarActivity {

    private AnimationDrawable mAnimationDrawable;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
        setContentView(R.layout.loadlabel);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        ImageView imageView = (ImageView) findViewById(R.id.loadlabel);
        imageView.setBackgroundResource(R.drawable.loadanim);

        mAnimationDrawable = (AnimationDrawable) imageView.getBackground();

        mAnimationDrawable.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(LabelLoader.this, LoadActivity.class);
                startActivity(i);

                //overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
                finish();
            }
        }, 1500);
    }
}
