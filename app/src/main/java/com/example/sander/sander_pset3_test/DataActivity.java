package com.example.sander.sander_pset3_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by sander on 20-4-17.
 */

public class DataActivity extends AppCompatActivity{
    TextView tvResult;
    ListView lvItems;
    ArrayList<String> movieArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        tvResult = (TextView) findViewById(R.id.tvFound);
        lvItems = (ListView) findViewById(R.id.listViewId);

        Bundle extras = getIntent().getExtras();
        movieArray = (ArrayList<String>) extras.getSerializable("data");

        makeAdapter();
    }

    public void makeAdapter() {
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, android.R.id.text1, movieArray);
        lvItems = (ListView) findViewById(R.id.listViewId);
        assert lvItems != null;
        lvItems.setAdapter(arrayAdapter);

    }
}
