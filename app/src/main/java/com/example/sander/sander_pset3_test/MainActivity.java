package com.example.sander.sander_pset3_test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.support.constraint.R.id.parent;

public class MainActivity extends AppCompatActivity {

    EditText movieQuery;
    List<String> watchlist;
    List<String> watchlistImdbIDs;
    ListView lvWatchlist;
    ArrayAdapter<String> adapterWatchlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("log", "Main.onCreate");

        movieQuery = (EditText) findViewById(R.id.eMovie);
        assert movieQuery != null;

        populateWatchlist();

        setListView();



    }

    private void setListView() {
        // if watchlist is not empty, show list
        if (watchlist.size() != 0) {
            Log.d("log", "setListView: start");
            // prepare adapter
            adapterWatchlist = new ArrayAdapter<>(this, R.layout.list_watchlist, R.id.watchlistTitle, watchlist);

            // get listview
            lvWatchlist = (ListView) findViewById(R.id.lvWatchlist);

            // set adapter to list
            lvWatchlist.setAdapter(adapterWatchlist);
            Log.d("log", "setListView: success");

            // set onClicklistener
            lvWatchlist.setOnItemClickListener(new watchListener());
        }
        // if watchlist is empty
        else {
            // tell user that list is empty
            Log.d("log", "Main.setListView: list is empty");
        }
    }

    private void populateWatchlist() {
        // get shared prefs
        SharedPreferences prefs =
                getApplicationContext().getSharedPreferences("watchlist", MODE_PRIVATE);

        // initialize editor for prefs
        SharedPreferences.Editor editor = prefs.edit();

        // get all titles from prefs
        Map<String, ?> keys = prefs.getAll();

        // iterate through key value pairs from prefs and add name of movie to title
        watchlist = new ArrayList<>();
        watchlistImdbIDs = new ArrayList<>();
        for (Map.Entry<String, ?> entry : keys.entrySet()) {
            watchlist.add(entry.getKey());
            watchlistImdbIDs.add((String)entry.getValue());
        }

        Log.d("log", watchlist.toString());
    }

    public void movieSearch(View view) {
        String movieSearch = movieQuery.getText().toString();
        MovieAsyncTask asyncTask = new MovieAsyncTask(this);
        asyncTask.execute(movieSearch);
        movieQuery.getText().clear();
    }

    public void resultsStartIntent(JSONObject movieData) {
        Intent dataIntent = new Intent(this, DataActivity.class);
        dataIntent.putExtra("data", movieData.toString());
        startActivity(dataIntent);
        Log.d("log", "movieData.toString: " + movieData.toString());
        finish();
        Log.d("log", "movieStartIntent: succes");
    }

    private class watchListener implements AdapterView.OnItemClickListener {
        public void onItemClick(
                AdapterView<?> parent,
                View view,
                int position,
                long id) {

            // start DetailsAsyncTask for list item and move to Details
            String imdbID = watchlistImdbIDs.get(position);
            DetailsAsyncTask detailsAsyncTask = new DetailsAsyncTask(
                    getApplicationContext(),
                    imdbID);
            detailsAsyncTask.execute(imdbID);
        }
    }
}
