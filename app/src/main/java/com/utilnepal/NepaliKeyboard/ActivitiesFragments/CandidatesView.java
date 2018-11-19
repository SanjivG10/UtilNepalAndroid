package com.utilnepal.NepaliKeyboard.ActivitiesFragments;

import android.content.Context;
import android.view.View;

import com.utilnepal.NepaliKeyboard.NepaliKeyboardService;


public class CandidatesView extends View {

    private NepaliKeyboardService mService;

    public CandidatesView(Context context) {
        super(context);
    }



    public void setService(NepaliKeyboardService listener) {
        mService = listener;
    }


}
