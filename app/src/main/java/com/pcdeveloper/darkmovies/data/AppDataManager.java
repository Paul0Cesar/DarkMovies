package com.pcdeveloper.darkmovies.data;

import android.content.Context;

import com.pcdeveloper.darkmovies.data.db.DbHelper;
import com.pcdeveloper.darkmovies.data.db.dao.ImageDao;
import com.pcdeveloper.darkmovies.data.network.ApiHelper;
import com.pcdeveloper.darkmovies.data.network.CallBack.CallBackto;
import com.pcdeveloper.darkmovies.data.prefs.Prefs;
import com.pcdeveloper.darkmovies.data.prefs.PrefsHelper;

import javax.inject.Inject;

public class AppDataManager  implements DataManager {


    private  ApiHelper mApiHelper;
    private  Context mContext;
    private  DbHelper mDbHelper;
    private  PrefsHelper mPreferencesHelper;


    @Inject
    public AppDataManager(Context context, DbHelper dbHelper, PrefsHelper preferencesHelper, ApiHelper apiHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public void getNowPlaying(String language, int page, CallBackto callBackto) {
         mApiHelper.getNowPlaying(language,page,callBackto);
    }

    @Override
    public void openDb() {
        mDbHelper.openDb();
    }

    @Override
    public void closeDb() {
        mDbHelper.closeDb();
    }

    @Override
    public ImageDao createImageDao() {
        return mDbHelper.createImageDao();
    }

    @Override
    public String getImageConfig() {
        return "https://image.tmdb.org/t/p/w500/";
    }
}
