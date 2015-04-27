package com.dailyappslab.tfms;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class GameActivity extends ActionBarActivity {

    TextView txtQuestion;
    Level level;
    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        preferences = new Preferences(this);
        level = new Level(this, preferences.GetCurrentLevel());

        txtQuestion = (TextView) findViewById(R.id.txtQuestion);

    }

    public void PressYes(View view)
    {
        if(level.PressYes())
            Toast.makeText(this, "Вы угадали", Toast.LENGTH_LONG);
        else
            Toast.makeText(this, "Вы не угадали", Toast.LENGTH_LONG);
    }

    public void PressNo(View view)
    {
        if(level.PressNo())
            Toast.makeText(this, "Вы не угадали", Toast.LENGTH_LONG);
        else
            Toast.makeText(this, "Вы угадали", Toast.LENGTH_LONG);
    }








    
}
