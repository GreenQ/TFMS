package com.dailyappslab.tfms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by GreenQ on 05.05.2015.
 */
public class PackageActivity extends ActionBarActivity {
    Preferences preferences;
    TextView txtTickets;

    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.package_list);

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        preferences = new Preferences(this);
        txtTickets = (TextView) findViewById(R.id.txtTickets);

        txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");

        ListView lvMain = (ListView) findViewById(R.id.lvMain);


        PackagesArrayAdapter adapter = new PackagesArrayAdapter(this,
                R.layout.package_list_item, Globals.GetPackages());
        final Preferences preferences = new Preferences(this);

//        Toast.makeText(this, (String) String.valueOf(preferences.GetCurrentPackage()),
//                Toast.LENGTH_LONG).show();

        lvMain.setAdapter(adapter);
        lvMain.setClickable(true);
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Globals.CurrentPackage = Package.GetPackage(((TextView) ((LinearLayout)view).findViewById(R.id.txtViewPackage)).getText().toString(), Globals.GetPackages());
                if(Globals.CurrentPackage.Id <= preferences.GetCurrentPackage()) {
                    if(preferences.GetCurrentTickets() == 0 && Globals.CurrentPackage.Id == preferences.GetCurrentPackage()) {
                        Intent i = new Intent(PackageActivity.this, TicketBuyActivity.class);
                        startActivity(i);
                    }
                    else {
                        Intent i = new Intent(PackageActivity.this, GameActivity.class);
                        startActivity(i);
                        if (Globals.CurrentPackage.Id == preferences.GetCurrentPackage()) {
                            preferences.EditTickets(preferences.GetCurrentTickets() - 1);
                            preferences.EditTicketUsageTime(System.currentTimeMillis());
                        }
                    }
                }
                else {
                    Intent i = new Intent(PackageActivity.this, PackageLockedActivity.class);
                    startActivity(i);
                }

            }
        });

//

    }
    @Override
    protected void onResume()
    {
        super.onResume();
        ListView lvMain = (ListView) findViewById(R.id.lvMain);

        txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
        PackagesArrayAdapter adapter = new PackagesArrayAdapter(this,
                R.layout.package_list_item, Globals.GetPackages());
        final Preferences preferences = new Preferences(this);

//        Toast.makeText(this, (String) String.valueOf(preferences.GetCurrentPackage()),
//                Toast.LENGTH_LONG).show();

        lvMain.setAdapter(adapter);
        lvMain.setClickable(true);
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Globals.CurrentPackage = Package.GetPackage(((TextView) ((LinearLayout)view).findViewById(R.id.txtViewPackage)).getText().toString(), Globals.GetPackages());
                if(Globals.CurrentPackage.Id <= preferences.GetCurrentPackage()) {
                    if(preferences.GetCurrentTickets() == 0 && Globals.CurrentPackage.Id == preferences.GetCurrentPackage()) {
                        Intent i = new Intent(PackageActivity.this, TicketBuyActivity.class);
                        startActivity(i);
                    }
                    else {
                        Intent i = new Intent(PackageActivity.this, GameActivity.class);
                        startActivity(i);
                        if (Globals.CurrentPackage.Id == preferences.GetCurrentPackage()) {
                            preferences.EditTickets(preferences.GetCurrentTickets() - 1);
                            preferences.EditTicketUsageTime(System.currentTimeMillis());
                        }
                    }
                }
                else {
                    Intent i = new Intent(PackageActivity.this, PackageLockedActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }
}
