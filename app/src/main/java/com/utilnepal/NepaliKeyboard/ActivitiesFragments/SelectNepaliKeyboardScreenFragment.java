package com.utilnepal.NepaliKeyboard.ActivitiesFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.utilnepal.R;

import java.util.Timer;
import java.util.TimerTask;

public class SelectNepaliKeyboardScreenFragment extends Fragment {

    private static String KEYBOARDID = "com.utilnepal/.NepaliKeyboard.NepaliKeyboardService";
    public static Timer timer;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.from(getContext()).inflate(R.layout.select_nepali_keyboard_fragment,container,false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Button selectNepaliKeyboard;

        selectNepaliKeyboard = view.findViewById(R.id.selectKeyboardButton);
        selectNepaliKeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkIfKeyboardIsEnabled())
                {
                    enableKeyboard();
                }

            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    private void enableKeyboard()
    {
        Intent enableIntent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
        enableIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(enableIntent);
        Log.e("Enable Keyboard",  "Checking if it has any value");

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                checkIfKeyboardIsEnabled();
            }
        }, 0, 3000);


    }

    private Boolean checkIfKeyboardIsEnabled() {

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        String list = imm.getEnabledInputMethodList().toString();

        Log.e("Listing", list);

        if(list.contains(KEYBOARDID))
        {
            timer.purge();
            timer.cancel();
            Intent intent = new Intent(getContext(), KeyboardActivity.class);
            startActivity(intent);
            getActivity().finish();
            return true;
        }

        return false;
    }



}
