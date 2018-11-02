package com.utilnepal.MobileHelp.Files;

public class EmergencyNumberFeatures {

    private String emergencyPlaceName;
    private String emergencyPlaceNumber;

    public EmergencyNumberFeatures(String emergencyPlaceName, String emergencyPlaceNumber) {
        this.emergencyPlaceName = emergencyPlaceName;
        this.emergencyPlaceNumber = emergencyPlaceNumber;
    }

    public String getEmergencyPlaceName() {
        return emergencyPlaceName;
    }

    public void setEmergencyPlaceName(String emergencyPlaceName) {
        this.emergencyPlaceName = emergencyPlaceName;
    }

    public String getEmergencyPlaceNumber() {
        return emergencyPlaceNumber;
    }

    public void setEmergencyPlaceNumber(String emergencyPlaceNumber) {
        this.emergencyPlaceNumber = emergencyPlaceNumber;
    }
}
