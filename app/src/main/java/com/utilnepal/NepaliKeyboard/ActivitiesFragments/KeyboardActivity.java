package com.utilnepal.NepaliKeyboard.ActivitiesFragments;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import com.utilnepal.NepaliKeyboard.ActivitiesFragments.EnableNepaliKeyboardFragment;
import com.utilnepal.NepaliKeyboard.ActivitiesFragments.SelectNepaliKeyboardScreenFragment;
import com.utilnepal.NepaliKeyboard.utils.InputMethodChangeReceiver;
import com.utilnepal.R;

public class KeyboardActivity extends AppCompatActivity {

    private Boolean checkIfKeyboardSelected;
    private static String KEYBOARDID = "com.utilnepal/.NepaliKeyboard.NepaliKeyboardService";

    private InputMethodChangeReceiver mReceiver;
    private IntentFilter mIntentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        mReceiver = new InputMethodChangeReceiver();
        mIntentFilter =  new IntentFilter(Intent.ACTION_INPUT_METHOD_CHANGED);
        registerReceiver(mReceiver, mIntentFilter);

        checkIfKeyboardSelected = checkIfKeyboardIsSelected() ;

        if(checkIfKeyboardSelected)
        {
            ft.replace(R.id.fragmentHolderKeyboardActivity, new EnableNepaliKeyboardFragment());
            ft.commit();
        }

        else
        {
            ft.replace(R.id.fragmentHolderKeyboardActivity, new SelectNepaliKeyboardScreenFragment());
            ft.commit();

        }

    }

    private Boolean checkIfKeyboardIsSelected() {

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        String list = imm.getEnabledInputMethodList().toString();

        if(list.contains(KEYBOARDID))
        {
            Log.e("IT HAS RETURNED ", "TRUE");
            return true;
        }

        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter);
        String defaultIME = Settings.Secure.getString(getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD);
        if(EnableNepaliKeyboardFragment.clickButton !=null && EnableNepaliKeyboardFragment.enableKeyboardText !=null && EnableNepaliKeyboardFragment.editTextForTyping!=null && EnableNepaliKeyboardFragment.successInfo!=null)
        {
            EnableNepaliKeyboardFragment.checkIfKeyboardIsSelected(defaultIME);
        }
    }
    /* unregister the broadcast receiver */
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }

}
