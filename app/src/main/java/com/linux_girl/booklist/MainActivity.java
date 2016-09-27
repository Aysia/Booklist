package com.linux_girl.booklist;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import java.util.ArrayList;

public class MainActivity extends Activity {
    /** tag for logs */
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    //JSON Node Names
    private static final String BOOK_TITLE = "title";
    private static final String BOOK_AUTHOR = "authors";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list);

        new JSONParse().execute();
    }

    /** Update the UI with values from @Books */
    private void updateUI(ArrayList<Books> books) {

        /**
         * Find a reference to the {@link ListView} in the layout
         */
        ListView listBookView = (ListView) findViewById(R.id.list);

        //Create empty View for reporting no results
        listBookView.setEmptyView(findViewById(R.id.empty_list_item));

        /**
         * Create an {@link BooksAdapter}, whose data source is a list of
         * {@link books}. The adapter knows how to create list items for each
         * item in the list.
         */
        BooksAdapter adapter = new BooksAdapter(this, books);

        /** Set the adapter on the {@link ListView}
         so the list can be populated in the user interface
         */
        listBookView.setAdapter(adapter);
    }

    /** AsyncTask to get JSONResponse from the URL */
    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            QueryUtils jParser = new QueryUtils();

            // Getting JSON from URL
            JSONObject json = jParser.getJSONFromUrl();
            return json;
        }
        //@Override
        protected void onPostExecute(JSONObject json) {
            ArrayList<Books> bookArray = new ArrayList<Books>();
            try {
                // Getting JSON Array
                JSONArray itemsArray = json.getJSONArray("items");

                for (int i = 0; i < itemsArray.length(); i++) {
                    JSONObject book = itemsArray.getJSONObject(i);
                    JSONObject volumeInfo = book.getJSONObject("volumeInfo");
                    // Storing  JSON item in a Variable
                    String bTitle = volumeInfo.getString(BOOK_TITLE);
                    String bAuthor = volumeInfo.getString(BOOK_AUTHOR).replace("[", "").replace("\"", "").replace("]", "").replace(",",", ");

                    //fill values to (@Books)
                    bookArray.add(new Books(bTitle, bAuthor));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            updateUI(bookArray);
        }
    }
}