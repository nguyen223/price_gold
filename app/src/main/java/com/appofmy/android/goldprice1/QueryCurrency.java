package com.appofmy.android.goldprice1;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WIN on 7/24/2017.
 */

public class QueryCurrency {
    private static final String LOG_TAG = QueryCurrency.class.getSimpleName();

    // Bien ngay thang.
    public static String dateRefresh;

    // Arraylist double
    public static ArrayList<String> listStringValue;

    public static String[] currencyCode, currencyName ;

    public static ArrayList<Double> ratesDateCurrency;


    public QueryCurrency() {
        dateRefresh = null;
        listStringValue = null;
        currencyCode = new String[]{"AUD", "BGN", "BRL", "CAD" ,"CHF","CNY", "CZK", "DKK", "GBP", "HKD", "HRK", "HUF", "IDR", "ILS", "INR", "JPY",
                "KRW", "MXN", "MYR", "NOK", "NZD", "PHP", "PLN", "RON", "RUB", "SEK", "SGD", "THB", "TRY", "ZAR", "EUR"};
        currencyName = new String[]{"Australian Dollar", "Bulgarian Lev", "Brazilian Real", "Canadian Dollar", "Swiss Franc", "Chinese Yuan", "Czech Republic Koruna",
                "Danish Krone", "British Pound Sterling", "Hong Kong Dollar", "Croatian Kuna", "Hungarian Forint", "Indonesian Rupiah", "Israeli New Sheqel",
                "Indian Rupee", "Japanese Yen", "South Korean Won", "Mexican Peso", "Malaysian Ringgit", "Norwegian Krone", "New Zealand Dollar", "Philippine Peso",
                "Polish Zloty", "Romanian Leu", "Russian Ruble", "Swedish Krona", "Singapore Dollar", "Thai Baht", "Turkish Lira", "South African Rand", "Euro"};



    }

