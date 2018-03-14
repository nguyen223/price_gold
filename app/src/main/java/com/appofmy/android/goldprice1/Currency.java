package com.appofmy.android.goldprice1;

/**
 * Created by WIN on 7/23/2017.
 */

public class Currency {
    // Dat bien toan cau.
    private int mImageResouceFlag;

    private String mCurrencyCode;

    private String mCurrencyName;

    private String mValueCurrency;

    //Nha xay dung cho doi tuong moi.
    public Currency(int imageResouceFlag, String currencyCode, String currencyName, String valueCurrency) {
        mImageResouceFlag = imageResouceFlag;
        mCurrencyCode = currencyCode;
        mCurrencyName = currencyName;
        mValueCurrency = valueCurrency;
    }
    // Lay tai nguyen hinh anh.
    public int getmImageResouceFlag(){
        return mImageResouceFlag;
    }
    // Lay chuoi ma tien.
    public String getmCurrencyCode(){
        return mCurrencyCode;
    }
    // Lay chuoi ten tien.
    public String getmCurrencyName(){
        return mCurrencyName;
    }
    //Lay value rates.
    public String getmValueCurrency(){
        return mValueCurrency;
    }

}
