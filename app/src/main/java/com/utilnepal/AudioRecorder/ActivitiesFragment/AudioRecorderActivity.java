package com.utilnepal.AudioRecorder.ActivitiesFragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.utilnepal.AudioRecorder.adapters.AudioRecorderAdapter;
import com.utilnepal.R;

import java.io.File;
import java.io.IOException;

public class AudioRecorderActivity extends AppCompatActivity {

    //views
    private TabLayout tabLayoutHomePage;
    private ViewPager mViewPager;
    private Toolbar toolbar;
    private AudioRecorderAdapter mAudioRecorderAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audiorecorder);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mViewPager = findViewById(R.id.viewPagerHomePage);
        mAudioRecorderAdapter = new AudioRecorderAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAudioRecorderAdapter);
        tabLayoutHomePage = findViewById(R.id.tabLayoutHomePage);
        tabLayoutHomePage.setupWithViewPager(mViewPager);
    }

}
