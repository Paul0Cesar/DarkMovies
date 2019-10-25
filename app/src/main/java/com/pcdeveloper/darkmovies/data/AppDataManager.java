package com.pcdeveloper.darkmovies.data;

import android.content.Context;

import com.pcdeveloper.darkmovies.data.db.DbHelper;
import com.pcdeveloper.darkmovies.data.network.ApiHelper;
import com.pcdeveloper.darkmovies.data.prefs.Prefs;

import javax.inject.Inject;

public class AppDataManager  implements DataManager {


    private  ApiHelper mApiHelper;
    private  Context mContext;
    private  DbHelper mDbHelper;
    private  Prefs mPreferencesHelper;


    @Inject
    public AppDataManager(Context context, DbHelper dbHelper, Prefs preferencesHelper, ApiHelper apiHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }
}
