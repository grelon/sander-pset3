package com.example.sander.sander_pset3_test;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sander on 1-5-17.
 */

public class DetailsAsyncTask extends AsyncTask<String, Integer, String> {
    Context context;
    String imdbID;

    public DetailsAsyncTask(Context context, String imdbID) {
        this.context = context;
        this.imdbID = imdbID;
    }

    @Override
    protected String doInBackground(String... params) {
        Log.d("log", "DetailsAsyncTask.doInBackground: started");
        return HttpRequestHelper.downloadPlot(params);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.d("log", result);
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra("detailedJSON", result);
        context.startActivity(intent);
        // how do i finish Data from this location?
        Log.d("log", "detailsStartIntent: succes");
    }
}

