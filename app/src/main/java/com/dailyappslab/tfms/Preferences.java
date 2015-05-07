package com.dailyappslab.tfms;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by GreenQ on 27.04.2015.
 */
public class Preferences {
    SharedPreferences pLvl;
    SharedPreferences pPackage;
    SharedPreferences pTickets;
    SharedPreferences pFirstTicketUsageTime;

    public Preferences(Context context)
    {
        pLvl = context.getSharedPreferences("LVL", Context.MODE_PRIVATE);
        pPackage = context.getSharedPreferences("PACKAGE", Context.MODE_PRIVATE);
        pTickets = context.getSharedPreferences("TICKETS", Context.MODE_PRIVATE);
        pFirstTicketUsageTime = context.getSharedPreferences("TICKET_TIME", Context.MODE_PRIVATE);
    }

    //region #GET STORED PREFERENCES
    public int GetCurrentLevel()
    {
        int temp = pLvl.getInt("LVL", 1) ;

        if (CheckLevelRange(temp))
            return temp;
        else
            return 1;
    }

    public int GetCurrentPackage()
    {
        int temp = pPackage.getInt("PACKAGE", 1) ;

        if (CheckPackageRange(temp))
            return temp;
        else
            return 1;
    }

    public int GetCurrentTickets()
    {
        int temp = pTickets.getInt("TICKETS", 5) ;

        if (CheckTicketsRange(temp))
            return temp;
        else
            return 1;
    }

    public long GetTicketUsageTime()
    {
        long temp = pFirstTicketUsageTime.getLong("TICKET_TIME", 0) ;

       return temp;
    }

    //endregion

    //region #CHECK RANGES
    private boolean CheckLevelRange(int i)
    {
        if(i > 0 && i <11)
            return true;
        else
            return false;
    }

    private boolean CheckPackageRange(int i)
    {
        if(i > 0 && i <51)
            return true;
        else
            return false;
    }

    private boolean CheckTicketsRange(int i)
    {
        if(i > -1 && i <6)
            return true;
        else
            return false;
    }
    //endregion

    //region #EDIT STORED PREFERENCES
    public void EditLevel(int i)
    {
        if (!CheckLevelRange(i))
            return;
        SharedPreferences.Editor editor = pLvl.edit();
        editor.putInt("LVL", i);
        editor.commit();
    }

    public void EditPackage(int i)
    {
        if (!CheckPackageRange(i))
            return;
        SharedPreferences.Editor editor = pPackage.edit();
        editor.putInt("PACKAGE", i);
        editor.commit();
    }

    public void EditTickets(int i)
    {
        if (!CheckTicketsRange(i))
            return;
        SharedPreferences.Editor editor = pTickets.edit();
        editor.putInt("TICKETS", i);
        editor.commit();
    }

    public void EditTicketUsageTime(long time)
    {
        SharedPreferences.Editor editor = pFirstTicketUsageTime.edit();
        editor.putLong("TICKET_TIME", time);
        editor.commit();
    }
    //endregion
}
