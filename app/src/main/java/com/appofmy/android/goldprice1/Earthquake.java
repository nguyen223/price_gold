package com.appofmy.android.goldprice1;

/**
 * Created by WIN on 4/27/2017.
 */

public class Earthquake {
    private String mDte;
    private String mDateRefresh;
    private String mUsdAM;
    private String mUsdPM;
    private String mGbpbAM;
    private String mGbpPM;
    private String mEuroAM;
    private String mEuroPM;

    /**
     * Constructs a new {@link Earthquake} object.
     * earthquake happened
     */

    public Earthquake(String date,String dateRefresh, String usdAM, String usdPM, String gbpAM, String gbpPM, String euroAM, String euroPM) {
        mDte = date;
        mUsdAM = usdAM;
        mUsdPM = usdPM;
        mGbpbAM = gbpAM;
        mGbpPM = gbpPM;
        mEuroAM = euroAM;
        mEuroPM = euroPM;
        mDateRefresh = dateRefresh;


    }

    public String getmDte() {
        return mDte;
    }

    public String getmUsdAM() {
        return mUsdAM;
    }

    public String getmUsdPM() {
        return mUsdPM;
    }

    public String getmGbpbAM() {
        return mGbpbAM;
    }

    public String getmGbpPM() {
        return mGbpPM;
    }

    public String getmEuroAM() {
        return mEuroAM;
    }

    public String getmEuroPM() {
        return mEuroPM;
    }
    public String getmDateRefresh(){
        return mDateRefresh;
    }


}