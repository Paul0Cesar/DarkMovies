package com.pcdeveloper.darkmovies.data.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Image extends RealmObject {

    @PrimaryKey
    private int id;
    private byte[] poster;
    private byte[] backPoster;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getPoster() {
        return poster;
    }

    public void setPoster(byte[] poster) {
        this.poster = poster;
    }

    public byte[] getBackPoster() {
        return backPoster;
    }

    public void setBackPoster(byte[] backPoster) {
        this.backPoster = backPoster;
    }
}
