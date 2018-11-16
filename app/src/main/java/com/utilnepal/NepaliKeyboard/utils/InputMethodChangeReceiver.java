package com.utilnepal.NepaliKeyboard.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.FragmentTransaction;

import com.utilnepal.NepaliKeyboard.ActivitiesFragments.EnableNepaliKeyboardFragment;

public class InputMethodChangeReceiver extends BroadcastReceiver {

    private FragmentTransaction ft;
    private static final String MY_KEYBOARD_ID = "com.utilnepal/.NepaliKeyboard.NepaliKeyboardService";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_INPUT_METHOD_CHANGED)) {
            String defaultIME = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD);
            EnableNepaliKeyboardFragment.checkIfKeyboardIsSelected(defaultIME);
        }


    }
}