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
    private ArrayList<Poster> posters;

    @SerializedName("total_pages")
    @Expose
    private int totalPages;

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    public ArrayList<Poster> getPosters() {
        return posters;
    }

    public void setPosters(ArrayList<Poster> posters) {
        this.posters = posters;
    }

    public void setAllMovies(ArrayList<Poster> posters){
        if(posters !=null && posters.size()>0){
            this.posters.addAll(posters);
        }
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
