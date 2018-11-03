package com.utilnepal.MobileHelp.ActivitiesFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.utilnepal.MainActivity;
import com.utilnepal.MobileHelp.Files.EmergencyNumberFeatures;
import com.utilnepal.MobileHelp.Files.MiscellaneousNumberFeatures;
import com.utilnepal.MobileHelp.adapters.EmergencyNumberAdapter;
import com.utilnepal.MobileHelp.adapters.MiscellaneousNumberAdapter;
import com.utilnepal.MobileHelp.data.EmergencyNumbersGenerator;
import com.utilnepal.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmergencyFragment extends Fragment {

    private ArrayList<EmergencyNumberFeatures> features;
    private RecyclerView emergencyNumberRecyclerView;
    private EmergencyNumberAdapter emergencyNumberAdapter;

    public EmergencyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_emergency, container, false);
        emergencyNumberRecyclerView = v.findViewById(R.id.emergencyNumberRecyclerView);
        emergencyNumberAdapter = new EmergencyNumberAdapter(new EmergencyNumbersGenerator(getContext()).generateEmergencyNumber(),getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        emergencyNumberRecyclerView.setLayoutManager(mLayoutManager);
        emergencyNumberRecyclerView.setItemAnimator(new DefaultItemAnimator());
        emergencyNumberRecyclerView.setAdapter(emergencyNumberAdapter);

        return v;
    }



}
