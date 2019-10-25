package com.pcdeveloper.darkmovies.data.prefs;

import android.content.Context;

import javax.inject.Inject;

public class Prefs implements PrefsHelper {

    private Context mContext;

    @Inject
    public Prefs(Context mContext) {
        this.mContext = mContext;
    }
}
