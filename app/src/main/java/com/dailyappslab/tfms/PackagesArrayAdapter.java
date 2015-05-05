package com.dailyappslab.tfms;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by GreenQ on 05.05.2015.
 */
public class PackagesArrayAdapter extends ArrayAdapter{

    private Context context;
    private int resource;
    private Package [] objects;

    public PackagesArrayAdapter(Context context, int resource, Package[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position,
                        View convertView,
                        ViewGroup parent) {
        LayoutInflater inflater =
                ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(resource, parent, false);

        try {

            TextView txtPackage = (TextView)
                    row.findViewById(R.id.txtViewPackage);
            txtPackage.setText(
                    objects[position].Name.toString());
            return row;
        }
        catch(Exception ex)
        {

        }
        return row;
    }
}
//
