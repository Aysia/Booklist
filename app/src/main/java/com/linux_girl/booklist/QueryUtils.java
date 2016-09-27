package com.linux_girl.booklist;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public final class QueryUtils {

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = null;

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /** URL to be used to get book data */
    private static final String GOOGLE_BOOKS_REQUEST_URL =
            //"https://www.googleapis.com/books/v1/volumes?q=quiltingwithjesus";
          "https://www.googleapis.com/books/v1/volumes?q=cupcakes&maxResults=25";

    /**
     * Constructor
     */
    public QueryUtils() {
    }
    String jsonResponse = "";
    public JSONObject getJSONFromUrl() {
        // Making HTTP request
        try {
            URL url = createUrl(GOOGLE_BOOKS_REQUEST_URL);

            // Perform HTTP request to the URL and receive a JSON response back
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.i(LOG_TAG, "No Data Found at the URL");
                return null;
            }

        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(jsonResponse);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON Object
        return jObj;
    }

    private URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }

    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}