package com.dailyappslab.tfms;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by GreenQ on 05.05.2015.
 */
public class PackageActivity extends ActionBarActivity {
    Preferences preferences;
    TextView txtTickets;
    CountDownTimer countDownTimer;
    CountDownTimer globalTimer;
    //CountDow
    long timeLeft;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.package_list);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        timeLeft = 0;
        preferences = new Preferences(this);
        txtTickets = (TextView) findViewById(R.id.txtTickets);

        //txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");

        ListView lvMain = (ListView) findViewById(R.id.lvMain);


        PackagesArrayAdapter adapter = new PackagesArrayAdapter(this,
                R.layout.package_list_item, Globals.GetPackages());
        final Preferences preferences = new Preferences(this);

//        Toast.makeText(this, (String) String.valueOf(preferences.GetCurrentPackage()),
//                Toast.LENGTH_LONG).show();

        //CounTimeAtStartUp();
        //Toast.makeText(getBaseContext(), "pervonah", Toast.LENGTH_SHORT).show();

//        if(preferences.GetCurrentTickets() == 5)
//            CountTime("new");
//        else
//            CountTime("continue");
        GetTicketsDueTime();
        lvMain.setAdapter(adapter);
        lvMain.setClickable(true);
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Globals.CurrentPackage = Package.GetPackage(((TextView) ((LinearLayout) view).findViewById(R.id.txtViewPackage)).getText().toString(), Globals.GetPackages());

                if (Globals.CurrentPackage.Id <= preferences.GetCurrentPackage()) {

                    if (preferences.GetCurrentTickets() == 0 && Globals.CurrentPackage.Id == preferences.GetCurrentPackage()) {
                        Intent i = new Intent(PackageActivity.this, TicketBuyActivity.class);
                        startActivity(i);
                    }

                    else {

                        Intent i = new Intent(PackageActivity.this, GameActivity.class);
                        startActivity(i);

                        if (Globals.CurrentPackage.Id == preferences.GetCurrentPackage()) {
                            preferences.EditTickets(preferences.GetCurrentTickets()-1);
                            txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                            if(preferences.GetCurrentTickets() == 4) {
                                preferences.EditTicketUsageTime(System.currentTimeMillis());
                                if(globalTimer == null)
                                    CountTime("new");
                                else
                                    CountTime("continue");
                            }
                            else
                                CountTime("continue");
                        }
                    }
                }

                else {
                    Intent i = new Intent(PackageActivity.this, PackageLockedActivity.class);
                    startActivity(i);
                }

            }
        });

        //FixTicketsAmount();
    }
