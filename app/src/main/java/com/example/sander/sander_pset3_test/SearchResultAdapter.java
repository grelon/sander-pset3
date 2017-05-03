package com.example.sander.sander_pset3_test;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sander on 21-4-17.
 */

public class SearchResultAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Movie> searchResults;

    protected SearchResultAdapter(Context context, ArrayList<Movie> searchResults) {
        this.context = context;
        this.searchResults = searchResults;
    }

    @Override
    public int getCount() {
        return searchResults.size();
    }

    @Override
    public Object getItem(int position) {
        return searchResults.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate view
        View view = View.inflate(context, R.layout.list_search_results, null);

        // get views within view
        TextView tvTitle = (TextView)view.findViewById(R.id.movieTitle);
        TextView tvYear = (TextView)view.findViewById(R.id.movieYear);

        // set content of views
        tvTitle.setText(searchResults.get(position).getTitle());
        tvYear.setText(Integer.toString(searchResults.get(position).getYear()));

        // return view
        return view;
    }
}
