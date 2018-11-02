package com.utilnepal.MobileHelp.ActivitiesFragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mViewPager = findViewById(R.id.viewPagerHomePage);
        mobileHelpViewPagerAdapter = new MobileHelpViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mobileHelpViewPagerAdapter);

        tabLayoutHomePage = findViewById(R.id.tabLayoutHomePage);
        tabLayoutHomePage.setupWithViewPager(mViewPager);
    }


}
