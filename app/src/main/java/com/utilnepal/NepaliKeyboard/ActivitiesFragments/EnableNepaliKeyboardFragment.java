package com.utilnepal.NepaliKeyboard.ActivitiesFragments;

import android.os.Bundle;
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
import android.widget.EditText;
import android.widget.TextView;

import com.utilnepal.R;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class EnableNepaliKeyboardFragment extends Fragment {

    public static EditText editTextForTyping;
    public static TextView enableKeyboardText;
    public static Button clickButton;
    public static TextView successInfo;

    private String keyboard;
    private static final String MY_KEYBOARD_ID = "com.utilnepal/.NepaliKeyboard.NepaliKeyboardService";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.from(getContext()).inflate(R.layout.enable_nepali_keyboard_fragment,container,false);


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        clickButton = view.findViewById(R.id.enableKeyboardButton);
        editTextForTyping = view.findViewById(R.id.editTextForTyping);
        enableKeyboardText = view.findViewById(R.id.enableKeyboardText);
        successInfo = view.findViewById(R.id.successKeyboardText);

        keyboard = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD);
        checkIfKeyboardIsSelected(keyboard);

        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imeManager = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
                imeManager.showInputMethodPicker();
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }




    public static void checkIfKeyboardIsSelected(String keyboard) {


        if(keyboard.equals(MY_KEYBOARD_ID))
        {
            Log.e("IN SECTIOn", keyboard);
            enableKeyboardText.setVisibility(View.GONE);
            editTextForTyping.setVisibility(View.VISIBLE);
            clickButton.setVisibility(View.GONE);
            successInfo.setVisibility(View.VISIBLE);
        }

        else
        {
            Log.e("In Wrong Section", keyboard);
            enableKeyboardText.setVisibility(View.VISIBLE);
            editTextForTyping.setVisibility(View.GONE);
            clickButton.setVisibility(View.VISIBLE);
            successInfo.setVisibility(View.GONE);
        }

    }
}
