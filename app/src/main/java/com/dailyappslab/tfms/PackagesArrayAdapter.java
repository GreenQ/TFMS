package com.dailyappslab.tfms;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by GreenQ on 05.05.2015.
 */
public class PackagesArrayAdapter extends ArrayAdapter{

    private Context context;
    private int resource;
    private Package [] objects;
    Preferences preferences;

    public PackagesArrayAdapter(Context context, int resource, Package[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        preferences = new Preferences(context);
    }

    @Override
    public View getView(int position,
                        View convertView,
                        ViewGroup parent) {
        LayoutInflater inflater =
                ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(resource, parent, false);

        try {



            //if(objects[position].Id > preferences.GetCurrentPackage())

            //row.setBackgroundColor(context.getResources().getColor(R.color.yellow1_item));

            TextView txtPackage = (TextView)
                    row.findViewById(R.id.txtViewPackage);
            txtPackage.setText(
                    objects[position].Name.toString());

            if(objects[position].Id < preferences.GetCurrentPackage()) {
                //green color
                txtPackage.setBackgroundResource(R.drawable.paki_g);

            }
            else if(objects[position].Id == preferences.GetCurrentPackage()) {
                txtPackage.setBackgroundResource(R.drawable.paki_y);
                //yellow color
            }
            if(position == 0) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 80);
                params.setMargins(5, 12, 5, 2);
                //params.setMarginStart(12);
                row.setLayoutParams(params);
            }
            return row;
        }
        catch(Exception ex)
        {

        }
        return row;
    }
}
//
