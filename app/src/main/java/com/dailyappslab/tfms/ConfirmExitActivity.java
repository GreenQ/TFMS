package com.dailyappslab.tfms;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by GreenQ on 23.05.2015.
 */
public class ConfirmExitActivity extends Activity{
   ImageView imgVConfirm;
   ImageView imgVCancel;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_close);
        imgVCancel = (ImageView) findViewById(R.id.imgVCancel);
        imgVCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgVConfirm = (ImageView) findViewById(R.id.imgVConfirm);
        imgVConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity(1);
            }
        });
    }
}
