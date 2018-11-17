package com.utilnepal.MobileHelp.ActivitiesFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.utilnepal.MobileHelp.Files.MiscellaneousNumberFeatures;
import com.utilnepal.R;
import com.utilnepal.MobileHelp.adapters.MiscellaneousNumberAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MiscellaneousFragment extends Fragment {

    private ArrayList<MiscellaneousNumberFeatures> features;
    private RecyclerView misNumbers;
    private MiscellaneousNumberAdapter miscellaneousNumberAdapter;

    public MiscellaneousFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_miscellaneous, container, false);

        misNumbers = view.findViewById(R.id.misNumbers);

        miscellaneousNumberAdapter = new MiscellaneousNumberAdapter(addFeatures(),getContext(),getActivity().getSupportFragmentManager());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        misNumbers.setLayoutManager(mLayoutManager);
        misNumbers.setItemAnimator(new DefaultItemAnimator());
        misNumbers.setAdapter(miscellaneousNumberAdapter);


        return view;
    }

    private ArrayList<MiscellaneousNumberFeatures> addFeatures() {

        features = new ArrayList<>();
        MiscellaneousNumberFeatures nm = new MiscellaneousNumberFeatures(
                getResources().getString(R.string.balance),
                getResources().getString(R.string.balance_info),
                "*901#",
                "*400#",
                "");
        features.add(nm);

//        nm = new MiscellaneousNumberFeatures(
//                getResources().getString(R.string.add_balance),
//                getResources().getString(R.string.add_balance_info),
//                "*903#",
//                "*9#",
//                "");
//        features.add(nm);



        nm = new MiscellaneousNumberFeatures(
                getResources().getString(R.string.mobile_number),
                getResources().getString(R.string.mobile_number_info),
                "*903#",
                "*9#",
                "");
        features.add(nm);
        nm = new MiscellaneousNumberFeatures(
                getResources().getString(R.string.data_pack),
                getResources().getString(R.string.data_pack_info),
                "*17123#",
                "*1415#",
                "");
        features.add(nm);
        nm = new MiscellaneousNumberFeatures(
                getResources().getString(R.string.call_forward),
                getResources().getString(R.string.call_forward_info),
                "*#67#",
                "*#67#",
                "");
        features.add(nm);
        return features;
    }


}
