package com.dailyappslab.tfms;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by TOPAC on 05.05.2015.
 */


public class List extends ActionBarActivity {


    String[] names = {"Уровень 1", "Уровень 2", "Уровень 3", "Уровень 4", "Уровень 5", "Уровень 6",
            "Уровень 7", "Уровень 8", "Уровень 9", "Уровень 10", "Уровень 11", "Уровень 12", "Уровень 13",
            "Уровень 14", "Уровень 15", "Уровень 16", "Уровень 17",
            "Уровень 18", "Уровень 19", "Уровень 20", "Уровень 21"};

    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        // находим список
        ListView lvMain = (ListView) findViewById(R.id.lvMain);

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, names);

        // присваиваем адаптер списку
        lvMain.setAdapter(adapter);

    }
}