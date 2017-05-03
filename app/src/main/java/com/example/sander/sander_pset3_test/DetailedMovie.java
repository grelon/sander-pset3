package com.example.sander.sander_pset3_test;

import java.net.URL;

/**
 * Created by sander on 2-5-17.
 */

public class DetailedMovie extends Movie {
    private String runtime;
    private String genre;
    private String director;
    private String plot;

    // constructor
    public DetailedMovie(Movie movie, String runtime, String genre, String director, String plot) {
        super(movie.getId(), movie.getTitle(), movie.getYear(), movie.getImdbID(), movie.getPoster());
        this.runtime = runtime;
        this.genre = genre;
        this.director = director;
        this.plot = plot;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }
}
