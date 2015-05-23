package com.dailyappslab.tfms;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by GreenQ on 27.04.2015.
 */
public class Preferences {
    SharedPreferences pLvl;
    SharedPreferences pGold;
    SharedPreferences pPackage;
    SharedPreferences pTickets;
    SharedPreferences pTicketsAtUsage;
    SharedPreferences pFirstTicketUsageTime;
    SharedPreferences pLastTicketUsageTime;

    public Preferences(Context context)
    {
        pLvl = context.getSharedPreferences("LVL", Context.MODE_PRIVATE);
        pGold = context.getSharedPreferences("GOLD", Context.MODE_PRIVATE);
        pPackage = context.getSharedPreferences("PACKAGE", Context.MODE_PRIVATE);
        pTickets = context.getSharedPreferences("TICKETS", Context.MODE_PRIVATE);
        pTicketsAtUsage = context.getSharedPreferences("TICKETS_AT_USAGE", Context.MODE_PRIVATE);
        pFirstTicketUsageTime = context.getSharedPreferences("TICKET_TIME", Context.MODE_PRIVATE);
        pLastTicketUsageTime = context.getSharedPreferences("LAST_TICKET_TIME", Context.MODE_PRIVATE);
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

    public int GetCurrentGold()
    {
        int temp = pGold.getInt("GOLD", 10) ;

        if (CheckGoldRange(temp))
            return temp;
        else
            return 10;
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

    public int GetTicketsAtLastUsage()
    {
        int temp = pTicketsAtUsage.getInt("TICKETS_AT_USAGE", 5) ;

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

    public long GetLastTicketUsageTime()
    {
        long temp = pLastTicketUsageTime.getLong("LAST_TICKET_TIME", 0) ;

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

    private boolean CheckGoldRange(int i)
    {
        if(i > -1 && i <99999)
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

    public void EditGold(int i)
    {
        if (!CheckGoldRange(i))
            return;
        SharedPreferences.Editor editor = pGold.edit();
        editor.putInt("GOLD", i);
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

    public void EditTicketsAtLastUsage(int i)
    {
        if (!CheckTicketsRange(i))
            return;
        SharedPreferences.Editor editor = pTicketsAtUsage.edit();
        editor.putInt("TICKETS_AT_USAGE", i);
        editor.commit();
    }

    public void EditTicketUsageTime(long time)
    {
        SharedPreferences.Editor editor = pFirstTicketUsageTime.edit();
        editor.putLong("TICKET_TIME", time);
        editor.commit();
    }

    public void EditLastTicketUsageTime(long time)
    {
        SharedPreferences.Editor editor = pLastTicketUsageTime.edit();
        editor.putLong("LAST_TICKET_TIME", time);
        editor.commit();
    }
    //endregion
}
