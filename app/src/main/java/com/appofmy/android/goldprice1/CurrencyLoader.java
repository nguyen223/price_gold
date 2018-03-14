package com.appofmy.android.goldprice1;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Loads a list of currency by using an AsyncTask to perform the
 * network request to the given URL.
 */
public class CurrencyLoader extends AsyncTaskLoader<List<Currency>> {
    /** Tag for log messages */
    private static final String LOG_TAG = CurrencyLoader.class.getName();
    /** Query URL */
    private String mUrl;
    /**
     * Constructs a new {@link CurrencyLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public CurrencyLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }


    @Override
    protected void onStartLoading() {
        forceLoad();
    }
    /**
     * This is on a background thread.
     */

    @Override
    public List<Currency> loadInBackground() {
        if(mUrl == null){
            return  null;

        }
        // Perform the network request, parse the response, and extract a list of earthquakes.
        return QueryCurrency.fetchCurrencyData(mUrl);
    }
}
