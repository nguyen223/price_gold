package com.appofmy.android.goldprice1;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

/**
 * An {@link EarthquakeAdapter} knows how to create a list item layout for each earthquake
 * in the data source (a list of {@link Earthquake} objects).
 *
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */

public class PriceOilAdapter extends ArrayAdapter<PriceOil> {
    private static final String LOG_TAG = PriceOil.class.getSimpleName();
    /**
     * The part of the location string from the USGS service that we use to determine
     * whether or not there is a location offset present ("5km N of Cairo, Egypt").
     */

    /**
     * Constructs a new {@link EarthquakeAdapter}.
     *
     * @param context     of the app
     * @param earthquakes is the list of earthquakes, which is the data source of the adapter
     */
    private static final String noHave = "null";
    private double percentU;
    private double percentG;
    private double percentE;
    private PriceOil earthquakeAndroidFlavor2;



    public PriceOilAdapter(Activity context, List<PriceOil> earthquakes) {
        super(context, 0, earthquakes);


    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_priceoil, parent, false);

        }
        int count = PriceOilActivity.dateNumbers - 1;
        PriceOil earthquakeAndroidFlavor = getItem(position);
        if(position < count ){
             earthquakeAndroidFlavor2 = getItem(position + 1);
        }

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date_value);
        String dateNow = null;
        if (earthquakeAndroidFlavor != null) {
            dateNow = earthquakeAndroidFlavor.getmDte();
        }
        //set value date
        dateTextView.setText(dateNow);

        // String dateRefresh.
        String dateRefresh = null;
        if (earthquakeAndroidFlavor != null) {
            dateRefresh = earthquakeAndroidFlavor.getmDateRefresh();
        }

        //Find TextView dateRefresh.
        TextView dateRefreshTextView = (TextView) listItemView.findViewById(R.id.timeView_value);
        dateRefreshTextView.setText(subDate(dateRefresh));
        //Find TextView dateRefresh.
        TextView timeRefreshTextView = (TextView) listItemView.findViewById(R.id.timeView_value_h);
        //set value date
        timeRefreshTextView.setText(subDateValue(dateRefresh));

        TextView price = (TextView)listItemView.findViewById(R.id.price_oil);
        String priceNow = null;
        if (earthquakeAndroidFlavor != null) {
            priceNow = earthquakeAndroidFlavor.getmPrice();
        }
        price.setText(formatCurrency(priceNow));


        TextView percent = (TextView)listItemView.findViewById(R.id.percent_oil);
        String percent_before = null;
        if (earthquakeAndroidFlavor != null) {
            percent_before = earthquakeAndroidFlavor.getmPrice();
        }
        String percent_after = earthquakeAndroidFlavor2.getmPrice();
        double doublePcent = getDoubleValue(percent_before, percent_after);
        if(position < count){
            percent.setText(formatPercentAM(doublePcent));
            percent.setTextColor(getColorValue(doublePcent));
            price.setTextColor(getColorValue(doublePcent));
        } else {
            percent.setText("");
            price.setTextColor(getBlack());
        }









        return listItemView;

    }


    private String formatPercentAM(double percentAM) {
        DecimalFormat percentAmFormat = new DecimalFormat("0.00");
        if(percentAM >= 0){
            return " +" + percentAmFormat.format(percentAM) + "%" ;
        } else {
            return " " + percentAmFormat.format(percentAM) + "%" ;
        }

    }
    private String formatCurrency(String currencyStringg){
        if(currencyStringg.contains(noHave)){
            return noHave;
        } else {
            double currency = Double.parseDouble(currencyStringg);
            DecimalFormat currentcyFormat = new DecimalFormat("#,##0.00" );
            return "$" + currentcyFormat.format(currency) ;
        }

    }
    private String subDate(String date){
        return date.substring(0,10);
    }
    private String subDateValue(String date){
        return date.substring(11,19) + " UTC";
    }
    private int getColorValue(double value){
        if(value > 0){
            return ContextCompat.getColor(getContext(), R.color.textBiggerValue);
        } else if(value < 0){
            return ContextCompat.getColor(getContext(), R.color.textSmallerValue);
        } else {
            return ContextCompat.getColor(getContext(), R.color.textEqualValue);
        }
    }
    private double getDoubleValue(String currencyPM, String currencyAM){
        double pmCurrecy = Double.parseDouble(currencyPM);
        double amCurrency = Double.parseDouble(currencyAM);
        return ((pmCurrecy - amCurrency)/amCurrency)*100;
    }
    private int getBlack(){
        return ContextCompat.getColor(getContext(),R.color.textBlack);
    }
    private TextView getTextViewValue(String pMbefore, TextView percentt2, String amString, int position2, int count2){
        double percentAm = getDoubleValue(amString, pMbefore);
        if(position2 < count2){
            percentt2.setText(formatPercentAM(percentAm));
            percentt2.setTextColor(getColorValue(percentAm));
        }else {
            percentt2.setText("");
        }
        return percentt2;
    }


}
