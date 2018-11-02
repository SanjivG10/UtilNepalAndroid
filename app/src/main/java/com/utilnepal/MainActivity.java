package com.utilnepal;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.utilnepal.MobileHelp.ActivitiesFragment.MobileActivity;

public class MainActivity extends AppCompatActivity {

    private CardView torchCardView;
    private CardView keyboardCardView;
    private CardView trendingCardView;
    private CardView mobileHelpCardView;
    private CardView newsCardView;
    private CardView recommenededCardView;
    private ImageView torchImageView;

    //for torch light!
    private Boolean is_flash_on;
    private Boolean hasFlash;
    private android.hardware.Camera camera;
    private android.hardware.Camera.Parameters p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

        is_flash_on = false;
        hasFlash= getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

    }

    private void initialize() {
        torchCardView = findViewById(R.id.torchCardView);
        keyboardCardView = findViewById(R.id.keyboardCardView);
        trendingCardView = findViewById(R.id.trendingCardView);
        mobileHelpCardView = findViewById(R.id.mobileHelpCardView);
        newsCardView = findViewById(R.id.newsCardView);
        recommenededCardView = findViewById(R.id.recommendedCardView);
        torchImageView = findViewById(R.id.torchImageView);

        torchCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                torch_func();
            }
        });

        mobileHelpCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MobileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void torch_func() {



        if (hasFlash) {
            if (is_flash_on)
                flashLightOff();
            else
                flashLightOn();
        } else {
            Toast.makeText(MainActivity.this, "No flash available on your device",
                    Toast.LENGTH_SHORT).show();
        }

    }

    private void flashLightOff() {
        try {
            torchImageView.setImageResource(R.drawable.ic_lightbulb_outline_black_24dp);
            camera.stopPreview();
            camera.release();
            is_flash_on = false;
        }
        catch (Exception e)
        {
            Toast.makeText(this," Unknown Error Occured",Toast.LENGTH_SHORT).show();
        }

    }

    private void flashLightOn()
    {
        try
        {
            torchImageView.setImageResource(R.drawable.ic_lightbulb_outline_yellow_64dp);
            camera = android.hardware.Camera.open();
            p = camera.getParameters();
            p.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(p);
            camera.startPreview();
            is_flash_on=true;
        }
        catch (Exception e)
        {
            Toast.makeText(this,"Unknown Error Occured",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(is_flash_on)
        {
            flashLightOff();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(is_flash_on)
        {
            flashLightOff();
        }
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(is_flash_on)
        {
            flashLightOn();
        }
    }
}



