package com.pcdeveloper.darkmovies.data.network.webObjects;

import com.google.gson.annotations.SerializedName;
import com.pcdeveloper.darkmovies.data.models.Movie;

import java.util.ArrayList;

public class SearchResult {

    @SerializedName("results")
    private ArrayList<Movie>movies;

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
}
