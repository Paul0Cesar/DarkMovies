package com.pcdeveloper.darkmovies.data.network;

import android.content.Context;

import javax.inject.Inject;

public class AppApiHelper implements  ApiHelper {

    private Context mContext;

    @Inject
    public AppApiHelper(Context mContext) {
        this.mContext = mContext;
    }
}
