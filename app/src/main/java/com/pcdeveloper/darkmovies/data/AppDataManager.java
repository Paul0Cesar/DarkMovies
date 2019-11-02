package com.pcdeveloper.darkmovies.data;

import android.content.Context;

import com.pcdeveloper.darkmovies.data.db.DbHelper;
import com.pcdeveloper.darkmovies.data.db.dao.MovieDao;
import com.pcdeveloper.darkmovies.data.models.Movie;
import com.pcdeveloper.darkmovies.data.network.ApiHelper;
import com.pcdeveloper.darkmovies.data.network.CallBack.CallBackto;
import com.pcdeveloper.darkmovies.data.prefs.PrefsHelper;

import java.util.ArrayList;

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
    public void saveMovie(Movie e) {
        mDbHelper.saveMovie(e);
    }

    @Override
    public ArrayList<Movie> getAlLMovies() {
        return mDbHelper.getAlLMovies();
    }

    @Override
    public void delMovie(Movie e) {
        mDbHelper.delMovie(e);
    }



    @Override
    public void getInfosByMovieId(long movie_id, String language,CallBackto<Movie> callBackto) {
        this.mApiHelper.getInfosByMovieId(movie_id,language,callBackto);
    }

    @Override
    public void addFavorites(long id) {
        mPreferencesHelper.addFavorites(id);
    }

    @Override
    public Boolean isFavorite(long id) {
        return mPreferencesHelper.isFavorite(id);
    }
}
