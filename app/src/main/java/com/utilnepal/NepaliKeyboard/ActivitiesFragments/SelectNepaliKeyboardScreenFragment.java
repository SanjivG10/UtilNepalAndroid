package com.utilnepal.NepaliKeyboard.ActivitiesFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.utilnepal.R;

public class SelectNepaliKeyboardScreenFragment extends Fragment {

    private static String KEYBOARDID = "com.utilnepal/.NepaliKeyboard.NepaliKeyboardService";



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
                Boolean x  = checkIfKeyboardIsSelected();
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    private Boolean checkIfKeyboardIsSelected() {
        Intent enableIntent = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
        enableIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(enableIntent);

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        String list = imm.getEnabledInputMethodList().toString();


        if(list.contains(KEYBOARDID))
        {
            return true;
        }

        return false;
    }



}
