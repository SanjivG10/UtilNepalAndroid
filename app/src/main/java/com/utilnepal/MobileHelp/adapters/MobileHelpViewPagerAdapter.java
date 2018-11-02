package com.utilnepal.MobileHelp.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.utilnepal.MobileHelp.ActivitiesFragment.EmergencyFragment;
import com.utilnepal.MobileHelp.ActivitiesFragment.MiscellaneousFragment;

import java.util.List;

public class MobileHelpViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    public MobileHelpViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0:
                return new MiscellaneousFragment();
            case 1 :
                return new EmergencyFragment();

            default:
                return new MiscellaneousFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Miscellaneous";
            case 1 :
                return "Emergency";
            default:
                return "Miscellaneous";
        }
    }
}