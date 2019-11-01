package com.pcdeveloper.darkmovies.data.db;

import com.pcdeveloper.darkmovies.data.db.dao.MovieDao;
import com.pcdeveloper.darkmovies.data.models.Movie;

import java.util.ArrayList;

public interface DbHelper {

    void openDb();
    void closeDb();

    void saveMovie(Movie e);
    ArrayList<Movie> getAlLMovies();
    void delMovie(Movie e);
}
