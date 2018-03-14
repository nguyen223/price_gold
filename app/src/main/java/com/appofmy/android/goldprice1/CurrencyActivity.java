package com.appofmy.android.goldprice1;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CurrencyActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Currency>> {

    private static final String CURRENCY_REQUEST_URL = "https://api.fixer.io/latest?base=USD";

    private static final int CURRENCY_LOADER = 2;
    private TextView mEmptyStateTextview;
    private ImageView imageEmpty, iconRefresh;

    private CurrencyAdapter mAdapter;
    public ImageView iconLbma, iconOpec;
    private ViewGroup euroBank;
    // (arbitrary) request code for the purchase flow
    static final int RC_REQUEST = 10001;
    private static final String TAG = "GOLD LBMA";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkBhCUh9Kjj0suRtgV0InXC/1F0NVXykNAec3qN21JowFeKou9qFfoE3WSv+6ZwzhWjm08p4i/rIpc3+8qy6luB8An+NvDqxn/dtmvnO2vAMzfSJbsK/FNT7A8ooAz8uRHux04KoagCntxETZttFhaDHQbsnpGhJOcSfjJKmxTJX8yZYZIEIGwJX6NkgF8dxVZRMO+hfS4IAWDQnxOqQs/qla8VM+H92M5lEtc4erf+mP4UnOs6ezQCQnzKfXI4i0uxcq5miZKUAjb/EMvtA7OxQBa/PlGijbK7kybm+5U/KfjFq8NS8L3SWkTrHLcVIwV6Fy25nNZEZidzD6DiSwmQIDAQAB";
        // Some sanity checks to see if the developer (that's you!) really followed the
        // instructions to run this sample (don't put these checks on your app!)



        //Setting up ArrayAdapter.
        mAdapter = new CurrencyAdapter(this, new ArrayList<Currency>());
        // finding the listView and setting the adapter to it
        ListView listView = (ListView)findViewById(R.id.list_currencyview);
        //Set Adapter.
        if (listView != null) {
            listView.setAdapter(mAdapter);
        }
        mEmptyStateTextview = (TextView)findViewById(R.id.empty_view_currency);
        // Create a new adapter that takes an empty list of currency as input.
        if (listView != null) {
            listView.setEmptyView(mEmptyStateTextview);
        }
        imageEmpty = (ImageView)findViewById(R.id.empty_image_curreny);
        if (imageEmpty != null) {
            imageEmpty.setVisibility(View.GONE);
        }

        iconLbma = (ImageView) findViewById(R.id.lbma_activity_currency);

        iconLbma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(CurrencyActivity.this, EarthquakeActivity.class);
                startActivity(intent);

            }
        });
        iconOpec = (ImageView) findViewById(R.id.priceoil_activity);
        iconOpec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(CurrencyActivity.this, PriceOilActivity.class);
                startActivity(intent);
            }
        });


        iconRefresh = (ImageView)findViewById(R.id.image_refresh_currency) ;
        iconRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(CurrencyActivity.this, CurrencyActivity.class);
                startActivity(intent);
            }
        });
        iconRefresh.setVisibility(View.GONE);
        euroBank = (ViewGroup)findViewById(R.id.euro_bank_title) ;

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // Get details on the currently active default data network

        NetworkInfo activeNetwork = connMgr.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        // If there is a network connection, fetch data

        if(isConnected){
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(CURRENCY_LOADER,null,this);

        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_currency);
            if (loadingIndicator != null) {
                loadingIndicator.setVisibility(View.GONE);
            }
            // Update empty state with no connection error message
            mEmptyStateTextview.setText(R.string.no_internet_conection );
            imageEmpty.setVisibility(View.VISIBLE);
            iconRefresh.setVisibility(View.VISIBLE);
            euroBank.setVisibility(View.GONE);



        }


    }

    @Override
    public Loader<List<Currency>> onCreateLoader(int id, Bundle args) {
        // Create a new loader for the given URL
        return new CurrencyLoader(this,CURRENCY_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Currency>> loader, List<Currency> data) {

        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_currency);
        if (loadingIndicator != null) {
            loadingIndicator.setVisibility(View.GONE);
        }

        // Set empty state text to display "No earthquakes found."
        mEmptyStateTextview.setText(getString(R.string.no_currency_exchenge) + "\n" +
                "  Please Try Again");


        // Clear the adapter of previous earthquake data
        mAdapter.clear();
        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if(data != null && !data.isEmpty()) {
            TextView dateValue = (TextView)findViewById(R.id.date_currency);
            if (dateValue != null) {
                dateValue.setText(QueryCurrency.dateRefresh);
            }
            //Set data.
            mAdapter.addAll(data);
        } else {
            //Set empty view.
            imageEmpty.setImageResource(R.drawable.emptyview_list);
            imageEmpty.setVisibility(View.VISIBLE);
            iconRefresh.setVisibility(View.VISIBLE);
            euroBank.setVisibility(View.GONE);

        }

    }

    @Override
    public void onLoaderReset(Loader<List<Currency>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_exchange, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // When the user clicks FETCH, fetch the first 500 characters of
            // raw HTML from www.google.com.
            case R.id.calculate:
                ArrayList<HashMap<String,Object>> items =new ArrayList<HashMap<String,Object>>();

                final PackageManager pm = getPackageManager();
                List<PackageInfo> packs = pm.getInstalledPackages(0);
                for (PackageInfo pi : packs) {
                    if(pi.packageName.toLowerCase().contains("calcul")){
                        HashMap<String, Object> map = new HashMap<String, Object>();
                        map.put("GOLD PRICE LBMA-OPEC", pi.applicationInfo.loadLabel(pm));
                        map.put("com.myapp.android.pricegold", pi.packageName);
                        items.add(map);
                    }
                }
                if(items.size()>=1){
                    String packageName = (String) items.get(0).get("com.myapp.android.pricegold");
                    Intent i = pm.getLaunchIntentForPackage(packageName);
                    if (i != null)
                        startActivity(i);
                }
                else{
                    // Application not found
                    Toast.makeText(this, "Not default calculator on devieces ", Toast.LENGTH_SHORT).show();
                }

                return true;
            // Clear the text and cancel download.
            case R.id.exchange_curency:
                ArrayList<String> value = QueryCurrency.listStringValue;
                if(QueryCurrency.dateRefresh != null && value.size() > 0){
                    Intent conversion = new Intent(CurrencyActivity.this, CurrencyConversion.class);
                    startActivity(conversion);
                }

                return true;
        }
        return false;
    }


}
