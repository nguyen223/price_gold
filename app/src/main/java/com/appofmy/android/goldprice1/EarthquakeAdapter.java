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

class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private static final String LOG_TAG = Earthquake.class.getSimpleName();
    /*
      The part of the location string from the USGS service that we use to determine
      whether or not there is a location offset present ("5km N of Cairo, Egypt").
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
    private Earthquake earthquakeAndroidFlavor2;



    EarthquakeAdapter(Activity context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);


    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);

        }

        int count = EarthquakeActivity.dateNumbers - 1;
        Earthquake earthquakeAndroidFlavor = getItem(position);

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
        //Format currentcy.
        String usdPmString = null;
        if (earthquakeAndroidFlavor != null) {
            usdPmString = earthquakeAndroidFlavor.getmUsdPM();
        }

        //Find TextView usd PM

        TextView usdPmView = (TextView) listItemView.findViewById(R.id.usd_pm);
        //set Text usd PM.
        usdPmView.setText(formatCurrency(usdPmString));

        //Format currentcy.
        String usdAmString = null;
        if (earthquakeAndroidFlavor != null) {
            usdAmString = earthquakeAndroidFlavor.getmUsdAM();
        }

        //Find TextView usd AM
        TextView usdAmView = (TextView) listItemView.findViewById(R.id.usd_am);
        //set Text usd AM
        usdAmView.setText(formatCurrency(usdAmString));
        //Format currentcy.
        String gbpPmString = null;
        if (earthquakeAndroidFlavor != null) {
            gbpPmString = earthquakeAndroidFlavor.getmGbpPM();
        }

        //Find TextView gbp PM.
        TextView gbpPmView = (TextView) listItemView.findViewById(R.id.gbp_pm);
        //set Text gbp PM.
        gbpPmView.setText(formatCurrency(gbpPmString));
        //Format currentcy.
        String gbpAmString = null;
        if (earthquakeAndroidFlavor != null) {
            gbpAmString = earthquakeAndroidFlavor.getmGbpbAM();
        }
        //Find TextView gbp AM.
        TextView gbpAmView = (TextView) listItemView.findViewById(R.id.gbp_am);
        //set Text gbp AM.
        gbpAmView.setText(formatCurrency(gbpAmString));
        //Format currentcy.
        String euroPmString = null;
        if (earthquakeAndroidFlavor != null) {
            euroPmString = earthquakeAndroidFlavor.getmEuroPM();
        }

        //Find TextView euro PM.
        TextView euroPmView = (TextView) listItemView.findViewById(R.id.euro_pm);
        //set Text gbp PM.
        euroPmView.setText(formatCurrency(euroPmString));
        //Format currentcy.
        String euroAmString = null;
        if (earthquakeAndroidFlavor != null) {
            euroAmString = earthquakeAndroidFlavor.getmEuroAM();
        }
        //Find TextView euro AM.
        TextView euroAmView = (TextView) listItemView.findViewById(R.id.euro_am);
        //set Text gbp AM.
        euroAmView.setText(formatCurrency(euroAmString));
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
        TextView percentUsd = (TextView) listItemView.findViewById(R.id.usd_percent);
        if (usdPmString != null) {
            if (usdPmString.contains(noHave)) {
                percentUsd.setText("");
            } else{

                percentU = getDoubleValue(usdPmString,usdAmString);
                percentUsd.setText(formatPercentPM(percentU));
                percentUsd.setTextColor(getColorValue(percentU));
            }
        }
        TextView percentGbp = (TextView) listItemView.findViewById(R.id.gbp_percent);
        if (gbpPmString != null) {
            if (gbpPmString.contains(noHave)) {
                percentGbp.setText("");
            } else{

                percentG = getDoubleValue(gbpPmString,gbpAmString);
                percentGbp.setText(formatPercentPM(percentG));
                percentGbp.setTextColor(getColorValue(percentG));

            }
        }
        TextView percentEuro = (TextView) listItemView.findViewById(R.id.euro_percent);
        if (euroPmString != null) {
            if (euroPmString.contains(noHave)) {
                percentEuro.setText("");
            } else{

                percentE = getDoubleValue(gbpPmString,gbpAmString);
                percentEuro.setText(formatPercentPM(percentE));
                percentEuro.setTextColor(getColorValue(percentE));

            }
        }
        if (usdPmString != null) {
            if(usdPmString.contains(noHave)){
                usdPmView.setTextColor(getBlack());
            } else {
                usdPmView.setTextColor( getColorValue(percentU));
            }
        }
        if (gbpPmString != null) {
            if(gbpPmString.contains(noHave)){
                gbpPmView.setTextColor(getBlack());
            }else {
                gbpPmView.setTextColor(getColorValue(percentG));
            }
        }
        if (euroPmString != null) {
            if(euroPmString.contains(noHave)){
                euroPmView.setTextColor(getBlack());
            }else {
                euroPmView.setTextColor(getColorValue(percentE));
            }
        }
        String usdPmbefore = null;
        if (earthquakeAndroidFlavor2 != null) {
            usdPmbefore = earthquakeAndroidFlavor2.getmUsdPM();
        }
        TextView percentUsd2 = (TextView) listItemView.findViewById(R.id.usd_percent1);
        double percentUsdAm = getDoubleValue(usdAmString, usdPmbefore);
        String gbpPmbefore = null;
        if (earthquakeAndroidFlavor2 != null) {
            gbpPmbefore = earthquakeAndroidFlavor2.getmGbpPM();
        }
        TextView percentGbp2 = (TextView)listItemView.findViewById(R.id.gbp_percent1);
        double percentGbpAm = getDoubleValue(gbpAmString,gbpPmbefore);
        String euroPmbefore = null;
        if (earthquakeAndroidFlavor2 != null) {
            euroPmbefore = earthquakeAndroidFlavor2.getmEuroPM();
        }
        TextView percentEuro2 = (TextView)listItemView.findViewById(R.id.euro_percent1);
        double percentEuroAm = getDoubleValue(euroAmString, euroPmbefore);
        if(position < count) {
            percentUsd2.setText(formatPercentAM(percentUsdAm));
            percentUsd2.setTextColor(getColorValue(percentUsdAm));
            percentGbp2.setText(formatPercentAM(percentGbpAm));
            percentGbp2.setTextColor(getColorValue(percentGbpAm));
            percentEuro2.setText(formatPercentAM(percentEuroAm));
            percentEuro2.setTextColor(getColorValue(percentEuroAm));
            usdAmView.setTextColor(getColorValue(percentUsdAm));
            gbpAmView.setTextColor(getColorValue(percentGbpAm));
            euroAmView.setTextColor(getColorValue(percentEuroAm));
        } else  {
            percentUsd2.setText("");
            percentGbp2.setText("");
            percentEuro2.setText("");
            usdAmView.setTextColor(getBlack());
            gbpAmView.setTextColor(getBlack());
            euroAmView.setTextColor(getBlack());

        }




        return listItemView;

    }

    private String formatPercentPM(double usdPercent) {
        DecimalFormat percenPmFormat = new DecimalFormat("0.00");
        if(usdPercent >= 0){
            return "+" + percenPmFormat.format(usdPercent) + "%(PM)";
        } else {
            return percenPmFormat.format(usdPercent) + "%(PM)";
        }

    }
    private String formatPercentAM(double percentAM) {
        DecimalFormat percentAmFormat = new DecimalFormat("0.00");
        if(percentAM >= 0){
            return "+" + percentAmFormat.format(percentAM) + "%(AM)";
        } else {
            return percentAmFormat.format(percentAM) + "%(AM)";
        }

    }
    private String formatCurrency(String currencyStringg){
        if(currencyStringg.contains(noHave)){
            return noHave;
        } else {
            double currency = Double.parseDouble(currencyStringg);
            DecimalFormat currentcyFormat = new DecimalFormat("#,##0.00" );
            return "$" + currentcyFormat.format(currency);
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
