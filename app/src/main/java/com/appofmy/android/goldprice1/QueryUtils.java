package com.appofmy.android.goldprice1;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {
    /** Tag for the log messages */
    public static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private static final int limit = 20;
    public static ArrayList<String> usdString,gbpString,euroString,dateString;

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
        dateString = null;
    }

    /**
     * Query the USGS dataset and return a list of {@link Earthquake} objects.
     */
    public static List<Earthquake> fetchEarthquakeData(String requestUrl) {
        try{
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Perform HTTP request to the URL and receive a JSON response back

        // Extract relevant fields from the JSON response and create a list of {@link Earthquake}s

        // Return the list of {@link Earthquake}s
        return extractEarthquakesFromJson(requestUrl);
    }

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing the given JSON response.
     */

    public static List<Earthquake> extractEarthquakesFromJson(String eathquakeJson) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(eathquakeJson)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding earthquakes to
        List<Earthquake> earthquakes = new ArrayList<>();
        usdString = new ArrayList<String>();
        gbpString = new ArrayList<String>();
        euroString = new ArrayList<String>();
        dateString = new ArrayList<String>();
        int numbers = EarthquakeActivity.dateNumbers;

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.
            JSONObject roots = getJSONObject(eathquakeJson);
            JSONObject dataset = roots.getJSONObject("dataset");
            for(int i = 0; i < numbers ;i++){
                JSONArray fist = dataset.getJSONArray("data");
                JSONArray array = fist.getJSONArray(i);
                // Extract the value for the key called "url"
                String value = array.toString();
                StringTokenizer tokens = new StringTokenizer(value,",");
                String date = tokens.nextToken();
                date = date.substring(2,12);
                String usdAM = tokens.nextToken();
                String usdPM = tokens.nextToken();
                String gbpAM = tokens.nextToken();
                String gbpPM = tokens.nextToken();
                String euroAM = tokens.nextToken();
                String euroPM = tokens.nextToken();
                euroPM = euroPM.substring(0,euroPM.length() - 1);
                String dateRefresh = dataset.getString("refreshed_at");
                usdString.add(usdPM);
                usdString.add(usdAM);
                gbpString.add(gbpPM);
                gbpString.add(gbpAM);
                euroString.add(euroPM);
                euroString.add(euroAM);
                dateString.add(date);


                // Create a new {@link Earthquake} object with the magnitude, location, time,
                // and url from the JSON response.
                earthquakes.add(new Earthquake(date, dateRefresh, usdAM,usdPM, gbpAM, gbpPM, euroAM, euroPM));
            }


        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return the list of earthquakes
        return earthquakes;
    }
    private static JSONObject getJSONObject(String _url) throws Exception {
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