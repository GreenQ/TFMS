package com.dailyappslab.tfms;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.WindowManager;
import android.widget.ListView;

/**
 * Created by GreenQ on 05.05.2015.
 */
public class PackageActivity extends ActionBarActivity {
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.list);

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        ListView lvMain = (ListView) findViewById(R.id.lvMain);


        PackagesArrayAdapter adapter = new PackagesArrayAdapter(this,
                R.layout.package_list_item, Globals.GetPackages());

        lvMain.setAdapter(adapter);



    }
}
