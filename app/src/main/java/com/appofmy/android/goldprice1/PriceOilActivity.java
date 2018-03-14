/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.appofmy.android.goldprice1;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.appofmy.android.goldprice1.util.IabHelper;

import java.util.ArrayList;
import java.util.List;

public class PriceOilActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<PriceOil>> {

    public static final String TAG = PriceOilActivity.class.getName();
    /** URL for earthquake data from the USGS dataset */
    private static final String USGS_REQUEST_URL = "https://www.quandl.com/api/v3/datasets/OPEC/ORB?api_key=-cuRWGNCVi6DchDEBrWy";
    private static final String MARKET_GOLD = "https://www.quandl.com/collections/markets/gasoline";
    /** Adapter for the list of earthquakes */
    private PriceOilAdapter mAdapter;
    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int EARTHQUAKE_LOADER_ID = 1;
    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextview;
    public static int dateNumbers;
    private boolean isConnected;
    IabHelper mHelper;
    private ImageView imageEmpty, iconRefresh;
    public ImageView iconEuro, iconLbma;
    private View loadingIndicator;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_oil_main);


        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String numberOfDates = sharedPrefs.getString(
                getString(R.string.settings_min_magnitude_key1),
                getString(R.string.settings_min_magnitude_default1));
        dateNumbers = 30;
        try{
            dateNumbers = Integer.parseInt(numberOfDates);
        } catch (NumberFormatException nfe){

        }

        iconEuro = (ImageView) findViewById(R.id.exchange_activity);

        iconEuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(PriceOilActivity.this, CurrencyActivity.class);
                startActivity(intent);

            }
        });

        iconLbma = (ImageView) findViewById(R.id.lbma_activity);

        iconLbma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(PriceOilActivity.this, EarthquakeActivity.class);
                startActivity(intent);

            }
        });


        iconRefresh = (ImageView)findViewById(R.id.image_refresh_button) ;
        iconRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(PriceOilActivity.this, PriceOilActivity.class);
                startActivity(intent);
            }
        });
        iconRefresh.setVisibility(View.GONE);
        //Set image empty
        imageEmpty = (ImageView)findViewById(R.id.empty_image);
        if (imageEmpty != null) {
            imageEmpty.setVisibility(View.GONE);
        }

        mEmptyStateTextview = (TextView) findViewById(R.id.empty_view) ;
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        // Create a new adapter that takes an empty list of earthquakes as input
        if (earthquakeListView != null) {
            earthquakeListView.setEmptyView(mEmptyStateTextview);
        }
        mAdapter = new PriceOilAdapter(this, new ArrayList<PriceOil>());
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        if (earthquakeListView != null) {
            earthquakeListView.setAdapter(mAdapter);
        }
        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake.
        if (earthquakeListView != null) {
            earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    // Convert the String URL into a URI object (to pass into the Intent constructor)
                    Uri earthquakeUri = Uri.parse(MARKET_GOLD);

                    // Create a new intent to view the earthquake URI
                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                    // Send the intent to launch a new activity
                    startActivity(websiteIntent);
                }
            });
        }
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // Get details on the currently active default data network

        NetworkInfo activeNetwork = connMgr.getActiveNetworkInfo();
        isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        // If there is a network connection, fetch data
        loadingIndicator = findViewById(R.id.loading_indicator);

        if(isConnected){
            if(dateNumbers < 3810){
                // Get a reference to the LoaderManager, in order to interact with loaders.
                LoaderManager loaderManager = getLoaderManager();

                // Initialize the loader. Pass in the int ID constant defined above and pass in null for
                // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
                // because this activity implements the LoaderCallbacks interface).
                loaderManager.initLoader(EARTHQUAKE_LOADER_ID,null,this);
            }else {
                if (loadingIndicator != null) {
                    loadingIndicator.setVisibility(View.GONE);
                }
                Toast.makeText(this, "Days are too large ", Toast.LENGTH_SHORT).show();
            }



        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible

            if (loadingIndicator != null) {
                loadingIndicator.setVisibility(View.GONE);
            }
            // Update empty state with no connection error message
            mEmptyStateTextview.setText(R.string.no_internet_conection );
            imageEmpty.setVisibility(View.VISIBLE);
            iconRefresh.setVisibility(View.VISIBLE);


        }


    }


    @Override
    public Loader<List<PriceOil>> onCreateLoader(int id, Bundle args) {
        // Create a new loader for the given URL

        return new PriceOilLoader(this,USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<PriceOil>> loader, List<PriceOil> earthquakes) {

        // Hide loading indicator because the data has been loaded
        loadingIndicator = findViewById(R.id.loading_indicator);
        if (loadingIndicator != null) {
            loadingIndicator.setVisibility(View.GONE);
        }

        // Set empty state text to display "No earthquakes found."
        mEmptyStateTextview.setText(getString(R.string.no_priceOil) + "\n" +
                "  Please Try Again");



        // Clear the adapter of previous earthquake data
        mAdapter.clear();
        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if(earthquakes != null && !earthquakes.isEmpty()) {
            mAdapter.addAll(earthquakes);

        } else {
            //Set empty view.
            imageEmpty.setImageResource(R.drawable.emptyview_list);
            imageEmpty.setVisibility(View.VISIBLE);
            iconRefresh.setVisibility(View.VISIBLE);


        }
    }

    @Override
    public void onLoaderReset(Loader<List<PriceOil>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_priceoi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // When the user clicks FETCH, fetch the first 500 characters of
            // raw HTML from www.google.com.
            case R.id.action_settings:

                Intent settingsIntent = new Intent(this, SettingsActivityPriceOil.class);
                startActivity(settingsIntent);

                return true;
            // Clear the text and cancel download.
            case R.id.char_settings_data:
                if(dateNumbers < 3810){
                    ArrayList<String> dateB = QueryPriceOil.dateString;

                    if (isConnected && dateB.size() > 0) {

                        Intent activity = new Intent(PriceOilActivity.this, PriceOilMainActivity.class);
                        startActivity(activity);
                    }
                }


                return true;
            case R.id.lbma_gold:
                // Convert the String URL into a URI object (to pass into the Intent constructor)
                String websiteLbma = "http://www.opec.org/opec_web/en/data_graphs/40.htm";
                Uri lbmaWebsite = Uri.parse(websiteLbma);

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, lbmaWebsite);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
                return true;
        }
        return false;
    }

}
