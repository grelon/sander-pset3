package com.example.sander.sander_pset3_test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class DetailsActivity extends AppCompatActivity {

    DetailedMovie detailedMovie;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Log.d("log", "Details.onCreate: start");
        // unpack intent and create DetailedMovie object
        String stDetailedJSON = (String) getIntent().getStringExtra("detailedJSON");

        Log.d("log", "onCreate.detailedJSON");
        Log.d("log", "onCreate.stDetails");
        Log.d("log", stDetailedJSON);

        // convert string with details to JSON
        JSONObject details;
        try {
            details = new JSONObject(stDetailedJSON);
            Movie movie = new Movie(
                    0,
                    details.getString("Title"),
                    Integer.parseInt(details.getString("Year")),
                    details.getString("imdbID"),
                    new URL(details.getString("Poster")));

            detailedMovie = new DetailedMovie(
                    movie,
                    details.getString("Runtime"),
                    details.getString("Genre"),
                    details.getString("Director"),
                    details.getString("Plot"));
        } catch (JSONException | MalformedURLException e) {
            e.printStackTrace();
        }

        // get shared prefs
        prefs = getSharedPreferences("watchlist", MODE_PRIVATE);

        // set the content of the views
        setViews();

        Log.d("log", "Details.onCreate: all set");
    }

    private void setViews() {
        // get views
        TextView tvTitle = (TextView)findViewById(R.id.detailsTitle);
        TextView tvGenre = (TextView)findViewById(R.id.detailsGenre);
        TextView tvYear = (TextView)findViewById(R.id.detailsYear);
        TextView tvDirector = (TextView)findViewById(R.id.detailsDirector);
        TextView tvRuntime = (TextView)findViewById(R.id.detailsRuntime);
        TextView tvPlot = (TextView)findViewById(R.id.detailsPlot);
        Button mutateButton = (Button) findViewById(R.id.detailsButton);
        ImageView ivPoster = (ImageView) findViewById(R.id.detailsIvPoster);

        Log.d("log", "onCreate.getViews: success");

        // set views
        try {
            tvTitle.setText(detailedMovie.getTitle());
            tvGenre.setText(detailedMovie.getGenre());
            tvYear.setText(String.valueOf(detailedMovie.getYear()));
            tvDirector.setText(detailedMovie.getDirector());
            tvRuntime.setText(detailedMovie.getRuntime());
            tvPlot.setText(detailedMovie.getPlot());
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        // set the text in the add/remove button
        if (prefs.contains(tvTitle.getText().toString())) {
            mutateButton.setText("Delete");
        }
        else {
            mutateButton.setText("Add");
        }

        // set imageview
        Picasso.with(this)
                .load(String.valueOf(detailedMovie.getPoster()))
                .into(ivPoster);
    }

    public void mutateWatchlist(View view) {
        prefs = getSharedPreferences("watchlist", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // either delete or add the title to prefs
        if (prefs.contains(detailedMovie.getTitle())) {
            deleteFromPrefs(editor);
            Log.d("log", "Details.deleteFromPrefs");
        }
        else {
            addToPrefs(editor);
            Log.d("log", "Details.addToPrefs");
        }
        // commit changes to pref
        editor.apply();
        Log.d("log", "Details.mutate: success");

        // send user back to main
        Intent mainIntent =  new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        finish();
        Log.d("log", "Details: finished");
    }

    private void addToPrefs(SharedPreferences.Editor editor) {
        // add to prefs
        editor.putString(detailedMovie.getTitle(), detailedMovie.getImdbID());
    }

    private void deleteFromPrefs(SharedPreferences.Editor editor) {
        // delete from prefs
        editor.remove(detailedMovie.getTitle());
    }
}
