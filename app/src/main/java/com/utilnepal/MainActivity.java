package com.utilnepal;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.utilnepal.AudioRecorder.ActivitiesFragment.AudioRecorderActivity;
import com.utilnepal.DateConverter.DateConverterActivity;
import com.utilnepal.MobileHelp.ActivitiesFragment.MobileActivity;
import com.utilnepal.NepaliKeyboard.ActivitiesFragments.KeyboardActivity;
import com.utilnepal.QRCodeScanner.QRCodeActivity;

public class MainActivity extends AppCompatActivity {

    private CardView torchCardView;
    private CardView keyboardCardView;
    private CardView audioRecordCardView;
    private CardView mobileHelpCardView;
    private CardView dateconverterCardView;
    private CardView qrCodeScannerCardView;
    private ImageView torchImageView;
    private String cameraId;

    //for torch light!
    private Boolean is_flash_on;
    private String checkIfFlashOnString;
    private Boolean hasFlash;
    private android.hardware.Camera camera;
    private android.hardware.Camera.Parameters p;
    private AdView adView;

    //for higherAPI
    private static final int CAMERA_REQUEST = 50;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        BottomNavigationView bottomNavigationView = (BottomNavigationView)
//                findViewById(R.id.bottom_navigation);
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(
//                new BottomNavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                        switch (item.getItemId()) {
//                            case R.id.navigation_audioRecorder:
//
//                                break;
//                            case R.id.navigation_torch:
//
//                                break;
//                            case R.id.navigation_keyboard:
//
//                                break;
//                            case R.id.navigation_mobile_help:
//
//                                break;
//                            case R.id.navigation_date_converter:
//
//                                break;
//                            case R.id.navigation_qr_code_scanner:
//
//                                break;
//                        }
//                        return false;
//                    }
//                });

        initialize();
//
//        MobileAds.initialize(this,"ca-app-pub-6365618181796618~5539213127");
//        adView = findViewById(R.id.mainActivityAdView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);


        hasFlash= getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        is_flash_on = false;

    }


    private void initialize() {
        // get camera permission!
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, 50);
        }

        torchCardView = findViewById(R.id.torchCardView);
        keyboardCardView = findViewById(R.id.keyboardCardView);
        audioRecordCardView = findViewById(R.id.audioRecordCardView);
        mobileHelpCardView = findViewById(R.id.mobileHelpCardView);
        dateconverterCardView = findViewById(R.id.dateconverterCardView);
        qrCodeScannerCardView = findViewById(R.id.qrCodeScannerCardView);
        torchImageView = findViewById(R.id.torchImageView);

        torchCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(hasFlash)
                {
                    if (is_flash_on) {
                    // turn off flash
                    turnOffFlash();
                    } else {
                        turnOnFlash();
                    }
                }

                else
                {
                    Toast.makeText(getApplicationContext(), "Flash not supported", Toast.LENGTH_SHORT).show();
                }


            }
        });

        mobileHelpCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MobileActivity.class);
                startActivity(intent);
            }
        });

        audioRecordCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AudioRecorderActivity.class);
                startActivity(intent);
            }
        });

        dateconverterCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DateConverterActivity.class);
                startActivity(intent);
            }
        });

        keyboardCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,KeyboardActivity.class);
                startActivity(intent);
            }
        });

        qrCodeScannerCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,QRCodeActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getCamera() {
        if (camera == null) {
            try {
                camera = Camera.open();
                p = camera.getParameters();
            } catch (RuntimeException e) {
                Log.e("Camera Null", "Camera is null");
            }
        }
    }



    private void turnOnFlash() {
        if (!is_flash_on) {
            if (camera == null || p == null) {
                return;
            }

            p = camera.getParameters();
            p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(p);
            camera.startPreview();
            is_flash_on = true;
            torchImageView.setImageResource(R.drawable.ic_lightbulb_outline_yellow_64dp);

            // changing button/switch image
        }

    }


    private void turnOffFlash() {
        if (is_flash_on) {
            if (camera == null || p == null) {
                return;
            }

            p = camera.getParameters();
            p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(p);
            camera.stopPreview();
            is_flash_on = false;

            // changing button/switch image
            torchImageView.setImageResource(R.drawable.ic_lightbulb_outline_black_24dp);
        }
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // on pause turn off the flash
        turnOffFlash();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStart() {
        super.onStart();

        getCamera();
    }

    @Override
    protected void onStop() {
        super.onStop();
        turnOffFlash();

        // on stop release the camera
        if (camera != null) {
            camera.release();

            camera = null;
        }
        turnOffFlash();
    }
}



