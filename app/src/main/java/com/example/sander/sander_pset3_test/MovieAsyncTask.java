package com.example.sander.sander_pset3_test;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sander on 20-4-17.
 */

public class MovieAsyncTask extends AsyncTask<String, Integer, String> {
    Context context;
    MainActivity mainActivity;

    public MovieAsyncTask(MainActivity main) {
        this.mainActivity = main;
        this.context = this.mainActivity.getApplicationContext();
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(context,"searching for movies...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground (String... params) {
        return HttpRequestHelper.downloadFromServer(params);
    }

    @Override
    protected void onPostExecute(String result) {

        super.onPostExecute(result);

        JSONObject searchResults = null;
        try {
            searchResults = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.mainActivity.resultsStartIntent(searchResults);
    }
}
