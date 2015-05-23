package com.dailyappslab.tfms;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anjlab.android.iab.v3.BillingProcessor;

/**
 * Created by GreenQ on 21.05.2015.
 */
public class ConfirmTicketBuyActivity extends Activity {
    public static String Message;
    TextView txtMessage;
    public static int TicketsToBuy;
    ImageView confirm;
    ImageView cancel;
    Preferences preferences;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_ticket_buy);
        confirm = (ImageView) findViewById(R.id.imgVConfirm);
        txtMessage = (TextView) findViewById(R.id.txtTicketMessage);
        txtMessage.setText(Message);
        preferences = new Preferences(this);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (TicketsToBuy){
                    case 1:
                        preferences.EditGold(preferences.GetCurrentGold()-10);
                        preferences.EditTickets(preferences.GetCurrentTickets()+1);
                        preferences.EditTicketsAtLastUsage(preferences.GetCurrentTickets());
                        preferences.EditLastTicketUsageTime(System.currentTimeMillis());
                        RefreshTicketsAmount();
                        break;
                    case 2:
                        preferences.EditGold(preferences.GetCurrentGold()-20);
                        preferences.EditTickets(preferences.GetCurrentTickets()+2);
                        preferences.EditTicketsAtLastUsage(preferences.GetCurrentTickets());
                        preferences.EditLastTicketUsageTime(System.currentTimeMillis());
                        RefreshTicketsAmount();
                        break;
                    case 3:
                        preferences.EditGold(preferences.GetCurrentGold()-50);
                        preferences.EditTickets(preferences.GetCurrentTickets()+5);
                        preferences.EditTicketsAtLastUsage(preferences.GetCurrentTickets());
                        preferences.EditLastTicketUsageTime(System.currentTimeMillis());
                        RefreshTicketsAmount();
                        break;
                }
                finish();
            }
        });
        cancel = (ImageView) findViewById(R.id.imgVCancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void RefreshTicketsAmount()
    {
        try {
            MainActivity.txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
            MainActivity.txtGold.setText(String.valueOf(preferences.GetCurrentGold()));
            PackageActivity.txtTickets.setText(String.valueOf(preferences.GetCurrentTickets()) + "/5");
            PackageActivity.txtGold.setText(String.valueOf(preferences.GetCurrentGold()));
            GameActivity.txtGold.setText(String.valueOf(preferences.GetCurrentGold()));
            TicketBuyActivity.txtTickets.setText(String.valueOf(preferences.GetCurrentTickets() + "/5"));
            TicketBuyActivity.txtTime.setText("");
        }
        catch (Exception ex){
            Log.d("TimerError: ", ex.getMessage());}
    }

}
