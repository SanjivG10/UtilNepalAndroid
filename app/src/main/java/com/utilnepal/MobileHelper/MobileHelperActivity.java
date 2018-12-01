package com.utilnepal.MobileHelper;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.utilnepal.AdUnits;
import com.utilnepal.MobileHelper.RechargeUtils.RechargeActivity;
import com.utilnepal.MobileHelper.data.SimNumbers;
import com.utilnepal.R;

import static com.utilnepal.MobileHelper.data.SimNumbers.CallForwardCheck;

public class MobileHelperActivity extends AppCompatActivity {

    private CardView balanceCheck;
    private CardView rechargeCardScanner;
    private CardView mobileNumberCheck;
    private CardView dataPackCheck;
    private CardView callForwardCheck;
    private CardView miscellaneousNumber;
    private AlertDialog alertDialog;

    private Button NcellCallingButton;
    private Button NTCCallingButton;

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_helper);

        initialize();
    }

    private void initialize() {
        balanceCheck = findViewById(R.id.balanceCheck);
        rechargeCardScanner = findViewById(R.id.rechargeScanner);
        mobileNumberCheck = findViewById(R.id.mobileNumberCheck);
        dataPackCheck = findViewById(R.id.dataPackCheck);
        callForwardCheck = findViewById(R.id.callForwardCheck);
        miscellaneousNumber = findViewById(R.id.miscelleanousNumber);

        //mobile Ads
//        MobileAds.initialize(this,
//                AdUnits.REAL_APP_ID);
//
//        mInterstitialAd = new InterstitialAd(this);
//        mInterstitialAd.setAdUnitId(AdUnits.MOBILE_HELP_AD_UNIT);
//        mInterstitialAd.loadAd(new AdRequest.Builder().build());
//
//        mInterstitialAd.setAdListener(new AdListener() {
//            @Override
//            public void onAdLoaded() {
//                // Code to be executed when an ad finishes loading.
//                mInterstitialAd.show();
//            }
//
//            @Override
//            public void onAdFailedToLoad(int errorCode) {
//                // Code to be executed when an ad request fails.
//            }
//
//            @Override
//            public void onAdOpened() {
//                // Code to be executed when the ad is displayed.
//            }
//
//            @Override
//            public void onAdLeftApplication() {
//                // Code to be executed when the user has left the app.
//
//            }
//
//            @Override
//            public void onAdClosed() {
//                // Code to be executed when when the interstitial ad is closed.
//            }
//        });






        balanceCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimChooserDialog(v.getId());
            }
        });

        rechargeCardScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimChooserDialog(v.getId());
            }
        });

        mobileNumberCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimChooserDialog(v.getId());
            }
        });

        dataPackCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimChooserDialog(v.getId());

            }
        });

        callForwardCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimChooserDialog(v.getId());
            }
        });

        miscellaneousNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //miscelleanous Number Showing
                Intent intent = new Intent(MobileHelperActivity.this,MiscellaneousActivity.class);
                startActivity(intent);
            }
        });


    }

    private void showSimChooserDialog(final int id) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View simChooserDialog = inflater.inflate(R.layout.sim_chooser_dialog, null);
        dialogBuilder.setView(simChooserDialog);

        final AlertDialog alertDialog = dialogBuilder.create();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(alertDialog.getWindow().getAttributes());
        lp.windowAnimations = R.style.DialogAnimation;
        alertDialog.getWindow().setAttributes(lp);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        NcellCallingButton = simChooserDialog.findViewById(R.id.ncellButton);
        NTCCallingButton = simChooserDialog.findViewById(R.id.ntcButton);

        //2131230882

        NcellCallingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (id)
                {
                    case R.id.balanceCheck:
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(SimNumbers.NcellBalanceCheck)));
                        startActivity(intent);

                        break;
                    case R.id.dataPackCheck:
                        Intent dataIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(SimNumbers.NcellDataPack)));
                        startActivity(dataIntent);
                        break;
                    case R.id.mobileNumberCheck:
                        Intent numberCheckIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(SimNumbers.NcellNumberCheck)));
                        startActivity(numberCheckIntent);
                        break;
                    case R.id.callForwardCheck:
                        Intent callForward = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(CallForwardCheck)));
                        startActivity(callForward);

                        break;
                    case R.id.rechargeScanner:
                        Intent recharge = new Intent(getApplicationContext(),RechargeActivity.class);
                        recharge.putExtra("type", "NCELL");
                        alertDialog.dismiss();
                        startActivity(recharge);
                        break;


                }
            }
        });

        NTCCallingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (id)
                {
                    case R.id.balanceCheck:
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(SimNumbers.NtcBalanceCheck)));
                        startActivity(intent);

                        break;
                    case R.id.dataPackCheck:
                        Intent dataIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(SimNumbers.NtcDataPack)));
                        startActivity(dataIntent);
                        break;

                    case R.id.mobileNumberCheck:
                        Intent numberCheckIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(SimNumbers.NtcNumberCheck)));
                        startActivity(numberCheckIntent);

                        break;
                    case R.id.callForwardCheck:
                        Intent callForward = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(CallForwardCheck)));
                        startActivity(callForward);

                        break;
                    case R.id.rechargeScanner:
                        Intent recharge = new Intent(getApplicationContext(),RechargeActivity.class);
                        recharge.putExtra("type", "NTC");
                        alertDialog.dismiss();
                        startActivity(recharge);
                        break;


                }

            }
        });

        alertDialog.show();
    }
}