//

    public void StartTicketsShop(View view)
    {
        Intent i = new Intent(PackageActivity.this, TicketBuyActivity.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
    }
//
//    @Override
//    protected void onResume()
//    {
//        super.onResume();
//        ListView lvMain = (ListView) findViewById(R.id.lvMain);
//
//        //CounTimeAtStartUp();
//        //Toast.makeText(getBaseContext(), "vtoronah", Toast.LENGTH_SHORT).show();
//
//
//        //txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
//        PackagesArrayAdapter adapter = new PackagesArrayAdapter(this,
//                R.layout.package_list_item, Globals.GetPackages());
//        final Preferences preferences = new Preferences(this);
//
//        GetTicketsDueTime();
////        Toast.makeText(this, (String) String.valueOf(preferences.GetCurrentPackage()),
////                Toast.LENGTH_LONG).show();
//
//        lvMain.setAdapter(adapter);
//        lvMain.setClickable(true);
//        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Globals.CurrentPackage = Package.GetPackage(((TextView) ((LinearLayout)view).findViewById(R.id.txtViewPackage)).getText().toString(), Globals.GetPackages());
//                if(Globals.CurrentPackage.Id <= preferences.GetCurrentPackage()) {
//                    if(preferences.GetCurrentTickets() == 0 && Globals.CurrentPackage.Id == preferences.GetCurrentPackage()) {
//                        Intent i = new Intent(PackageActivity.this, TicketBuyActivity.class);
//                        startActivity(i);
//
//                    }
//                    else {
//                        Intent i = new Intent(PackageActivity.this, GameActivity.class);
//                        startActivity(i);
//                        if (Globals.CurrentPackage.Id == preferences.GetCurrentPackage()) {
//                            preferences.EditTickets(preferences.GetCurrentTickets()-1);
//                            txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
//                            if(preferences.GetCurrentTickets() == 4) {
//                                preferences.EditTicketUsageTime(System.currentTimeMillis());
//                                if(globalTimer == null)
//                                    CountTime("new");
//                                else
//                                    CountTime("continue");
//                            }
//                            else
//                                CountTime("continue");
//                        }
//                    }
//                }
//                else {
//                    Intent i = new Intent(PackageActivity.this, PackageLockedActivity.class);
//                    startActivity(i);
//                }
//            }
//        });
//        //FixTicketsAmount();
//    }


    public void CountTime(String instruction)
    {
        //txtTime = (TextView) findViewById( R.id.txtTime );
        switch (instruction) {
            case "new":
                globalTimer = new CountDownTimer(420000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        //txtTime.setText(new SimpleDateFormat("mm:ss").format(new Date( millisUntilFinished)));
                        // if(((millisUntilFinished) % 420000) < 1000)
                        //{
//                    preferences.EditTickets(preferences.GetCurrentTickets() + 1);
//                    txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                        // FixTicketsAmount();
                        //}
                        timeLeft = millisUntilFinished;
                    }

                    public void onFinish() {
                        if (preferences.GetCurrentTickets() < 5) {
                            //preferences.EditTickets(preferences.GetCurrentTickets() + 1);
                            txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                            if (preferences.GetCurrentTickets() < 5)
                                CountTime("continue");
                            //txtTime.setText("");
                        }
                    }
                }.start();
                break;
            case "continue":
                try {
                    globalTimer.cancel();
                }
                catch(Exception ex)
                {}
                globalTimer = new CountDownTimer(420000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        //txtTime.setText(new SimpleDateFormat("mm:ss").format(new Date( millisUntilFinished)));
                        if(((millisUntilFinished) % 420000) < 1000)
                        {
                            //preferences.EditTickets(preferences.GetCurrentTickets() + 1);
                            txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                            // FixTicketsAmount();
                            //}
                            timeLeft = millisUntilFinished;
                        }
                    }

                    public void onFinish() {
                        if(preferences.GetCurrentTickets() < 5)
                        {
                            //preferences.EditTickets(preferences.GetCurrentTickets() + 1);
                            txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                            if (preferences.GetCurrentTickets() < 5)
                                CountTime("continue");
                            //txtTime.setText("");
                        }
                    }
                }.start();
                break;
        }
    }

    public void GetTicketsDueTime()
    {
        long lastTicketUsage = preferences.GetTicketUsageTime();
        long currentTime = System.currentTimeMillis();

        long differenceTime = currentTime - lastTicketUsage;

        long restTime = differenceTime % 420000;

        long moduledTime = differenceTime - restTime;

        int possibleTickets = (int) (moduledTime/420000);


        int temp = preferences.GetCurrentTickets() + possibleTickets;
        Toast.makeText(getBaseContext(), String.valueOf(temp), Toast.LENGTH_SHORT).show();

        if(temp < 5)
        {
            //preferences.EditTickets(temp);
            txtTickets.setText(String.valueOf(temp) + "/5");

            globalTimer = new CountDownTimer(Globals.TimeLeft, 1000) {

                public void onTick(long millisUntilFinished) {
                    //txtTime.setText(new SimpleDateFormat("mm:ss").format(new Date( millisUntilFinished)));
                    // if(((millisUntilFinished) % 420000) < 1000)
                    //{
//                    preferences.EditTickets(preferences.GetCurrentTickets() + 1);
//                    txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                    // FixTicketsAmount();
                    //}
                    timeLeft = millisUntilFinished;
                }

                public void onFinish() {
                    if (preferences.GetCurrentTickets() < 6) {
                        //preferences.EditTickets(preferences.GetCurrentTickets() + 1);
                        txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
                        if (preferences.GetCurrentTickets() < 5)
                            CountTime("continue");
                        //txtTime.setText("");
                    }
                }
            }.start();
        }
        else {
            //preferences.EditTickets(5);
            txtTickets.setText(String.valueOf(5) + "/5");
        }
    }

    public void FixTicketsAmount()
    {
        long timeleft = TicketsManager.GetTimeLeft(preferences.GetTicketUsageTime(),getResources().getConfiguration().locale, getBaseContext(), preferences.GetCurrentTickets());
        long partialTimeLeft = timeleft % 15000;
        long resultTimeLeft = timeleft - partialTimeLeft;

        Toast.makeText(getApplicationContext(), "Resulttimeleft " + resultTimeLeft, Toast.LENGTH_LONG);

        if(resultTimeLeft == 75000) {
            if (preferences.GetCurrentTickets() < 0)
                preferences.EditTickets(0);
        }
        else if(resultTimeLeft == 60000) {
            if (preferences.GetCurrentTickets() < 1)
                preferences.EditTickets(1);
        }
        else if(resultTimeLeft == 45000) {
            if (preferences.GetCurrentTickets() < 2)
                preferences.EditTickets(2);
        }
        else if(resultTimeLeft == 30000) {
            if (preferences.GetCurrentTickets() < 3)
                preferences.EditTickets(3);
        }
        else
        if(resultTimeLeft == 15000) {
            if (preferences.GetCurrentTickets() < 4)
                preferences.EditTickets(4);
        }
        else if(timeleft <= 0) {
            if (preferences.GetCurrentTickets() < 5)
                preferences.EditTickets(5);
        }






        txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
    }

