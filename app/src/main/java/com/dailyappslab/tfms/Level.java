package com.dailyappslab.tfms;

import android.content.Context;

/**
 * Created by GreenQ on 27.04.2015.
 */
public class Level {
    private Context context;
    public String Fact;
    public String IsFactCorrect;

    public Level(Context context, int lvl)
    {
        this.context = context;
        this.Fact = context.getResources().getString(context.getResources().getIdentifier("q" + lvl, "string", context.getPackageName()));
        this.IsFactCorrect = context.getResources().getString(context.getResources().getIdentifier("q" + lvl + "a", "string", context.getPackageName()));
    }

    public boolean PressYes()
    {
        if(Boolean.valueOf(IsFactCorrect))
            return true;
        else return false;
    }

    public boolean PressNo()
    {
        if(Boolean.valueOf(IsFactCorrect))
            return false;
        else return true;
    }
}
