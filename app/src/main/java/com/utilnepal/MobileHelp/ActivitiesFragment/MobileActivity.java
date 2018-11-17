package com.utilnepal.MobileHelp.ActivitiesFragment;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.utilnepal.MobileHelp.Files.MiscellaneousNumberFeatures;
import com.utilnepal.R;
import com.utilnepal.MobileHelp.adapters.MiscellaneousNumberAdapter;
import com.utilnepal.MobileHelp.adapters.MobileHelpViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MobileActivity extends AppCompatActivity {

    private ArrayList<MiscellaneousNumberFeatures> features ;
    private MiscellaneousNumberAdapter miscellaneousNumberAdapter;
    private TabLayout tabLayoutHomePage;
    private ViewPager mViewPager;
    private List<Fragment> fragmentList;
    private MobileHelpViewPagerAdapter mobileHelpViewPagerAdapter;
    private Toolbar toolbar;
    private AdView adView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);

        MobileAds.initialize(this,"ca-app-pub-3940256099942544~3347511713");
        adView = findViewById(R.id.mobileViewAdView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.loadAd(adRequest);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getPermission();

        mViewPager = findViewById(R.id.viewPagerHomePage);
        mobileHelpViewPagerAdapter = new MobileHelpViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mobileHelpViewPagerAdapter);

        tabLayoutHomePage = findViewById(R.id.tabLayoutHomePage);
        tabLayoutHomePage.setupWithViewPager(mViewPager);
    }

    private void getPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MobileActivity.this, new String[]{Manifest.permission.CALL_PHONE},1000);
        }
    }


}
