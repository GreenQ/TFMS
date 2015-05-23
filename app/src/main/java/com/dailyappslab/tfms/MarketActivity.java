package com.dailyappslab.tfms;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
/**
 * Created by GreenQ on 06.05.2015.
 */
public class MarketActivity extends Activity {
    Button btnClose;
    RelativeLayout btn100;
    RelativeLayout btn250;
    RelativeLayout btn500;
    RelativeLayout btn1000;
    RelativeLayout btn2500;
    private int currentPrise;
    private String currentPriseTag ="";
    private boolean readyToPurchase;
    private BillingProcessor bps;
    Preferences preferences;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameshop);




        btnClose = (Button) findViewById(R.id.btnClose);
        btn100 = ( RelativeLayout ) findViewById( R.id.m1 );
        btn250 = ( RelativeLayout ) findViewById( R.id.m2 );
        btn500 = ( RelativeLayout ) findViewById( R.id.m3 );
        btn1000 = ( RelativeLayout ) findViewById( R.id.m4 );
        btn2500 = ( RelativeLayout ) findViewById( R.id.m5 );

        btn2500.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickPrised(2500, "tfmsruc2500" );
            }
        });

        btn1000.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickPrised(1000, "tfmsruc1000"  );
            }
        });

        btn500.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickPrised(500, "tfmsruc500" );
            }
        });

        btn250.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickPrised(250, "tfmsruc250" );
            }

        });

        btn100.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickPrised(100 , "tfmsruc100" );
            }
        });

        btnClose = (Button) findViewById(R.id.btnClose);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bps = new BillingProcessor( this ,  getResources().getString( R.string.base64cod), new BillingProcessor.IBillingHandler() {
            @Override
            public void onProductPurchased(String productId, TransactionDetails details) {

            }
            @Override
            public void onBillingError(int errorCode, Throwable error) {
            }
            @Override
            public void onBillingInitialized() {
                readyToPurchase = true;

            }
            @Override
            public void onPurchaseHistoryRestored() {

            }
        });}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        bps.loadOwnedPurchasesFromGoogle();
        if(  bps.isPurchased( currentPriseTag ) )
        {
            bps.consumePurchase(currentPriseTag);
            preferences.EditGold(currentPrise);
            currentPriseTag = "";
            currentPrise = 0;
        }
        if (!bps.handleActivityResult(requestCode, resultCode, data))
            super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if ( bps != null)
            bps.release();
        super.onDestroy();
    }

    private void clickPrised(int integer , String id )
    {
        currentPrise = integer;
        currentPriseTag = id;
        if (!readyToPurchase) {
            showAlert("Billing not initialized.");
            return;
        }
        bps.purchase( currentPriseTag );
//        finish();
    }

    private void showAlert(String message)
    {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage(message);
        dlgAlert.setTitle("App Title");
        dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //dismiss the dialog
            }
        });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

}
