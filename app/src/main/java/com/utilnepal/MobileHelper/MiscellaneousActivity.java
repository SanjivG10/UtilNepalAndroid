package com.utilnepal.MobileHelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.utilnepal.MobileHelper.adapters.EmergencyNumberAdapter;
import com.utilnepal.MobileHelper.data.EmergencyNumberFeatures;
import com.utilnepal.MobileHelper.data.EmergencyNumbersGenerator;
import com.utilnepal.R;

import java.util.ArrayList;

public class MiscellaneousActivity extends AppCompatActivity {

    private ArrayList<EmergencyNumberFeatures> features;
    private RecyclerView emergencyNumberRecyclerView;
    private EmergencyNumberAdapter emergencyNumberAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miscellaneous);


        emergencyNumberRecyclerView = findViewById(R.id.emergencyNumberRecyclerView);
        emergencyNumberAdapter = new EmergencyNumberAdapter(new EmergencyNumbersGenerator(this).generateEmergencyNumber(),this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        emergencyNumberRecyclerView.setLayoutManager(mLayoutManager);
        emergencyNumberRecyclerView.setItemAnimator(new DefaultItemAnimator());
        emergencyNumberRecyclerView.setAdapter(emergencyNumberAdapter);

    }
}
