package com.pcdeveloper.darkmovies.data.models;

import com.google.gson.annotations.SerializedName;

public class Countrie {
    @SerializedName("iso_3166_1")
    private String iso;
    private String name;
    private long lat;
    private long log;

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLat() {
        return lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public long getLog() {
        return log;
    }

    public void setLog(long log) {
        this.log = log;
    }
}
