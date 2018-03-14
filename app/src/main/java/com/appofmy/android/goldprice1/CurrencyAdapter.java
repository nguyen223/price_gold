package com.appofmy.android.goldprice1;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WIN on 7/24/2017.
 */

public class CurrencyAdapter extends ArrayAdapter<Currency> {
    public CurrencyAdapter(Activity context, List<Currency> currencyArrayAdapter) {
        super(context, 0, currencyArrayAdapter);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // check if the current view is reused else inflate the view
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_currency, parent, false);
        }
        //get the object located at position
        Currency currencyItem = getItem(position);

        // Tim image view.
        ImageView flag = (ImageView)listItemView.findViewById(R.id.flag_view);
        // Lay image va dat.
        if (currencyItem != null) {
            flag.setImageResource(currencyItem.getmImageResouceFlag());
        }
        // Tim textview currency code.
        TextView code = (TextView)listItemView.findViewById(R.id.codecurrency);
        // dat textview.
        if (currencyItem != null) {
            code.setText(currencyItem.getmCurrencyCode());
        }
        //Tim textview currency name.
        TextView name = (TextView)listItemView.findViewById(R.id.namecurrency);
        // dat textview.
        if (currencyItem != null) {
            name.setText(currencyItem.getmCurrencyName());
        }

        //Set value rates.
        TextView valuExchange = (TextView)listItemView.findViewById(R.id.value_currency);
        if (currencyItem != null) {
            valuExchange.setText(currencyItem.getmValueCurrency());
        }
        ArrayList<String> valueList = QueryCurrency.listStringValue;
        String valueRates = valueList.get(position);
        double ratesDouble = Double.parseDouble(valueRates);
        if(ratesDouble < 1){
            valuExchange.setTextColor(ContextCompat.getColor(getContext(), R.color.textBiggerValue));

        }else if(ratesDouble == 1){
            valuExchange.setTextColor(ContextCompat.getColor(getContext(), R.color.textEqualValue));

        }else {
            valuExchange.setTextColor(ContextCompat.getColor(getContext(), R.color.textSmallerValue));

        }
        return listItemView;
    }
}
