package com.utilnepal.MobileHelp.ActivitiesFragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.utilnepal.R;

import java.io.IOException;

public class RechargeActivity extends AppCompatActivity {

    private Button doneButton;
    private SurfaceView mSurfaceView;
    private TextView mTextView;
    private TextRecognizer textRecognizer;
    private Context context;
    private CameraSource mCameraSource;
    private static final int RequestCameraPermissionID = 1001;
    private String numberToDail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);

        doneButton = findViewById(R.id.rechargeDoneButton);
        mSurfaceView = findViewById(R.id.surface_camera_preview);
        mTextView = findViewById(R.id.generated_code);

        doneButton.setEnabled(false);

        getPermission();

        final String type= getIntent().getStringExtra("type");

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type=="NTC")
                {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Uri.encode("*412*"+numberToDail+"#")));
                    startActivity(intent);
                }

                else if(type=="NCELL")
                {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Uri.encode("*102*"+numberToDail+"#")));
                    startActivity(intent);
                }
            }
        });

        textRecognizer = new TextRecognizer.Builder(this).build();

        //checking if textRecognizer is operational
        if (!textRecognizer.isOperational()) {
            Toast.makeText(this, "Dependency not available", Toast.LENGTH_SHORT).show();


            IntentFilter lowstorageFilter = new IntentFilter(Intent.ACTION_DEVICE_STORAGE_LOW);
            boolean hasLowStorage = registerReceiver(null, lowstorageFilter) != null;

            if (hasLowStorage) {
                Toast.makeText(this, "VERY LOW STORAGE", Toast.LENGTH_LONG).show();
            }



            return;
        }

        mCameraSource = new CameraSource.Builder(this, textRecognizer)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedPreviewSize(1280, 1024)
                .setAutoFocusEnabled(true)
                .setRequestedFps(2.0f)
                .build();

        mSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(RechargeActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(RechargeActivity.this,
                                new String[]{Manifest.permission.CAMERA},
                                RequestCameraPermissionID);
                        return;
                    }
                    mCameraSource.start(mSurfaceView.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                mCameraSource.stop();
            }
        });




        textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<TextBlock> detections) {

                final SparseArray<TextBlock> items = detections.getDetectedItems();
                if(items.size() != 0)
                {
                    mTextView.post(new Runnable() {
                        @Override
                        public void run() {
                            StringBuilder stringBuilder = new StringBuilder();
                            for(int i =0;i<items.size();++i)
                            {
                                TextBlock item = items.valueAt(i);
                                stringBuilder.append(item.getValue());
                                stringBuilder.append("\n");
                            }
                            String myText = stringBuilder.toString();

                            String onlyDigits = getOnlyNumbers(myText);
                            mTextView.setText(myText);

                            if(checkIfDigitIsCorrect(onlyDigits))
                            {
                                doneButton.setEnabled(true);
                                numberToDail = onlyDigits;
                            }
                            else
                            {
                                doneButton.setEnabled(false);
                            }
                        }
                    });
                }
            }
        });

    }

    private void getPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 777);

        }
    }

    private Boolean checkIfDigitIsCorrect(String onlyDigits) {
        return onlyDigits.length()==16;
    }

    private String getOnlyNumbers(String myText) {
        String str = myText.replaceAll("\\D+","");
        return str;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCameraPermissionID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        mCameraSource.start(mSurfaceView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
        }
    }
}

