package com.utilnepal.MobileHelper.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.utilnepal.MobileHelper.data.EmergencyNumberFeatures;
import com.utilnepal.R;

import java.util.ArrayList;

public class EmergencyNumberAdapter extends RecyclerView.Adapter<EmergencyNumberAdapter.MyViewHolder> {

    private ArrayList<EmergencyNumberFeatures> emergencyNumbers;
    private Context c;

    public EmergencyNumberAdapter(ArrayList <EmergencyNumberFeatures> features, Context c)
    {
        emergencyNumbers = features;
        this.c = c;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(c).inflate(R.layout.emergency_numbers,viewGroup,false);
        return new EmergencyNumberAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.emergencyPlaceName.setText(emergencyNumbers.get(i).getEmergencyPlaceName());
        myViewHolder.emergencyPlaceNumber.setText(emergencyNumbers.get(i).getEmergencyPlaceNumber());

        myViewHolder.callingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number  =    emergencyNumbers.get(i).getEmergencyPlaceNumber();
                if(number.length() >3)
                {
                    if(!number.startsWith("01"))
                    {
                        number = "01"+number;
                    }
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(number)));
                    c.startActivity(intent);
                    Log.e("Number", number);
                }
                else if(number.length()==3)
                {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Uri.encode(number)));
                    c.startActivity(intent);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return emergencyNumbers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView emergencyPlaceName;
        private TextView emergencyPlaceNumber;
        private ImageView callingButton;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            emergencyPlaceName = itemView.findViewById(R.id.nameOfEmergencyPlace);
            emergencyPlaceNumber = itemView.findViewById(R.id.numberOfEmergencyPlace);
            callingButton = itemView.findViewById(R.id.callingButton);
        }
    }
}
