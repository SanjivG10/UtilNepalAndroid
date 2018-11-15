package com.utilnepal.NepaliKeyboard.fragments;

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

    private EditText editTextForTyping;
    private TextView enableKeyboardText;
    private Button clickButton;

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

        checkIfKeyboardIsSelected();

        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imeManager = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
                imeManager.showInputMethodPicker();
                checkIfKeyboardIsSelected();
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    private void checkIfKeyboardIsSelected() {

        String keyboard = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD);

        if(keyboard.equals(MY_KEYBOARD_ID))
        {
            Log.e("IN SECTIOn", "hh");
            enableKeyboardText.setVisibility(View.GONE);
            editTextForTyping.setVisibility(View.VISIBLE);
            clickButton.setVisibility(View.GONE);
        }

    }
}
