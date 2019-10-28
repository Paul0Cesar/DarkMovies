package com.pcdeveloper.darkmovies.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PageMovie {

    @SerializedName("page")
    @Expose
    private int numberOfPage;
    @SerializedName("results")
    @Expose
    private ArrayList<Movie> movies;

    @SerializedName("total_pages")
    @Expose
    private int totalPages;

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public void setAllMovies(ArrayList<Movie> movies){
        if(movies!=null && movies.size()>0){
            this.movies.addAll(movies);
        }
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
