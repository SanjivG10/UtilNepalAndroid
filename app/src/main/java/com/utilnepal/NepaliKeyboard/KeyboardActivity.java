package com.utilnepal.NepaliKeyboard;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.utilnepal.NepaliKeyboard.fragments.EnableNepaliKeyboardFragment;
import com.utilnepal.NepaliKeyboard.fragments.SelectNepaliKeyboardScreenFragment;
import com.utilnepal.R;

public class KeyboardActivity extends AppCompatActivity {

    private Boolean checkIfKeyboardSelected;
    private static String KEYBOARDID = "com.utilnepal/.NepaliKeyboard.NepaliKeyboardService";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);


        checkIfKeyboardSelected = checkIfKeyboardIsSelected() ;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

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
            return true;
        }

        return false;
    }

}
