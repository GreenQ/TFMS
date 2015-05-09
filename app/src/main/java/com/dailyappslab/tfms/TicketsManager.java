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

    public static int GetAmountOfTicketsDueToTime(long date, Context context) {
        Date currentDates = new Date(System.currentTimeMillis());
        Date dates = new Date(date);
        int tickets = 0;
        Date tempDate = new Date(dates.getTime());

        if (tempDate.getTime() <= currentDates.getTime())
        {
            while (tempDate.getTime() <= currentDates.getTime()) {
                //if (tempDate.getTime() < currentDates.getTime()) {
                    tickets++;
                    tempDate.setTime(tempDate.getTime() + 15000);
                //}
            }
    }
        Toast.makeText(context, "am of t " + tickets, Toast.LENGTH_SHORT);
        return tickets;

    }

    public static long GetTimeLeft(long date, Locale locale, Context context, int tickets) {

        try {
            Date currentDates = new Date(System.currentTimeMillis());
            Date dates = new Date(date);
//            String currentDate = String.valueOf(currentDates);
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);

           // Date dtCurrent = new Date(df.format(currentDates));
            // использовать dtCurrent.getTime - кол-во милисекунд, эквивалентных 1, 2 и 3 дням

            //new Date()
            //Date dtCompareWith = new Date(df.format(dates));

            Date delayFor1Ticket = new Date();
            delayFor1Ticket.setTime(dates.getTime()+15000);
            Date delayFor2Ticket = new Date();
            delayFor2Ticket.setTime(dates.getTime()+15000*2);
            Date delayFor3Ticket = new Date();
            delayFor3Ticket.setTime(dates.getTime()+ 15000*3);
            Date delayFor4Ticket = new Date();
            delayFor4Ticket.setTime(dates.getTime()+ 15000*4);
            Date delayFor5Ticket = new Date();
            delayFor5Ticket.setTime(dates.getTime()+ 15000*5);

            switch (tickets){
                case 0:
                    return delayFor5Ticket.getTime() - currentDates.getTime();
                case 1:
                    return delayFor4Ticket.getTime() - currentDates.getTime();
                case 2:
                    return delayFor3Ticket.getTime() - currentDates.getTime();
                case 3:
                    return delayFor2Ticket.getTime() - currentDates.getTime();
                case 4:
                    return delayFor1Ticket.getTime() - currentDates.getTime();
                case 5:
                    return 0;
            }
            //return currentDates.getTime() - dates.getTime();
        }
        catch (Exception ex)
        {
            Globals.DisplayAlert(context, ex.getMessage(), "Возникла ошибка");
        }
        return 0;
    }
}