    /**
     * Query the USGS dataset and return a list of {@link Earthquake} objects.
     */
    public static List<Currency> fetchCurrencyData(String requestUrl) {
        try{
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Perform HTTP request to the URL and receive a JSON response back

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s

        // Return the list .
        return extractCurrencyFromJson(requestUrl);
    }



    public static List<Currency> extractCurrencyFromJson(String eathquakeJsonUrl) {
        String[] currencyCode ={"AUD", "BGN", "BRL", "CAD", "CHF", "CNY", "CZK", "DKK", "GBP", "HKD", "HRK", "HUF", "IDR", "ILS", "INR", "JPY",
                "KRW", "MXN", "MYR", "NOK", "NZD", "PHP", "PLN", "RON", "RUB", "SEK", "SGD", "THB", "TRY", "ZAR", "EUR"};

        String[] currencyName = {"Australian Dollar", "Bulgarian Lev", "Brazilian Real", "Canadian Dollar", "Swiss Franc", "Chinese Yuan", "Czech Republic Koruna",
                "Danish Krone", "British Pound Sterling", "Hong Kong Dollar", "Croatian Kuna", "Hungarian Forint", "Indonesian Rupiah", "Israeli New Sheqel",
                "Indian Rupee", "Japanese Yen", "South Korean Won", "Mexican Peso", "Malaysian Ringgit", "Norwegian Krone", "New Zealand Dollar", "Philippine Peso",
                "Polish Zloty", "Romanian Leu", "Russian Ruble", "Swedish Krona", "Singapore Dollar", "Thai Baht", "Turkish Lira", "South African Rand", "Euro"};

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(eathquakeJsonUrl)) {
            return null;
        }
        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        listStringValue = new ArrayList<String>();

        ratesDateCurrency = new ArrayList<Double>();



        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.

        List<Currency> listCurrency = new ArrayList<>();

        try {
            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.
            JSONObject roots = getJSONObjectCurrency(eathquakeJsonUrl);
            String dates = roots.getString("date");
            dateRefresh = dates;

            JSONObject rateCurrency = roots.getJSONObject("rates");
            for (int i = 0; i < 31; i++){
                Double codeCurrency = rateCurrency.getDouble(currencyCode[i]);

                ratesDateCurrency.add(codeCurrency);
                String valueRates = codeCurrency.toString();
                listStringValue.add(valueRates);




            }
            listCurrency.add(new Currency(R.drawable.aud, currencyCode[0], currencyName[0], listStringValue.get(0)));
            listCurrency.add(new Currency(R.drawable.bgn, currencyCode[1], currencyName[1], listStringValue.get(1)));
            listCurrency.add(new Currency(R.drawable.brl, currencyCode[2], currencyName[2], listStringValue.get(2)));
            listCurrency.add(new Currency(R.drawable.cad, currencyCode[3], currencyName[3], listStringValue.get(3)));
            listCurrency.add(new Currency(R.drawable.chf, currencyCode[4], currencyName[4], listStringValue.get(4)));
            listCurrency.add(new Currency(R.drawable.cny, currencyCode[5], currencyName[5], listStringValue.get(5)));
            listCurrency.add(new Currency(R.drawable.czk, currencyCode[6], currencyName[6],listStringValue.get(6)));
            listCurrency.add(new Currency(R.drawable.dkk, currencyCode[7], currencyName[7], listStringValue.get(7)));
            listCurrency.add(new Currency(R.drawable.gbp, currencyCode[8], currencyName[8], listStringValue.get(8)));
            listCurrency.add(new Currency(R.drawable.hkd, currencyCode[9], currencyName[9], listStringValue.get(9)));
            listCurrency.add(new Currency(R.drawable.hrk, currencyCode[10], currencyName[10], listStringValue.get(10)));
            listCurrency.add(new Currency(R.drawable.huf, currencyCode[11], currencyName[11], listStringValue.get(11)));
            listCurrency.add(new Currency(R.drawable.idr, currencyCode[12], currencyName[12], listStringValue.get(12)));
            listCurrency.add(new Currency(R.drawable.ils, currencyCode[13], currencyName[13], listStringValue.get(13)));
            listCurrency.add(new Currency(R.drawable.inr, currencyCode[14], currencyName[14], listStringValue.get(14)));
            listCurrency.add(new Currency(R.drawable.jpy, currencyCode[15], currencyName[15], listStringValue.get(15)));
            listCurrency.add(new Currency(R.drawable.krw, currencyCode[16], currencyName[16], listStringValue.get(16)));
            listCurrency.add(new Currency(R.drawable.mxn, currencyCode[17], currencyName[17], listStringValue.get(17)));
            listCurrency.add(new Currency(R.drawable.myr, currencyCode[18], currencyName[18], listStringValue.get(18)));
            listCurrency.add(new Currency(R.drawable.nok, currencyCode[19], currencyName[19], listStringValue.get(19)));
            listCurrency.add(new Currency(R.drawable.nzd, currencyCode[20], currencyName[20], listStringValue.get(20)));
            listCurrency.add(new Currency(R.drawable.php, currencyCode[21], currencyName[21], listStringValue.get(21)));
            listCurrency.add(new Currency(R.drawable.pln, currencyCode[22], currencyName[22], listStringValue.get(22)));
            listCurrency.add(new Currency(R.drawable.ron, currencyCode[23], currencyName[23], listStringValue.get(23)));
            listCurrency.add(new Currency(R.drawable.rub, currencyCode[24], currencyName[24], listStringValue.get(24)));
            listCurrency.add(new Currency(R.drawable.sek, currencyCode[25], currencyName[25], listStringValue.get(25)));
            listCurrency.add(new Currency(R.drawable.sgd, currencyCode[26], currencyName[26], listStringValue.get(26)));
            listCurrency.add(new Currency(R.drawable.thb, currencyCode[27], currencyName[27], listStringValue.get(27)));
            listCurrency.add(new Currency(R.drawable.try1, currencyCode[28], currencyName[28], listStringValue.get(28)));
            listCurrency.add(new Currency(R.drawable.zar, currencyCode[29], currencyName[29], listStringValue.get(29)));
            listCurrency.add(new Currency(R.drawable.eur, currencyCode[30], currencyName[30], listStringValue.get(30)));






        } catch ( JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Return the list of currency double value.
        return listCurrency;


    }


    private static JSONObject getJSONObjectCurrency(String _url) throws Exception {
        if (_url.equals(""))
            throw new Exception("URL can't be empty");

        URL url = new URL(_url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setDoInput(true);
        conn.setRequestProperty("User-Agent", "android");
        conn.setRequestProperty("Accept", "application/json");
        conn.addRequestProperty("Content-Type", "application/json");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));

        if (!url.getHost().equals(conn.getURL().getHost())) {
            conn.disconnect();
            return new JSONObject();
        }
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        conn.disconnect();

        return new JSONObject(response.toString());

    }
}
