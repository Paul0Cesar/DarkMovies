package com.pcdeveloper.darkmovies.data.db;

import com.pcdeveloper.darkmovies.data.db.dao.MovieDao;

public interface DbHelper {

    void openDb();
    void closeDb();

    MovieDao createMovieDao();
}
