package com.dailyappslab.tfms;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
