package com.pcdeveloper.darkmovies.data.db;

import android.content.Context;

import javax.inject.Inject;

public class DbOpenHelper implements DbHelper {

    private Context mContext;

    @Inject
    public DbOpenHelper(Context mContext) {
        this.mContext = mContext;
    }
}
