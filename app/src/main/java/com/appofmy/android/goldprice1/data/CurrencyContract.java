package com.appofmy.android.goldprice1.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by WIN on 8/3/2017.
 */

public final class CurrencyContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.


    private CurrencyContract() {}
    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    public static final String CONTENT_AUTHORITY = "com.myapp.android.pricegold";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    /**
     * Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.example.android.pets/pets/ is a valid path for
     * looking at pet data. content://com.myapp.android.pricogold/staff/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "staff".
     */
    public static final String PATH_CURRENCY = "currency";
    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single currency.
     */
    public static final class CurrencyEntry implements BaseColumns{
        /** The content URI to access the pet data in the provider */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_CURRENCY);

        /** Name of database table for currency */
        public final static String TABLE_CURRENCY = "currency";

        /**
         * Unique ID number for the cyrrency (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Dates of value currency.
         *
         * Type: TEXT
         */
        public final static String DATES_VALUE = "dates";



        /**
         * Number Edittext currency of the currency.
         *
         * Type: INTEGER.
         */
        public final static String VALUE_EXCHANGE = "value_exchange";

        /**
         * mGenderSpinner of currency
         *
         * Type: INTEGER.
         */
        public final static String GENDER_SPINNER = "gender_spinner";
        /**
         * Text Currency exchange 1 of value currency.
         *
         * Type: TEXT
         */
        public final static String TEXTCURRENCY1 = "text_currency1";
        /**
         * mGender1 of currency
         *
         * Type: INTEGER.
         */
        public final static String GENDER1 = "gender1";
        /**
         * Text Currency exchange 2 of value currency.
         *
         * Type: TEXT
         */
        public final static String TEXTCURRENCY2 = "text_currency2";
        /**
         * mGender1 of currency
         *
         * Type: INTEGER.
         */
        public final static String GENDER2 = "gender2";
        /**
         * Text Currency exchange 3 of value currency.
         *
         * Type: TEXT
         */
        public final static String TEXTCURRENCY3 = "text_currency3";
        /**
         * mGender1 of currency
         *
         * Type: INTEGER.
         */
        public final static String GENDER3 = "gender3";
        /**
         * Possible values for the gender of the currency.
         *
         */
        public final static int GENDER_AUD = 0;
        public final static int GENDER_BGN = 1;
        public final static int GENDER_BRL = 2;
        public final static int GENDER_CAD = 3;
        public final static int GENDER_CHF = 4;
        public final static int GENDER_CNY = 5;
        public final static int GENDER_CZK = 6;
        public final static int GENDER_DKK = 7;
        public final static int GENDER_GBP = 8;
        public final static int GENDER_HKD = 9;
        public final static int GENDER_HRK = 10;
        public final static int GENDER_HUF = 11;
        public final static int GENDER_IDR = 12;
        public final static int GENDER_ILS = 13;
        public final static int GENDER_INR = 14;
        public final static int GENDER_JPY = 15;
        public final static int GENDER_KRW = 16;
        public final static int GENDER_MXN = 17;
        public final static int GENDER_MYR = 18;
        public final static int GENDER_NOK = 19;
        public final static int GENDER_NZD = 20;
        public final static int GENDER_PHP = 21;
        public final static int GENDER_PLN = 22;
        public final static int GENDER_RON = 23;
        public final static int GENDER_RUB = 24;
        public final static int GENDER_SEK = 25;
        public final static int GENDER_SGD = 26;
        public final static int GENDER_THB = 27;
        public final static int GENDER_TRY = 28;
        public final static int GENDER_ZAR = 29;
        public final static int GENDER_EUR = 30;
        public final static int GENDER_USD = 31;
        //Set array int gender.
        public final static int[] genderArray = {GENDER_AUD, GENDER_BGN, GENDER_BRL, GENDER_CAD, GENDER_CHF, GENDER_CNY, GENDER_CZK, GENDER_DKK, GENDER_GBP
        ,GENDER_HKD, GENDER_HRK, GENDER_HUF, GENDER_IDR, GENDER_ILS, GENDER_INR, GENDER_JPY, GENDER_KRW, GENDER_MXN, GENDER_MYR, GENDER_NOK, GENDER_NZD,
        GENDER_PHP, GENDER_PLN, GENDER_RON, GENDER_RUB, GENDER_SEK, GENDER_SGD, GENDER_THB, GENDER_TRY, GENDER_ZAR, GENDER_EUR, GENDER_USD};







    }

}
