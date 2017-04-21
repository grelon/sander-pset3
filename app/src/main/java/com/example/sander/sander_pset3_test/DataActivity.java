package com.example.sander.sander_pset3_test;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sander on 20-4-17.
 */

public class DataActivity extends AppCompatActivity{
    private ListView lvItems;
    private SearchResultAdapter adapter;
    private List<Movie> movieList;

    JSONArray searchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        // transform JSON from Intent back to an actual JSON
        String stSearchResults = getIntent().getStringExtra("data");
        try {
            JSONObject searchObject = new JSONObject(stSearchResults);
            searchResults = searchObject.getJSONArray("Search");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // set up ingredients for list
        makeMovieList(searchResults);
        makeAdapter(movieList);
    }

    public void makeAdapter(List<Movie> list) {
        adapter = new SearchResultAdapter(getApplicationContext(), list);
        lvItems.setAdapter(adapter);
    }

    public void makeMovieList(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            // store data of movie i in object
            JSONObject jsonObjectMovie = null;
            try {
                jsonObjectMovie = new JSONObject(searchResults.getJSONObject(i).toString());
                Log.d("log earlier", jsonObjectMovie.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            // use data to create Movie object
            Movie movie = null;
            
            movie.setId(i);
            try {
                Log.d("log", "1");
                Log.d("log", jsonObjectMovie.toString());
                movie.setTitle(jsonObjectMovie.getString("Title"));
                Log.d("log", "1.1");
                movie.setImdbID(jsonObjectMovie.getString("imdbID"));
                Log.d("log", "1.2");
                movie.setYear(Integer.parseInt(jsonObjectMovie.getString("Year")));
                Log.d("log", "1.3");
                movie.setPoster(jsonObjectMovie.getString("Poster"));
                Log.d("log", "1.4");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.d("log", "2");
            // add it to the list
            movieList.add(movie);
            Log.d("log", "3");
        }
    }


}
