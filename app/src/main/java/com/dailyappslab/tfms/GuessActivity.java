package com.dailyappslab.tfms;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by GreenQ on 06.05.2015.
 */
public class GuessActivity extends Activity {

    TextView txtIsCorrect;
    TextView txtFact;
    protected void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fact);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        txtIsCorrect = (TextView) findViewById(R.id.txtIsCorrect);
        txtFact = (TextView) findViewById(R.id.txtFact);

    }

    //private void SetData


}