//    public void CounTimeAtStartUp()
//    {
//        long startTimeLeft = TicketsManager.GetTimeLeft(preferences.GetTicketUsageTime(),getResources().getConfiguration().locale, getBaseContext(), preferences.GetCurrentTickets());
//        long partialTimeLeft = startTimeLeft % 15000;
//        //Toast.makeText(getBaseContext(), String.valueOf(preferences.GetCurrentTickets()), Toast.LENGTH_SHORT).show();
//
//        if(startTimeLeft >= 0) {
//            int sum = (int) (startTimeLeft - partialTimeLeft) / 15000;
//            preferences.EditTickets(TicketsManager.GetAmountOfTicketsDueToTime(System.currentTimeMillis(), getBaseContext()));
//            txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
//          //  Toast.makeText(getBaseContext(), "summa "+ sum, Toast.LENGTH_SHORT).show();
//            //preferences.EditTickets((int) (startTimeLeft - partialTimeLeft) / 15000);
//            //Toast.makeText(getBaseContext(), String.valueOf(preferences.GetCurrentTickets()), Toast.LENGTH_SHORT).show();
//            //txtTickets.setText(preferences);
//            // }
//
//            countDownTimer = new CountDownTimer(partialTimeLeft, 1000) {
//
//                public void onTick(long millisUntilFinished) {
//                    // txtTime.setText(new SimpleDateFormat("mm:ss").format(new Date( millisUntilFinished)));
//                    // if(((millisUntilFinished) % 15000) < 1000)
//                    //{
////                    preferences.EditTickets(preferences.GetCurrentTickets() + 1);
////                    txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
//                    // FixTicketsAmount();
//                    //}
//                    timeLeft = millisUntilFinished;
//                }
//
//                public void onFinish() {
//                    preferences.EditTickets(preferences.GetCurrentTickets() + 1);
//                    txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
//                    if (preferences.GetCurrentTickets() < 5)
//                        CountTime("continue");
//                    //txtTime.setText("");
//                }
//            }.start();
//        }
//        else
//            preferences.EditTickets(5);
////
////        if(preferences.GetCurrentTickets() == 5) {
////            if (countDownTimer == null)
////                CountTime("new");
////        }
////        else
////                CountTime("continue");
//
//    }
//
//    public void CountTime(String instruction)
//    {
//        //txtTime = (TextView) findViewById( R.id.txtTime );
//        switch (instruction) {
//            case "new":
//            countDownTimer = new CountDownTimer(15000, 1000) {
//
//                public void onTick(long millisUntilFinished) {
//                    // txtTime.setText(new SimpleDateFormat("mm:ss").format(new Date( millisUntilFinished)));
//                    // if(((millisUntilFinished) % 15000) < 1000)
//                    //{
////                    preferences.EditTickets(preferences.GetCurrentTickets() + 1);
////                    txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
//                    // FixTicketsAmount();
//                    //}
//                    timeLeft = millisUntilFinished;
//                }
//
//                public void onFinish() {
//                    preferences.EditTickets(preferences.GetCurrentTickets() + 1);
//                    txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
//                    if (preferences.GetCurrentTickets() < 5)
//                        CountTime("continue");
//                    //txtTime.setText("");
//                }
//            }.start();
//                break;
//            case "continue":
//                try {
//                    countDownTimer.cancel();
//                }
//                catch(Exception ex)
//                {}
//                countDownTimer = new CountDownTimer(15000, 1000) {
//
//                    public void onTick(long millisUntilFinished) {
//                        // txtTime.setText(new SimpleDateFormat("mm:ss").format(new Date( millisUntilFinished)));
//                         if(((millisUntilFinished) % 15000) < 1000)
//                        {
//                    preferences.EditTickets(preferences.GetCurrentTickets() + 1);
//                    txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
//                        // FixTicketsAmount();
//                        //}
//                        timeLeft = millisUntilFinished;
//                    }
//                    }
//
//                    public void onFinish() {
//                        preferences.EditTickets(preferences.GetCurrentTickets() + 1);
//                        txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
//                    if (preferences.GetCurrentTickets() < 5)
//                        CountTime("continue");
//                        //txtTime.setText("");
//                    }
//                }.start();
//                break;
//        }
//    }
    @Override
    public void onBackPressed()
    {
        finish();
    }
}
