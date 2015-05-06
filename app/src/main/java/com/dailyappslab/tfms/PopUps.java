package com.dailyappslab.tfms;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.View;

/**
 * Created by GreenQ on 06.05.2015.
 */
public class PopUps extends Activity {
    Context context;

    public PopUps(Context context)
    {
        this.context = context;
    }

    public void ShowWindow(Class activity)
    {

        try {
            final Intent i = new Intent(context, activity);
            // Intent s = new Intent();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Intent i = new Intent(from, to);
                    startActivity(i);
                }
            }, 100);
        }
        catch(Exception ex)
        {
            Globals.DisplayAlert(context, ex.getMessage(), "Возникла ошибка");
        }
    }


}
