package com.example.sander.sander_pset3_test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.sander.sander_pset3_test.R.id.lvSearchResults;

/**
 * Created by sander on 20-4-17.
 */

public class DataActivity extends AppCompatActivity{
    private ListView lvItems;
    private SearchResultAdapter adapter;
    private ArrayList<Movie> movieList;

    JSONArray searchResults;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        Log.d("log", "Data.onCreate: start");

        // transform JSON from String in the Intent back to an actual JSON
        String stSearchResults = getIntent().getStringExtra("data");
        try {
            JSONObject searchObject = new JSONObject(stSearchResults);
            searchResults = searchObject.getJSONArray("Search");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("log", "Data.onCreate: JSON fetched");

        // set up ingredients for list
        makeMovieList(searchResults);
        Log.d("log", "Data.onCreate: makeMovieList success");

        context = this;

    }

    public void makeAdapter(final ArrayList<Movie> list) {
        adapter = new SearchResultAdapter(getApplicationContext(), list);
        lvItems = (ListView) findViewById(lvSearchResults);
        lvItems.setAdapter(adapter);

        // listen for clicks on list items
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // store selectedMovie
                String selectedImdbID = movieList.get(position).getImdbID();

                // get the details of the movie
                DetailsAsyncTask asyncTask = new DetailsAsyncTask(getApplicationContext(), selectedImdbID);
                asyncTask.execute(selectedImdbID);
            }
        });
    }

    public void makeMovieList(JSONArray jsonArray) {
        if (jsonArray != null) {

            // initialize movieList as an ArrayList
            movieList = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                // store data of movie i in object
                JSONObject jsonObjectMovie = null;
                try {
                    jsonObjectMovie = new JSONObject(searchResults.getJSONObject(i).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // initialize Movie object
                Movie movie = null;

                // fill movie with data from JSON
                try {
                    movie = new Movie(
                            i,
                            jsonObjectMovie.getString("Title"),
                            jsonObjectMovie.getInt("Year"),
                            jsonObjectMovie.getString("imdbID"),
                            new URL(jsonObjectMovie.getString("Poster")));
                } catch (JSONException | MalformedURLException e) {
                    e.printStackTrace();
                }

                // add movie to the list
                movieList.add(movie);
            }

            // prepare and set adapter to list
            makeAdapter(movieList);
            Log.d("log", "Data.onCreate: makeAdapter success");
        }
        else {
            Toast.makeText(this,"no movies found", Toast.LENGTH_SHORT).show();
            return;
        }


    }
}
