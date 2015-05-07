package com.dailyappslab.tfms;

import android.content.Context;
import android.provider.Settings;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by GreenQ on 07.05.2015.
 */
public class TicketsManager {

    public static long GetTimeLeft(long date, Locale locale, Context context) {

        try {
            Date currentDates = new Date(System.currentTimeMillis());
            Date dates = new Date(date);
            String currentDate = String.valueOf(currentDates);
            SimpleDateFormat df = new SimpleDateFormat("mm:ss", locale);

            Date dtCurrent = new Date(df.format(currentDates));
            // использовать dtCurrent.getTime - кол-во милисекунд, эквивалентных 1, 2 и 3 дням

            Date dtCompareWith = new Date(df.format(dates));

            return dtCurrent.getTime() - dtCompareWith.getTime();
        }
        catch (Exception ex)
        {
            Globals.DisplayAlert(context, ex.getMessage(), "Возникла ошибка");
        }
        return 0;
    }
}
