package com.appofmy.android.goldprice1;

/**
 * Created by WIN on 4/27/2017.
 */

public class PriceOil {
    private String mDte;
    private String mDateRefresh;
    private String mPrice;

    /**
     * Constructs a new {@link Earthquake} object.
     * earthquake happened
     */

    public PriceOil(String date, String dateRefresh, String price) {
        mDte = date;
        mDateRefresh = dateRefresh;
        mPrice = price;


    }

    public String getmDte() {
        return mDte;
    }

    public String getmDateRefresh(){
        return mDateRefresh;
    }

    public String getmPrice(){
        return mPrice;
    }


}