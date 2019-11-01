package com.pcdeveloper.darkmovies.data.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Genres  extends RealmObject {

    @PrimaryKey
    private  int id;
    private long movie_id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(long movie_id) {
        this.movie_id = movie_id;
    }
}
