package com.appofmy.android.goldprice1.data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.appofmy.android.goldprice1.CurrencyConversion;
import com.appofmy.android.goldprice1.data.CurrencyContract.CurrencyEntry;

/**
 * Created by WIN on 8/4/2017.
 */

public class CurrencyDbHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = CurrencyDbHelper.class.getSimpleName();
    /** Name of the database file */
    private static final String DATABASE_NAME = "tabcurrency.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    public CurrencyDbHelper(CurrencyConversion context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table

        String SQL_CREAT_CURRENCY_TABLE = "CREATE TABLE " + CurrencyEntry.TABLE_CURRENCY + "("
                + CurrencyEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CurrencyEntry.DATES_VALUE + " TEXT, "
                + CurrencyEntry.VALUE_EXCHANGE + " LONG NOT NULL DEFAULT 1, "
                + CurrencyEntry.GENDER_SPINNER + " INTEGER NOT NULL, "
                + CurrencyEntry.TEXTCURRENCY1 + " TEXT NOT NULL, "
                + CurrencyEntry.GENDER1 + " INTEGER NOT NULL, "
                + CurrencyEntry.TEXTCURRENCY2 + " TEXT NOT NULL, "
                + CurrencyEntry.GENDER2 + " INTEGER NOT NULL, "
                + CurrencyEntry.TEXTCURRENCY3 + " TEXT NOT NULL, "
                + CurrencyEntry.GENDER3 + " INTEGER NOT NULL); ";
        // Execute the SQL statement
        db.execSQL(SQL_CREAT_CURRENCY_TABLE);



    }
    /**
     * This is called when the database needs to be upgraded.
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
