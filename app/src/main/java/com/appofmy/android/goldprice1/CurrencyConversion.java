package com.appofmy.android.goldprice1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.appofmy.android.goldprice1.data.CurrencyContract.CurrencyEntry;
import com.appofmy.android.goldprice1.data.CurrencyDbHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CurrencyConversion extends AppCompatActivity {
    public Spinner mGenderSpinner, mGender2;
    public static Spinner mGender1,mGender3;
    private ArrayList<Double> mDaterates;

    public TextView mTextDay ,mTextCurrency2;
    public static TextView mTextCurrency1, mTextCurrency3;
    private EditText mCurrencyBasic;
    private int mNumberBasic, mNumberCurrency1, mNumberCurrency2, mNumberCurrency3;
    private Button mClear, mExchange;

    private final Double mUsd = 1.000;
    private String[] currencyCodeExchange, currencyNameExchange;
    private String numberBasic;
    private CurrencyDbHelper mCurrencyHelper;
    private int[] codeContract;

    /** Identifier for the pet data loader */
    private static final int EXISTING_PET_LOADER = 3;

    private long newRowId = -1;
    private Uri currentPetUri;
    private double rate1, rate2, rate3 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_conversion);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.

        mCurrencyHelper = new CurrencyDbHelper(this);

        currencyCodeExchange = new String[]{"AUD", "BGN", "BRL", "CAD", "CHF", "CNY", "CZK", "DKK", "GBP", "HKD", "HRK", "HUF", "IDR", "ILS", "INR", "JPY",
                "KRW", "MXN", "MYR", "NOK", "NZD", "PHP", "PLN", "RON", "RUB", "SEK", "SGD", "THB", "TRY", "ZAR", "EUR", "USD"};

        currencyNameExchange = new String[]{"Australian Dollar", "Bulgarian Lev", "Brazilian Real", "Canadian Dollar", "Swiss Franc", "Chinese Yuan", "Czech Republic Koruna",
                "Danish Krone", "British Pound Sterling", "Hong Kong Dollar", "Croatian Kuna", "Hungarian Forint", "Indonesian Rupiah", "Israeli New Sheqel",
                "Indian Rupee", "Japanese Yen", "South Korean Won", "Mexican Peso", "Malaysian Ringgit", "Norwegian Krone", "New Zealand Dollar", "Philippine Peso",
                "Polish Zloty", "Romanian Leu", "Russian Ruble", "Swedish Krona", "Singapore Dollar", "Thai Baht", "Turkish Lira", "South African Rand", "Euro", "United States Dollar"};

        //Find Spinner gender.
        mGenderSpinner = (Spinner)findViewById(R.id.spinner_gender);
        mGender1 = (Spinner) findViewById(R.id.currency_one);
        mGender2 = (Spinner)findViewById(R.id.currency_two) ;
        mGender3 = (Spinner)findViewById(R.id.currency_three);
        //Find Edittext.
        mCurrencyBasic = (EditText)findViewById(R.id.edittext_basic);
        //Find TetxView.
        mTextDay = (TextView)findViewById(R.id.date_rates);
        mTextCurrency1 = (TextView)findViewById(R.id.text_currency1);

        mTextCurrency2 = (TextView)findViewById(R.id.text_currency2);
        mTextCurrency3 = (TextView)findViewById(R.id.text_currency3);

        //Find botton.
        mClear = (Button)findViewById(R.id.button_clear);
        mExchange = (Button)findViewById(R.id.button_exchange);

        //set list rates of dates.
        mDaterates = QueryCurrency.ratesDateCurrency;
        mDaterates.add(mUsd);
        codeContract = CurrencyEntry.genderArray;



        setupSpinner();
        setupSpinner1();
        setupSpinner2();
        setupSpinner3();




    }
    private void setupSpinner() {

        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout

        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.list_currency_code, R.layout.my_spinner_style);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        // Apply the adapter to the spinner
        mGenderSpinner.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String select = (String)parent.getItemAtPosition(position);
                if(!TextUtils.isEmpty(select)){
                    for (int i = 0; i < 32; i++){
                        if(select.contains(currencyCodeExchange[i])){
                            mNumberBasic = codeContract[i];
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mNumberBasic = CurrencyEntry.GENDER_INR;

            }
        });

    }
    private void setupSpinner1() {

        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout

        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.list_currency_code, R.layout.my_spinner_style);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        // Apply the adapter to the spinner
        mGender1.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mGender1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String select = (String)parent.getItemAtPosition(position);
                if(!TextUtils.isEmpty(select)){
                    for (int i = 0; i < 32; i++){
                        if(select.contains(currencyCodeExchange[i])){
                            mNumberCurrency1 = codeContract[i];
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mNumberCurrency1 = CurrencyEntry.GENDER_USD;

            }
        });
    }
    private void setupSpinner2() {

        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout

        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.list_currency_code, R.layout.my_spinner_style);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        // Apply the adapter to the spinner
        mGender2.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        // Set the integer mSelected to the constant values
        mGender2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String select = (String)parent.getItemAtPosition(position);
                if(!TextUtils.isEmpty(select)){
                    for (int i = 0; i < 32; i++){
                        if(select.contains(currencyCodeExchange[i])){
                            mNumberCurrency2 = codeContract[i];
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mNumberCurrency2 = CurrencyEntry.GENDER_GBP;

            }
        });
    }
    private void setupSpinner3() {

        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout

        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.list_currency_code, R.layout.my_spinner_style);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        // Apply the adapter to the spinner
        mGender3.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        // Set the integer mSelected to the constant values
        mGender3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String select = (String)parent.getItemAtPosition(position);
                if(!TextUtils.isEmpty(select)){
                    for (int i = 0; i < 32; i++){
                        if(select.contains(currencyCodeExchange[i])){
                            mNumberCurrency3 = codeContract[i];
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mNumberCurrency3 = CurrencyEntry.GENDER_EUR;

            }
        });
    }
    private void saveCurrency(){
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String dateRates = QueryCurrency.dateRefresh;
        String valueRates = mCurrencyBasic.getText().toString().trim();
        if(TextUtils.isEmpty(dateRates) && TextUtils.isEmpty(valueRates) && mNumberCurrency1 == CurrencyEntry.GENDER_USD
                &&  mNumberCurrency2 == CurrencyEntry.GENDER_GBP && mNumberCurrency3 == CurrencyEntry.GENDER_EUR){
            return;
        }
        double valueBasic = 1.0;
        long valueBasicInt = 1;
        if(!TextUtils.isEmpty(valueRates)){
            valueBasic = Double.parseDouble(valueRates);

            valueBasicInt = Long.parseLong(valueRates);
        }


        //Calculate currency with rates 1.
        double rateBasic = mDaterates.get(mNumberBasic);
        double rateCurrency1 = mDaterates.get(mNumberCurrency1);
        double exchange1 = valueBasic*rateCurrency1/rateBasic ;
        String exchange1String = String.valueOf(exchange1);

        //Calculate currency with rates 2.
        double ratesCurrency2 = mDaterates.get(mNumberCurrency2);
        double exchange2 = valueBasic*ratesCurrency2/rateBasic;
        String exchange2String = String.valueOf(exchange2);

        //Calculate currency with rates3.
        double ratesCurrency3 = mDaterates.get(mNumberCurrency3);
        double exchange3 = valueBasic*ratesCurrency3/rateBasic;
        String exchange3String = String.valueOf(exchange3);




        // Gets the database in write mode
        SQLiteDatabase db = mCurrencyHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        int idPut = 1;
        ContentValues values = new ContentValues();
        if(CurrencyEntry._ID != null){
            values.put(CurrencyEntry._ID, idPut);
        }

        values.put(CurrencyEntry.DATES_VALUE, dateRates);
        values.put(CurrencyEntry.VALUE_EXCHANGE, valueBasicInt);
        values.put(CurrencyEntry.GENDER_SPINNER, mNumberBasic);
        values.put(CurrencyEntry.TEXTCURRENCY1, exchange1String);
        values.put(CurrencyEntry.GENDER1,mNumberCurrency1);
        values.put(CurrencyEntry.TEXTCURRENCY2, exchange2String);
        values.put(CurrencyEntry.GENDER2,mNumberCurrency2);
        values.put(CurrencyEntry.TEXTCURRENCY3, exchange3String);
        values.put(CurrencyEntry.GENDER3,mNumberCurrency3);

        // Insert a new row for pet in the database, returning the ID of that new row.
        newRowId = db.replace(CurrencyEntry.TABLE_CURRENCY, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with Currency conversion code base ", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Success Currency conversion with code base ", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    protected void onStart() {
        super.onStart();
        mGenderSpinner.setSelection(14);
        mGender1.setSelection(31);
        mGender2.setSelection(8);
        mGender3.setSelection(30);
        mExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCurrency();
                displayDatabase();

            }
        });
        displayDatabase();
        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextDay.setText("");
                mCurrencyBasic.setText("");
                mTextCurrency1.setText("");
                mTextCurrency2.setText("");
                mTextCurrency3.setText("");
            }
        });

    }
    private void displayDatabase() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mCurrencyHelper.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {CurrencyEntry._ID,
                CurrencyEntry.DATES_VALUE, CurrencyEntry.VALUE_EXCHANGE,
                CurrencyEntry.GENDER_SPINNER, CurrencyEntry.TEXTCURRENCY1,
                CurrencyEntry.GENDER1, CurrencyEntry.TEXTCURRENCY2,
                CurrencyEntry.GENDER2, CurrencyEntry.TEXTCURRENCY3, CurrencyEntry.GENDER3 };
        // Perform a query on the pets table
        Cursor cursor = db.query(
                CurrencyEntry.TABLE_CURRENCY, // The table to query
                projection,                  // The columns to return
                null,                       // The columns for the WHERE clause
                null,                        // The values for the WHERE clause
                null,                       // Don't group the rows
                null,                      // Don't filter by row groups
                null ) ;                   // The sort order

        try {

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(CurrencyEntry._ID);
            int dateColumnIndex = cursor.getColumnIndex(CurrencyEntry.DATES_VALUE);
            int valueColumnIndex = cursor.getColumnIndex(CurrencyEntry.VALUE_EXCHANGE);
            int genderColumnIndex = cursor.getColumnIndex(CurrencyEntry.GENDER_SPINNER);
            int text1ColumnIndex = cursor.getColumnIndex(CurrencyEntry.TEXTCURRENCY1);
            int gender1ColumnIndex = cursor.getColumnIndex(CurrencyEntry.GENDER1);
            int text2ColumnIndex = cursor.getColumnIndex(CurrencyEntry.TEXTCURRENCY2);
            int gender2ColumnIndex = cursor.getColumnIndex(CurrencyEntry.GENDER2);
            int text3ColumnIndex = cursor.getColumnIndex(CurrencyEntry.TEXTCURRENCY3);
            int gender3ColumnIndex = cursor.getColumnIndex(CurrencyEntry.GENDER3);
            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()){
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currencyId = cursor.getInt(idColumnIndex);
                String datesRates = cursor.getString(dateColumnIndex);
                long valueBase = cursor.getLong(valueColumnIndex);
                int genderSpinner = cursor.getInt(genderColumnIndex);
                String textCurrency1 = cursor.getString(text1ColumnIndex);
                int gender1 = cursor.getInt(gender1ColumnIndex);
                String textCurrency2 = cursor.getString(text2ColumnIndex);
                int gender2 = cursor.getInt(gender2ColumnIndex);
                String textCurrency3 = cursor.getString(text3ColumnIndex);
                int gender3 = cursor.getInt(gender3ColumnIndex);

                double valuBasicDouble = Double.parseDouble(String.valueOf(valueBase));
                double text1Double = Double.parseDouble(textCurrency1);
                double text2Double = Double.parseDouble(textCurrency2);
                double text3Double = Double.parseDouble(textCurrency3);


                //Set text color.
                mTextCurrency1.setTextColor(getResources().getColor(getColorValueCurrency(valuBasicDouble, text1Double)));
                mTextCurrency2.setTextColor(getResources().getColor(getColorValueCurrency(valuBasicDouble, text2Double)));
                mTextCurrency3.setTextColor(getResources().getColor(getColorValueCurrency( valuBasicDouble, text3Double)));

                String exchange1String = formatCurrencyExchange(text1Double);
                String exchange2String = formatCurrencyExchange(text2Double);
                String exchange3String = formatCurrencyExchange(text3Double);





                // Update the views on the screen with the values from the database
                mTextDay.setText(datesRates);
                mCurrencyBasic.setText(String.valueOf(valueBase));
                mTextCurrency1.setText(exchange1String);
                mTextCurrency2.setText(exchange2String);
                mTextCurrency3.setText(exchange3String);

                // Gender is a dropdown spinner, so map the constant value from the database
                // into one of the dropdown options .
                // Then call setSelection() so that option is displayed on screen as the current selection.
                switch (genderSpinner){
                    case CurrencyEntry.GENDER_AUD:
                        mGenderSpinner.setSelection(0);
                        break;
                    case CurrencyEntry.GENDER_BGN:
                        mGenderSpinner.setSelection(1);
                        break;
                    case CurrencyEntry.GENDER_BRL:
                        mGenderSpinner.setSelection(2);
                        break;
                    case CurrencyEntry.GENDER_CAD:
                        mGenderSpinner.setSelection(3);
                        break;
                    case CurrencyEntry.GENDER_CHF:
                        mGenderSpinner.setSelection(4);
                        break;
                    case CurrencyEntry.GENDER_CNY:
                        mGenderSpinner.setSelection(5);
                        break;
                    case CurrencyEntry.GENDER_CZK:
                        mGenderSpinner.setSelection(6);
                        break;
                    case CurrencyEntry.GENDER_DKK:
                        mGenderSpinner.setSelection(7);
                        break;
                    case CurrencyEntry.GENDER_GBP:
                        mGenderSpinner.setSelection(8);
                        break;
                    case CurrencyEntry.GENDER_HKD:
                        mGenderSpinner.setSelection(9);
                        break;
                    case CurrencyEntry.GENDER_HRK:
                        mGenderSpinner.setSelection(10);
                        break;
                    case CurrencyEntry.GENDER_HUF:
                        mGenderSpinner.setSelection(11);
                        break;
                    case CurrencyEntry.GENDER_IDR:
                        mGenderSpinner.setSelection(12);
                        break;
                    case CurrencyEntry.GENDER_ILS:
                        mGenderSpinner.setSelection(13);
                        break;
                    case CurrencyEntry.GENDER_JPY:
                        mGenderSpinner.setSelection(15);
                        break;
                    case CurrencyEntry.GENDER_KRW:
                        mGenderSpinner.setSelection(16);
                        break;
                    case CurrencyEntry.GENDER_MXN:
                        mGenderSpinner.setSelection(17);
                        break;
                    case CurrencyEntry.GENDER_MYR:
                        mGenderSpinner.setSelection(18);
                        break;
                    case CurrencyEntry.GENDER_NOK:
                        mGenderSpinner.setSelection(19);
                        break;
                    case CurrencyEntry.GENDER_NZD:
                        mGenderSpinner.setSelection(20);
                        break;
                    case CurrencyEntry.GENDER_PHP:
                        mGenderSpinner.setSelection(21);
                        break;
                    case CurrencyEntry.GENDER_PLN:
                        mGenderSpinner.setSelection(22);
                        break;
                    case CurrencyEntry.GENDER_RON:
                        mGenderSpinner.setSelection(23);
                        break;
                    case CurrencyEntry.GENDER_RUB:
                        mGenderSpinner.setSelection(24);
                        break;
                    case CurrencyEntry.GENDER_SEK:
                        mGenderSpinner.setSelection(25);
                        break;
                    case CurrencyEntry.GENDER_SGD:
                        mGenderSpinner.setSelection(26);
                        break;
                    case CurrencyEntry.GENDER_THB:
                        mGenderSpinner.setSelection(27);
                        break;
                    case CurrencyEntry.GENDER_TRY:
                        mGenderSpinner.setSelection(28);
                        break;
                    case CurrencyEntry.GENDER_ZAR:
                        mGenderSpinner.setSelection(29);
                        break;
                    case CurrencyEntry.GENDER_EUR:
                        mGenderSpinner.setSelection(30);
                        break;
                    case CurrencyEntry.GENDER_USD:
                        mGenderSpinner.setSelection(31);
                        break;

                    default:
                        mGenderSpinner.setSelection(14);
                        break;

                }
                switch (gender1){
                    case CurrencyEntry.GENDER_AUD:
                        mGender1.setSelection(0);
                        break;
                    case CurrencyEntry.GENDER_BGN:
                        mGender1.setSelection(1);
                        break;
                    case CurrencyEntry.GENDER_BRL:
                        mGender1.setSelection(2);
                        break;
                    case CurrencyEntry.GENDER_CAD:
                        mGender1.setSelection(3);
                        break;
                    case CurrencyEntry.GENDER_CHF:
                        mGender1.setSelection(4);
                        break;
                    case CurrencyEntry.GENDER_CNY:
                        mGender1.setSelection(5);
                        break;
                    case CurrencyEntry.GENDER_CZK:
                        mGender1.setSelection(6);
                        break;
                    case CurrencyEntry.GENDER_DKK:
                        mGender1.setSelection(7);
                        break;
                    case CurrencyEntry.GENDER_GBP:
                        mGender1.setSelection(8);
                        break;
                    case CurrencyEntry.GENDER_HKD:
                        mGender1.setSelection(9);
                        break;
                    case CurrencyEntry.GENDER_HRK:
                        mGender1.setSelection(10);
                        break;
                    case CurrencyEntry.GENDER_HUF:
                        mGender1.setSelection(11);
                        break;
                    case CurrencyEntry.GENDER_IDR:
                        mGender1.setSelection(12);
                        break;
                    case CurrencyEntry.GENDER_ILS:
                        mGender1.setSelection(13);
                        break;
                    case CurrencyEntry.GENDER_INR:
                        mGender1.setSelection(14);
                        break;
                    case CurrencyEntry.GENDER_JPY:
                        mGender1.setSelection(15);
                        break;
                    case CurrencyEntry.GENDER_KRW:
                        mGender1.setSelection(16);
                        break;
                    case CurrencyEntry.GENDER_MXN:
                        mGender1.setSelection(17);
                        break;
                    case CurrencyEntry.GENDER_MYR:
                        mGender1.setSelection(18);
                        break;
                    case CurrencyEntry.GENDER_NOK:
                        mGender1.setSelection(19);
                        break;
                    case CurrencyEntry.GENDER_NZD:
                        mGender1.setSelection(20);
                        break;
                    case CurrencyEntry.GENDER_PHP:
                        mGender1.setSelection(21);
                        break;
                    case CurrencyEntry.GENDER_PLN:
                        mGender1.setSelection(22);
                        break;
                    case CurrencyEntry.GENDER_RON:
                        mGender1.setSelection(23);
                        break;
                    case CurrencyEntry.GENDER_RUB:
                        mGender1.setSelection(24);
                        break;
                    case CurrencyEntry.GENDER_SEK:
                        mGender1.setSelection(25);
                        break;
                    case CurrencyEntry.GENDER_SGD:
                        mGender1.setSelection(26);
                        break;
                    case CurrencyEntry.GENDER_THB:
                        mGender1.setSelection(27);
                        break;
                    case CurrencyEntry.GENDER_TRY:
                        mGender1.setSelection(28);
                        break;
                    case CurrencyEntry.GENDER_ZAR:
                        mGender1.setSelection(29);
                        break;
                    case CurrencyEntry.GENDER_EUR:
                        mGender1.setSelection(30);
                        break;

                    default:
                        mGender1.setSelection(31);
                        break;

                }
                switch (gender2) {
                    case CurrencyEntry.GENDER_AUD:
                        mGender2.setSelection(0);
                        break;
                    case CurrencyEntry.GENDER_BGN:
                        mGender2.setSelection(1);
                        break;
                    case CurrencyEntry.GENDER_BRL:
                        mGender2.setSelection(2);
                        break;
                    case CurrencyEntry.GENDER_CAD:
                        mGender2.setSelection(3);
                        break;
                    case CurrencyEntry.GENDER_CHF:
                        mGender2.setSelection(4);
                        break;
                    case CurrencyEntry.GENDER_CNY:
                        mGender2.setSelection(5);
                        break;
                    case CurrencyEntry.GENDER_CZK:
                        mGender2.setSelection(6);
                        break;
                    case CurrencyEntry.GENDER_DKK:
                        mGender2.setSelection(7);
                        break;
                    case CurrencyEntry.GENDER_HKD:
                        mGender2.setSelection(9);
                        break;
                    case CurrencyEntry.GENDER_HRK:
                        mGender2.setSelection(10);
                        break;
                    case CurrencyEntry.GENDER_HUF:
                        mGender2.setSelection(11);
                        break;
                    case CurrencyEntry.GENDER_IDR:
                        mGender2.setSelection(12);
                        break;
                    case CurrencyEntry.GENDER_ILS:
                        mGender2.setSelection(13);
                        break;
                    case CurrencyEntry.GENDER_INR:
                        mGender2.setSelection(14);
                        break;
                    case CurrencyEntry.GENDER_JPY:
                        mGender2.setSelection(15);
                        break;
                    case CurrencyEntry.GENDER_KRW:
                        mGender2.setSelection(16);
                        break;
                    case CurrencyEntry.GENDER_MXN:
                        mGender2.setSelection(17);
                        break;
                    case CurrencyEntry.GENDER_MYR:
                        mGender2.setSelection(18);
                        break;
                    case CurrencyEntry.GENDER_NOK:
                        mGender2.setSelection(19);
                        break;
                    case CurrencyEntry.GENDER_NZD:
                        mGender2.setSelection(20);
                        break;
                    case CurrencyEntry.GENDER_PHP:
                        mGender2.setSelection(21);
                        break;
                    case CurrencyEntry.GENDER_PLN:
                        mGender2.setSelection(22);
                        break;
                    case CurrencyEntry.GENDER_RON:
                        mGender2.setSelection(23);
                        break;
                    case CurrencyEntry.GENDER_RUB:
                        mGender2.setSelection(24);
                        break;
                    case CurrencyEntry.GENDER_SEK:
                        mGender2.setSelection(25);
                        break;
                    case CurrencyEntry.GENDER_SGD:
                        mGender2.setSelection(26);
                        break;
                    case CurrencyEntry.GENDER_THB:
                        mGender2.setSelection(27);
                        break;
                    case CurrencyEntry.GENDER_TRY:
                        mGender2.setSelection(28);
                        break;
                    case CurrencyEntry.GENDER_ZAR:
                        mGender2.setSelection(29);
                        break;
                    case CurrencyEntry.GENDER_EUR:
                        mGender2.setSelection(30);
                        break;
                    case CurrencyEntry.GENDER_USD:
                        mGender2.setSelection(31);
                        break;
                    default:
                        mGender2.setSelection(8);
                        break;
                }
                switch (gender3) {
                    case CurrencyEntry.GENDER_AUD:
                        mGender3.setSelection(0);
                        break;
                    case CurrencyEntry.GENDER_BGN:
                        mGender3.setSelection(1);
                        break;
                    case CurrencyEntry.GENDER_BRL:
                        mGender3.setSelection(2);
                        break;
                    case CurrencyEntry.GENDER_CAD:
                        mGender3.setSelection(3);
                        break;
                    case CurrencyEntry.GENDER_CHF:
                        mGender3.setSelection(4);
                        break;
                    case CurrencyEntry.GENDER_CNY:
                        mGender3.setSelection(5);
                        break;
                    case CurrencyEntry.GENDER_CZK:
                        mGender3.setSelection(6);
                        break;
                    case CurrencyEntry.GENDER_DKK:
                        mGender3.setSelection(7);
                        break;
                    case CurrencyEntry.GENDER_HKD:
                        mGender3.setSelection(9);
                        break;
                    case CurrencyEntry.GENDER_GBP:
                        mGender3.setSelection(8);
                        break;
                    case CurrencyEntry.GENDER_HRK:
                        mGender3.setSelection(10);
                        break;
                    case CurrencyEntry.GENDER_HUF:
                        mGender3.setSelection(11);
                        break;
                    case CurrencyEntry.GENDER_IDR:
                        mGender3.setSelection(12);
                        break;
                    case CurrencyEntry.GENDER_ILS:
                        mGender3.setSelection(13);
                        break;
                    case CurrencyEntry.GENDER_INR:
                        mGender3.setSelection(14);
                        break;
                    case CurrencyEntry.GENDER_JPY:
                        mGender3.setSelection(15);
                        break;
                    case CurrencyEntry.GENDER_KRW:
                        mGender3.setSelection(16);
                        break;
                    case CurrencyEntry.GENDER_MXN:
                        mGender3.setSelection(17);
                        break;
                    case CurrencyEntry.GENDER_MYR:
                        mGender3.setSelection(18);
                        break;
                    case CurrencyEntry.GENDER_NOK:
                        mGender3.setSelection(19);
                        break;
                    case CurrencyEntry.GENDER_NZD:
                        mGender3.setSelection(20);
                        break;
                    case CurrencyEntry.GENDER_PHP:
                        mGender3.setSelection(21);
                        break;
                    case CurrencyEntry.GENDER_PLN:
                        mGender3.setSelection(22);
                        break;
                    case CurrencyEntry.GENDER_RON:
                        mGender3.setSelection(23);
                        break;
                    case CurrencyEntry.GENDER_RUB:
                        mGender3.setSelection(24);
                        break;
                    case CurrencyEntry.GENDER_SEK:
                        mGender3.setSelection(25);
                        break;
                    case CurrencyEntry.GENDER_SGD:
                        mGender3.setSelection(26);
                        break;
                    case CurrencyEntry.GENDER_THB:
                        mGender3.setSelection(27);
                        break;
                    case CurrencyEntry.GENDER_TRY:
                        mGender3.setSelection(28);
                        break;
                    case CurrencyEntry.GENDER_ZAR:
                        mGender3.setSelection(29);
                        break;
                    case CurrencyEntry.GENDER_USD:
                        mGender3.setSelection(31);
                        break;
                    default:
                        mGender3.setSelection(30);
                        break;
                }



            }


        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }


    }

    private String formatCurrencyExchange(double currencyChange){
        DecimalFormat currentcyFormat = new DecimalFormat("#,###,###,###,###,##0.00###" );
        return currentcyFormat.format(currencyChange);

    }
    private int getColorValueCurrency(double value1, double value2){
        if(value2 > value1){
            return R.color.textBiggerValue;
        } else if(value2 == value1 || Math.abs(value2 - value1) < 0.000001){
            return R.color.textEqualValue;
        } else {
            return R.color.textSmallerValue;
        }
    }


}
