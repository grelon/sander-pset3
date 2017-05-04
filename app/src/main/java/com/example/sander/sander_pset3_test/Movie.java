package com.example.sander.sander_pset3_test;

import android.media.Image;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by sander on 21-4-17.
 */

public class Movie implements Serializable{
    private int id;
    private String title;
    private int year;
    private String imdbID;
    private String poster;

    // constructor
    public Movie(int id, String title, int year, String imdbID, String poster) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.imdbID = imdbID;
        this.poster = poster;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
