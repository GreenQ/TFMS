package com.dailyappslab.tfms;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by GreenQ on 27.04.2015.
 */
public class Preferences {
    SharedPreferences pLvl;

    public Preferences(Context context)
    {
        pLvl = context.getSharedPreferences("LVL", Context.MODE_PRIVATE);
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
    //endregion

    //region #CHECK RANGES
    private boolean CheckLevelRange(int i)
    {
        if(i > 0 && i <201)
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
    //endregion
}
