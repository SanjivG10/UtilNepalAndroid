package com.utilnepal.MobileHelper.data;

import android.content.Context;

import com.utilnepal.R;

import java.util.ArrayList;

public class EmergencyNumbersGenerator {

    private  ArrayList<EmergencyNumberFeatures> features;
    private Context c;

    public EmergencyNumbersGenerator(Context c)
    {
        this.c = c;
    }

    public ArrayList<EmergencyNumberFeatures> generateEmergencyNumber()
    {

        features = new ArrayList<>();

        EmergencyNumberFeatures em = new EmergencyNumberFeatures(
                c.getResources().getString(R.string.policeControl),
                c.getResources().getString(R.string.policeControlNumber)
        );

        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.policeEmergency), c.getResources().getString(R.string.policeEmergencyNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.kathmanduPolice), c.getResources().getString(R.string.kathmanduPoliceNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.lalitpurPolice), c.getResources().getString(R.string.lalitpurPoliceNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.bhaktapurPolice), c.getResources().getString(R.string.bhaktapurPoliceNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.paropkarAmbulance), c.getResources().getString(R.string.paropkarAmbulanceNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.lalitpurRedCrossAmbulance), c.getResources().getString(R.string.lalitpurRedCrossAmbulanceNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.bishalBazaarRedCrossAmbulance), c.getResources().getString(R.string.bishalBazaarRedCrossAmbulanceNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.redCrossAmbulance), c.getResources().getString(R.string.redCrossAmbulanceNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.agarwalSewaCenter), c.getResources().getString(R.string.agarwalSewaCenterNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.aasaraRehabilitationCenter), c.getResources().getString(R.string.aasaraRehabilitationCenterNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.neaplEyeBank), c.getResources().getString(R.string.neaplEyeBankNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.nepalEyeHospital), c.getResources().getString(R.string.nepalEyeHospitalNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.tilGangaeyeHospital), c.getResources().getString(R.string.tilGangaeyeHospitalNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.bitHospital), c.getResources().getString(R.string.birHospitalNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.nepalPoliceHospital), c.getResources().getString(R.string.nepalPoliceHospitalNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.tuTeachingHospital), c.getResources().getString(R.string.tuTeachingHospitalNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.maternityHospital), c.getResources().getString(R.string.maternityHospitalNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.tekuHospital), c.getResources().getString(R.string.tekuHospitalNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.patanHospital), c.getResources().getString(R.string.patanHospitalNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.bhaktapurHospital), c.getResources().getString(R.string.bhaktapurHospitalNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.mentalHospital), c.getResources().getString(R.string.mentalHospitalNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.kantiChildrenHospital), c.getResources().getString(R.string.kantiChildrenHospitalNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.kathmanduModelHospital), c.getResources().getString(R.string.kathmanduModelHospitalNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.bAndbHospital), c.getResources().getString(R.string.bAndbHospitalNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.mediaCareNationalHospital), c.getResources().getString(R.string.mediaCareNationalHospitalNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.mediaCareNationalHospitalAmbulance), c.getResources().getString(R.string.mediaCareNationalHospitalAmbulanceNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.nepalOrthoPedicHospital), c.getResources().getString(R.string.nepalOrthoPedicHospitalNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.teachingHospitalSinamangal), c.getResources().getString(R.string.teachingHospitalSinamangalNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.teachingHospitalJorpati), c.getResources().getString(R.string.teachingHospitalJorpatiNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.kantipurDentalHospital), c.getResources().getString(R.string.kantipurDentalHospitalNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.kantipurHospital), c.getResources().getString(R.string.kantipurHospitalNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.hospitalAndResearchCenter), c.getResources().getString(R.string.hospitalAndResearchCenterNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.norvicHospital), c.getResources().getString(R.string.norvicHospitalNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.gangalalNationalHeartCenter), c.getResources().getString(R.string.gangalalNationalHeartCenterNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.lifeCareHospital), c.getResources().getString(R.string.lifeCareHospitalNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.miteriHospital), c.getResources().getString(R.string.miteriHospitalNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.capitalHospital), c.getResources().getString(R.string.capitalHospitalNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.shreeSatyasahiCenter), c.getResources().getString(R.string.shreeSatyasahiCenterNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.bhaktapurRedCross), c.getResources().getString(R.string.bhaktapurRedCrossNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.nationalKidneyCenter), c.getResources().getString(R.string.nationalKidneyCenterNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.fireBrigade), c.getResources().getString(R.string.fireBrigadeNumber) );
        features.add(em);
        em = new EmergencyNumberFeatures( c.getResources().getString(R.string.bloodBank), c.getResources().getString(R.string.bloodBankNumber) );
        features.add(em);

        return features;
    }


}
