package com.utilnepal.MobileHelp.adapters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.utilnepal.MainActivity;
import com.utilnepal.MobileHelp.Files.MiscellaneousNumberFeatures;
import com.utilnepal.R;

import java.util.ArrayList;

public class MiscellaneousNumberAdapter extends RecyclerView.Adapter<MiscellaneousNumberAdapter.MyViewHolder> {

    private ArrayList<MiscellaneousNumberFeatures> featuresSim;
    private Context c;

    public MiscellaneousNumberAdapter(ArrayList <MiscellaneousNumberFeatures> features, Context c)
    {
        this.featuresSim = features;
        this.c = c;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(c).inflate(R.layout.each_features_miscellaneous,viewGroup,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {
//        myViewHolder.eachFeaturesName.setText(featuresSim.get(i));
        myViewHolder.eachFeaturesName.setText(featuresSim.get(position).getFeaturesName());
        myViewHolder.eachRelativeLayoutFeatures.setText(featuresSim.get(position).getDetailName());

        myViewHolder.eachFeaturesName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideUnhideDetailLayout(position);
            }

            private void hideUnhideDetailLayout(int position) {
                if (myViewHolder.eachRelativeLayout.getVisibility()==View.GONE)
                {
                    myViewHolder.eachRelativeLayout.setVisibility(View.VISIBLE);
                }

                else if (myViewHolder.eachRelativeLayout.getVisibility()==View.VISIBLE)
                {
                    myViewHolder.eachRelativeLayout.setVisibility(View.GONE);
                }
            }
        });

        myViewHolder.ncellCallButton.setOnClickListener(new View.OnClickListener() {
            String ncellNumber = featuresSim.get(position).getNcellDialNumber();
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Uri.encode(ncellNumber)));
                c.startActivity(intent);

            }
        });

        myViewHolder.ntcCallButton.setOnClickListener(new View.OnClickListener() {
            String ntcNumber = featuresSim.get(position).getNtcDialNumber();
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Uri.encode(ntcNumber)));
                c.startActivity(intent);
            }
        });




    }



    @Override
    public int getItemCount() {
        return featuresSim.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView eachFeaturesName;
        private TextView eachRelativeLayoutFeatures;
        private Button ncellCallButton;
        private Button ntcCallButton;
        private RelativeLayout eachRelativeLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            eachFeaturesName = itemView.findViewById(R.id.eachFeaturesName);
            eachRelativeLayoutFeatures = itemView.findViewById(R.id.eachRelativeLayoutFeatures);
            ncellCallButton = itemView.findViewById(R.id.ncellCallButton);
            ntcCallButton = itemView.findViewById(R.id.ntcCallButton);
            eachRelativeLayout = itemView.findViewById(R.id.eachRelativeLayout);

        }
    }

}