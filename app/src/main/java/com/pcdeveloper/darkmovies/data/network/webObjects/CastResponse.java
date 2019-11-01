package com.pcdeveloper.darkmovies.data.network.webObjects;

import com.google.gson.annotations.SerializedName;
import com.pcdeveloper.darkmovies.data.models.Cast;

import java.util.ArrayList;

public class CastResponse {

    private long id;
    @SerializedName("cast")
    private ArrayList<Cast>casts;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<Cast> getCasts() {
        return casts;
    }

    public void setCasts(ArrayList<Cast> casts) {
        this.casts = casts;
    }
}
