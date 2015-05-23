package com.dailyappslab.tfms;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class GameActivity extends Activity {

    //region #DECLARATION
    TextView txtQuestion;
    TextView txtCurLvl;
    TextView txtCurPackage;
    public static TextView txtGold;
    Level level;
    Preferences preferences;
    Package[] packages;
    int displayedLevel;
    int oscarsAmount;
    ImageView hp1;
    ImageView hp2;

    //endregion

    //region #OVERRIDED METHODS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            super.onCreate(savedInstanceState);
            //requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.game);

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


            preferences = new Preferences(this);
            packages = Globals.GetPackages();
            Globals.CurrentLevel = Globals.CurrentPackage.MinQuestion;
            level = new Level(this, Globals.CurrentLevel);

            displayedLevel = 1;

            hp1 = (ImageView) findViewById(R.id.hp1);
            hp2 = (ImageView) findViewById(R.id.hp2);

            txtQuestion = (TextView) findViewById(R.id.txtQuestion);
            txtQuestion.setText(level.Fact);

            txtGold = (TextView) findViewById(R.id.txtGold);
            txtGold.setText(String.valueOf(preferences.GetCurrentGold()));

            txtCurLvl = (TextView) findViewById(R.id.txtCurLvl);
            txtCurLvl.setText(String.valueOf(displayedLevel) + "/10");

            txtCurPackage = (TextView) findViewById(R.id.txtCurPackage);
            txtCurPackage.setText(String.valueOf(Globals.CurrentPackage.Id));

            oscarsAmount = 2;
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
            {
                ShowGuessResultPopUp(true);
                //DisplayAlert("Вы угадали", "Поздравляем!");
            }
            else
            {
                ShowGuessResultPopUp(false);
                RemoveOscar();
                //DisplayAlert("Вы не угадали", "Не поздравляем!");

                //NextLevel(null);
            }
        }
        catch (Exception ex)
        {
           DisplayAlert(ex.getMessage(), "Возникла непредвиденная ошибка");
        }
    }

    public void PressNo(View view)
    {
        try {
        if (level.PressNo()) {
            //popups.ShowWindow(GuessActivity.class);
//            Globals.IsCorrect = true;
//            ShowWinDialog();
//            NextLevel(null);
            ShowGuessResultPopUp(true);
        }
            //DisplayAlert("Вы угадали", "Поздравляем!");
        else {
//            Globals.IsCorrect = false;
//            ShowWinDialog();
//            NextLevel(null);
            //popups.ShowWindow(GuessActivity.class);
            ShowGuessResultPopUp(false);
            RemoveOscar();
        }
        // DisplayAlert("Вы не угадали", "Не поздравляем!");

    }
    catch (Exception ex)
    {
        DisplayAlert(ex.getMessage(), "Ошибочка вышла");
    }
    }

    public void NextLevel(View view)
    {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
        displayedLevel = displayedLevel+1;
        Globals.CurrentLevel = Globals.CurrentLevel + 1;
        preferences.EditLevel(preferences.GetCurrentLevel());
        level = new Level(getBaseContext(), Globals.CurrentLevel);
        txtQuestion.setText(level.Fact);
        txtCurLvl.setText(String.valueOf(displayedLevel) + "/10");
//            }
//        }, 1000);
    }

    public void NextPackage()
    {

    }

    public void PrevLevel(View view)
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Globals.CurrentLevel = Globals.CurrentLevel - 1;
                preferences.EditLevel(preferences.GetCurrentLevel());
                level = new Level(getBaseContext(), Globals.CurrentLevel);
                txtQuestion.setText(level.Fact);
                txtCurLvl.setText(String.valueOf(Globals.CurrentLevel) + "/10");
            }
        }, 1000);
    }

    public void PressBack(View view)
    {
        super.finish();
    }

    public void StartGameShop(View view)
    {
        Intent i = new Intent(GameActivity.this, MarketActivity.class);
        startActivity(i);
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

    //region #DIALOG WINDOWS
    private void ShowWinDialog()
    {
        Intent i = new Intent(GameActivity.this, GuessActivity.class);
        startActivityForResult(i, 1);
    }

    private void ShowWinPopUp()
    {
        LayoutInflater layoutInflater
                = (LayoutInflater) getBaseContext()
                .getSystemService(LAYOUT_INFLATER_SERVICE);

        View popupView = layoutInflater.inflate(R.layout.game_win, null);
        final PopupWindow popupWindowWin = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        try {
            //sadView = new SADView(this, "5536149602e39f1f00000000");
            //LinearLayout adLayout = (LinearLayout) popupView.findViewById(R.id.admob);

            // Add the adView to it
            //adLayout.addView(this.sadView);
            //sadView.loadAd(SADView.LANGUAGE_EN);
        } catch (Exception ex) {
        }

        RelativeLayout rltvContinue = (RelativeLayout) popupView.findViewById(R.id.rltvContinue);


        rltvContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.EditGold(preferences.GetCurrentGold()+5);
                txtGold.setText(String.valueOf(preferences.GetCurrentGold()));

                if (preferences.GetCurrentTickets() == 0 && Globals.CurrentPackage.Id == preferences.GetCurrentPackage()) {
                    Intent i = new Intent(GameActivity.this, TicketBuyActivity.class);
                    startActivity(i);
                    finish();
                }
                else
                preferences.EditTickets(preferences.GetCurrentTickets()-1);
                popupWindowWin.dismiss();
            }
        });
        popupWindowWin.showAtLocation(findViewById(R.id.rootLayout), 0, 0, -10);
    }


    private void ShowLosePopUp()
    {
        LayoutInflater layoutInflater
                = (LayoutInflater) getBaseContext()
                .getSystemService(LAYOUT_INFLATER_SERVICE);

        View popupView = layoutInflater.inflate(R.layout.game_over, null);
        final PopupWindow popupWindowWin = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        try {
            //sadView = new SADView(this, "5536149602e39f1f00000000");
            //LinearLayout adLayout = (LinearLayout) popupView.findViewById(R.id.admob);

            // Add the adView to it
            //adLayout.addView(this.sadView);
            //sadView.loadAd(SADView.LANGUAGE_EN);
        } catch (Exception ex) {
        }

        RelativeLayout restart = (RelativeLayout) popupView.findViewById(R.id.rltvRestart);
        RelativeLayout quit = (RelativeLayout) popupView.findViewById(R.id.rltvQuit);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (preferences.GetCurrentTickets() == 0 && Globals.CurrentPackage.Id == preferences.GetCurrentPackage()) {
                    Intent i = new Intent(GameActivity.this, TicketBuyActivity.class);
                    startActivity(i);
                    finish();
                }
                else {
                    preferences.EditTickets(preferences.GetCurrentTickets() - 1);
                    popupWindowWin.dismiss();
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        popupWindowWin.showAtLocation(findViewById(R.id.rootLayout), 0, 0, -10);
    }

    private void ShowGuessResultPopUp(final boolean isCorrect) {


        LayoutInflater layoutInflater
                = (LayoutInflater) getBaseContext()
                .getSystemService(LAYOUT_INFLATER_SERVICE);

        View popupView = layoutInflater.inflate(R.layout.fact, null);
        final PopupWindow popupWindowWin = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        try {
            //sadView = new SADView(this, "5536149602e39f1f00000000");
            //LinearLayout adLayout = (LinearLayout) popupView.findViewById(R.id.admob);

            // Add the adView to it
            //adLayout.addView(this.sadView);
            //sadView.loadAd(SADView.LANGUAGE_EN);
        } catch (Exception ex) {
        }

        TextView txtIsCorrect = (TextView) popupView.findViewById(R.id.txtIsCorrect);
        TextView txtFact = (TextView) popupView.findViewById(R.id.txtFact);
        RelativeLayout rootWinLayout = (RelativeLayout) popupView.findViewById(R.id.rootWinLayout);
        if (isCorrect) {
            txtIsCorrect.setText("Верно!");
            rootWinLayout.setBackgroundColor(getResources().getColor(R.color.win));
        }
        else if (!isCorrect) {
            txtIsCorrect.setText("Не верно!");
            rootWinLayout.setBackgroundColor(getResources().getColor(R.color.lose));
        }
        txtFact.setText(getResources().getString(getResources().getIdentifier("q" + Globals.CurrentLevel + "add", "string", getPackageName())));

        RelativeLayout btnDismiss = (RelativeLayout) popupView.findViewById(R.id.btnContinue);
        btnDismiss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Globals.CurrentLevel == Globals.CurrentPackage.MaxQuestion) {
                    //Globals.DisplayAlert(getBaseContext(), "Уровень пройден", "Грац");
                    Globals.CurrentPackage = Package.GetNextPackage(Globals.CurrentPackage, packages);
                    //preferences.EditPackage(Globals.CurrentPackage.Id);
                    if(preferences.GetCurrentPackage() < Globals.CurrentPackage.Id)
                        preferences.EditPackage(Globals.CurrentPackage.Id);


                    txtCurPackage.setText(String.valueOf(Globals.CurrentPackage.Id));
                    displayedLevel = 0;
                    ShowWinPopUp();
                    oscarsAmount = 2;
                    SetOscarImage();
                }
                NextLevel(null);
                popupWindowWin.dismiss();
                if (oscarsAmount == -1) {
                    ShowLosePopUp();
                }
            }
        });

        popupWindowWin.showAtLocation(findViewById(R.id.rootLayout), 0, 0, -10);
        try {
            // if(interstitial.isLoaded())
            //interstitial.show();
            // DelayedAdsShow();
        } catch (Exception ex) {
            Globals.DisplayAlert(this, ex.getMessage(), "Возникла непредвиденная ошибка");
        }
    }
    //endregion

    //region #OSCARS
    public void RemoveOscar() {
        if (oscarsAmount > -1 && oscarsAmount < 3)
        {
            oscarsAmount = oscarsAmount - 1;
            SetOscarImage();
        }
    }

    public void AddOscar()
    {
        oscarsAmount = oscarsAmount + 1;
    }

    public void SetOscarImage()
    {
        switch (oscarsAmount) {
            case 0:
                hp1.setImageResource(R.drawable.leo);
                hp2.setImageResource(R.drawable.leo);
                break;
            case 1:
                hp1.setImageResource(R.drawable.oscar);
                hp2.setImageResource(R.drawable.leo);
                break;
            case 2:
                hp1.setImageResource(R.drawable.oscar);
                hp2.setImageResource(R.drawable.oscar);
                break;
        }
    }
    //endregion
}

