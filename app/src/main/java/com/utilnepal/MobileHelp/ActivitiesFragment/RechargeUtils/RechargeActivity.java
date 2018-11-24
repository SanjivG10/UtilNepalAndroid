package com.utilnepal.MobileHelp.ActivitiesFragment.RechargeUtils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.utilnepal.R;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class RechargeActivity extends AppCompatActivity {

    private Button doneButton;
    private TextRecognizer textRecognizer;
    private static final int RequestCameraPermissionID = 1001;
    private String numberToDail;

    private EditText codeGenerated;
//    private CameraView cameraView;
    private Camera mOriginalCamera;


    // old methods
    public static SurfaceView oldSurfaceView;
    CameraSource oldCameraSource;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);

        doneButton = findViewById(R.id.rechargeDoneButton);
        codeGenerated = findViewById(R.id.generated_code);
        doneButton.setEnabled(false);
        numberToDail = "";
        getPermission();


        oldSurfaceView = findViewById(R.id.surface_camera_preview);
        textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

        final String type = getIntent().getStringExtra("type");
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("NTC")) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Uri.encode("*412*" + numberToDail + "#")));
                    startActivity(intent);
                } else if (type.equals("NCELL")) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Uri.encode("*102*" + numberToDail + "#")));
                    startActivity(intent);
                }
            }
        });


        codeGenerated.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                CheckNumberToDail();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                CheckNumberToDail();

            }

            @Override
            public void afterTextChanged(Editable s) {
                CheckNumberToDail();

            }
        });


        if (!textRecognizer.isOperational()) {
            Log.w("MainActivity", "Detector dependencies are not yet available");
        } else {

            oldCameraSource = new CameraSource.Builder(getApplicationContext(), textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024)
                    .setRequestedFps(15.0f)
                    .setAutoFocusEnabled(true)
                    .build();



            oldSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder surfaceHolder) {

                    try {
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                            ActivityCompat.requestPermissions(RechargeActivity.this,
                                    new String[]{Manifest.permission.CAMERA},
                                    RequestCameraPermissionID);
                            return;
                        }
                        oldCameraSource.start(oldSurfaceView.getHolder());

                        Camera getCamera  = CameraFixVisionApi.getCamera(oldCameraSource);
                        if(getCamera!=null)
                        {
                            mOriginalCamera = getCamera;
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    if(mOriginalCamera!=null)
                    {
                        oldCameraSource.stop();
                    }
                }
            });




            // focus on Click
            oldSurfaceView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if(mOriginalCamera!=null)
                   {
                       try
                       {
                            mOriginalCamera.cancelAutoFocus();
                           Camera.Parameters parameters = mOriginalCamera.getParameters();

                           if(parameters.isZoomSupported())
                           {
                               int maxZoom = parameters.getMaxZoom();
                               Log.e(" Max zoom ", String.valueOf(maxZoom));
                               if(maxZoom > 5)
                               {
                                   parameters.setZoom(3);
                               }
                           }

                           List<String> focusModes = parameters.getSupportedFocusModes();

                           if(focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO ))
                           {
                               parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO );
                           }

                           if(focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)){

                               parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                           }
                           else if(focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)){
                               parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                           }


                           int maxFocusArea= parameters.getMaxNumFocusAreas();
                           if(maxFocusArea!=0)
                           {
                               Rect focusArea = new Rect();
                               focusArea = SelectedView.focusRect;

                               List<Camera.Area> focusList = new ArrayList<Camera.Area>();
                               Camera.Area cameraArea = new Camera.Area(focusArea, 1000);
                               focusList.add(cameraArea);

                               parameters.setFocusAreas(focusList);
                               parameters.setMeteringAreas(focusList);
                           }




                           mOriginalCamera.setDisplayOrientation(90);

                           mOriginalCamera.setParameters(parameters);

                           mOriginalCamera.autoFocus(new Camera.AutoFocusCallback() {
                               @Override
                               public void onAutoFocus(boolean success, Camera camera) {

                               }
                           });

                       }
                       catch (Exception e)
                       {
                           Log.e(" EXCEPTION"," EXCEPTION");
                       }


                   }




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
                        codeGenerated.post(new Runnable() {
                            @Override
                            public void run() {
                                StringBuilder stringBuilder = new StringBuilder();
                                for(int i =0;i<items.size();++i)
                                {
                                    TextBlock item = items.valueAt(i);
                                    stringBuilder.append(item.getValue());
                                    stringBuilder.append("\n");
                                }
                                String[] lines = stringBuilder.toString().split("\\n");
                                for (String line : lines)
                                {
                                    String numberIwantToDail = getOnlyNumbers(line);
                                    if(checkIfDigitIsCorrect(numberIwantToDail))
                                    {
                                        numberToDail = numberIwantToDail;
                                        break;
                                    }

                                }

                                codeGenerated.setText(numberToDail);
                            }
                        });
                    }
                }
            });
        }
    }



    private void CheckNumberToDail()
    {
        numberToDail = codeGenerated.getText().toString();
        numberToDail = getOnlyNumbers(numberToDail);
        if(checkIfDigitIsCorrect(numberToDail))
        {

            doneButton.setEnabled(true);

        }
        else
        {
            doneButton.setEnabled(false);

        }
    }

    private void getPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, RequestCameraPermissionID);

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
                }

            }
            break;
        }
    }





    private void setCamFocusMode(){

        if(null == mOriginalCamera) {

            return;
        }
        mOriginalCamera.cancelAutoFocus();

        /* Set Auto focus */
        Camera.Parameters parameters = mOriginalCamera.getParameters();
        List<String> focusModes = parameters.getSupportedFocusModes();
        Log.e("FOCUS MODE", focusModes.toString());

        if(focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO ))
        {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO );
        }

        if(focusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)){
            Log.e("COTNI MODE", "COTNI");

            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        }
        else if(focusModes.contains(Camera.Parameters.FOCUS_MODE_AUTO)){
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        }

        int maxFocusArea= parameters.getMaxNumFocusAreas();
        if(maxFocusArea!=0)
        {
            Rect focusArea = new Rect();
            ArrayList<Camera.Area> focusAreas = new ArrayList<Camera.Area>();
            focusAreas.add(new Camera.Area(focusArea, 1000));

            parameters.setFocusAreas(focusAreas);
        }

        mOriginalCamera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                if(success)
                {

                }
            }
        });
        mOriginalCamera.setParameters(parameters);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(mOriginalCamera!=null)
        {
            try{
                mOriginalCamera = Camera.open(0);
                mOriginalCamera.setDisplayOrientation(90);
                mOriginalCamera.startPreview();
            } catch (RuntimeException ex){
                Toast.makeText(this, " Camera Could not be opened ", Toast.LENGTH_LONG).show();
            }
        }

    }


    @Override
    protected void onPause() {
        if(mOriginalCamera != null) {
            mOriginalCamera.stopPreview();
            mOriginalCamera.release();
            mOriginalCamera = null;
        }
        super.onPause();
    }

}

