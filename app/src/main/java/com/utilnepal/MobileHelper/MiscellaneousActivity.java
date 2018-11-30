package com.utilnepal.MobileHelper;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.utilnepal.MobileHelper.adapters.EmergencyNumberAdapter;
import com.utilnepal.MobileHelper.data.EmergencyNumberFeatures;
import com.utilnepal.MobileHelper.data.EmergencyNumbersGenerator;
import com.utilnepal.R;

import java.util.ArrayList;

public class MiscellaneousActivity extends AppCompatActivity {

    private ArrayList<EmergencyNumberFeatures> features;
    private RecyclerView emergencyNumberRecyclerView;
    private EmergencyNumberAdapter emergencyNumberAdapter;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miscellaneous);

        toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        emergencyNumberRecyclerView = findViewById(R.id.emergencyNumberRecyclerView);
        emergencyNumberAdapter = new EmergencyNumberAdapter(new EmergencyNumbersGenerator(this).generateEmergencyNumber(),this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        emergencyNumberRecyclerView.setLayoutManager(mLayoutManager);
        emergencyNumberRecyclerView.setItemAnimator(new DefaultItemAnimator());
        emergencyNumberRecyclerView.setAdapter(emergencyNumberAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mobile_activity_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.source:
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

                final AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.setMessage("This phone numbers are derived from https://www.nepalpolice.gov.np/.Any numbers that were present during the extraction  are kept here.");
                alertDialog.setCancelable(true);
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.cancel();
                    }
                });

                alertDialog.show();
                break;
        }

        return false;
    }
}
