package com.utilnepal.MobileHelp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.utilnepal.MobileHelp.Files.EmergencyNumberFeatures;
import com.utilnepal.MobileHelp.Files.MiscellaneousNumberFeatures;
import com.utilnepal.R;

import org.w3c.dom.Text;

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
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.emergencyPlaceName.setText(emergencyNumbers.get(i).getEmergencyPlaceName());
        myViewHolder.emergencyPlaceNumber.setText(emergencyNumbers.get(i).getEmergencyPlaceNumber());

    }

    @Override
    public int getItemCount() {
        return emergencyNumbers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView emergencyPlaceName;
        private TextView emergencyPlaceNumber;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            emergencyPlaceName = itemView.findViewById(R.id.nameOfEmergencyPlace);
            emergencyPlaceNumber = itemView.findViewById(R.id.numberOfEmergencyPlace);
        }
    }
}
