package com.utilnepal.MobileHelp.Files;

public class MiscellaneousNumberFeatures {

    private String featuresName;
    private String detailName;
    private String ncellDialNumber;
    private String ntcDialNumber;
    private String getTempNumber2;

    public MiscellaneousNumberFeatures(String featuresName, String detailName, String ncellDialNumber, String ntcDialNumber, String getTempNumber2) {
        this.featuresName = featuresName;
        this.detailName = detailName;
        this.ncellDialNumber = ncellDialNumber;
        this.ntcDialNumber = ntcDialNumber;
        this.getTempNumber2 = getTempNumber2;
    }

    public String getFeaturesName() {
        return featuresName;
    }

    public void setFeaturesName(String featuresName) {
        this.featuresName = featuresName;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    public String getNcellDialNumber() {
        return ncellDialNumber;
    }

    public void setNcellDialNumber(String ncellDialNumber) {
        this.ncellDialNumber = ncellDialNumber;
    }

    public String getNtcDialNumber() {
        return ntcDialNumber;
    }

    public void setNtcDialNumber(String ntcDialNumber) {
        this.ntcDialNumber = ntcDialNumber;
    }

    public String getGetTempNumber2() {
        return getTempNumber2;
    }

    public void setGetTempNumber2(String getTempNumber2) {
        this.getTempNumber2 = getTempNumber2;
    }
}
