package com.example.sander.sander_pset3_test;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sander on 21-4-17.
 */

public class SearchResultAdapter extends BaseAdapter {

    public Context context;
    public List<Movie> searchResults;

    public SearchResultAdapter(Context context, List<Movie> searchResults) {
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
        View view = View.inflate(context, R.layout.list_search_results, null);
        TextView tvTitle = (TextView)view.findViewById(R.id.movieTitle);

        // set text view
        tvTitle.setText(searchResults.get(position).getTitle());
        return null;
    }
}
