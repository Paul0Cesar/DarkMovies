package com.pcdeveloper.darkmovies.data.db;

import com.pcdeveloper.darkmovies.data.db.dao.ImageDao;

public interface DbHelper {

    void openDb();
    void closeDb();

    ImageDao createImageDao();
}
