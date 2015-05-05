package com.dailyappslab.tfms;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


public class GameActivity extends ActionBarActivity {

    //region #DECLARATION
    TextView txtQuestion;
    TextView txtCurLvl;
    Level level;
    Preferences preferences;
    int currentLevel;
    //endregion

    //region #OVERRIDED METHODS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            //requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.game);

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);



            preferences = new Preferences(this);
            currentLevel = Globals.CurrentPackage.MinQuestion;
            level = new Level(this, currentLevel);

            txtQuestion = (TextView) findViewById(R.id.txtQuestion);
            txtQuestion.setText(level.Fact);

            txtCurLvl = (TextView) findViewById(R.id.txtCurLvl);
            txtCurLvl.setText(String.valueOf(currentLevel));

        }
        catch (Exception ex)
        {
           DisplayAlert(ex.getMessage(), "Возникла непредвиденная ошибка");
        }
    }
    //endregion

    //region #ONCLICK METHODS
    public void PressYes(View view)
    {
        try {
            if (level.PressYes())
                DisplayAlert("Вы угадали", "Поздравляем!");
            else
                DisplayAlert("Вы не угадали", "Не поздравляем!");
        }
        catch (Exception ex)
        {
           DisplayAlert(ex.getMessage(), "Возникла непредвиденная ошибка");
        }
    }

    public void PressNo(View view)
    {
        if(level.PressNo())
            DisplayAlert("Вы угадали", "Поздравляем!");
        else
            DisplayAlert("Вы не угадали", "Не поздравляем!");
    }

    public void NextLevel(View view)
    {
        currentLevel = currentLevel+1;
        preferences.EditLevel(preferences.GetCurrentLevel() + 1);
        level = new Level(this, currentLevel);
        txtQuestion.setText(level.Fact);
        txtCurLvl.setText(String.valueOf(currentLevel));
    }

    public void PrevLevel(View view)
    {
        currentLevel = currentLevel-1;
        preferences.EditLevel(currentLevel);
        level = new Level(this, currentLevel);
        txtQuestion.setText(level.Fact);
        txtCurLvl.setText(String.valueOf(currentLevel));
        //
    }

    public void PressBack(View view)
    {
        super.finish();
    }
    //endregion

    //region #SUPPLEMENT
    private void DisplayAlert(String string, String title)
    {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage(string);
        dlgAlert.setTitle(title);
        dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }
    //endregion
}
