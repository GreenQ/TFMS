package com.dailyappslab.tfms;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by GreenQ on 06.05.2015.
 */
public class MarketActivity extends Activity {
    Button btnClose;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameshop);
        btnClose = (Button) findViewById(R.id.btnClose);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

//    public void CloseDialog(View view)
//    {
//        try {
//           onBackPressed();
//        }
//        catch (Exception ex)
//        {
//            Globals.DisplayAlert(this, ex.getMessage(), "Произошла непредвиденная ошибка");
//        }
//    }
}
