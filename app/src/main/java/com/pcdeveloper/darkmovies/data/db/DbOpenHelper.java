package com.pcdeveloper.darkmovies.data.db;

import android.content.Context;

import com.pcdeveloper.darkmovies.data.db.dao.MovieDao;
import com.pcdeveloper.darkmovies.data.models.Cast;
import com.pcdeveloper.darkmovies.data.models.Genres;
import com.pcdeveloper.darkmovies.data.models.Movie;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmConfiguration;

@Singleton
public class DbOpenHelper implements DbHelper {

    private Context mContext;
    private Realm mRealm;


    @Inject
    public DbOpenHelper(Context mContext) {
        this.mContext = mContext;
        Realm.init(mContext);
        RealmConfiguration config = new RealmConfiguration.Builder().name("database.realm").build();
        Realm.setDefaultConfiguration(config);
    }

    @Override
    public void openDb() {
        mRealm =  Realm.getDefaultInstance();

    }

    @Override
    public void closeDb() {
        if(mRealm!=null){
            mRealm.close();
        }

    }

    @Override
    public void saveMovie(Movie e) {
        checkIfRealmIsOpen();
        MovieDao movieDao= new MovieDao(mRealm);
        if(e!=null){
            movieDao.save(e);
        }
    }

    @Override
    public ArrayList<Movie> getAlLMovies() {
        checkIfRealmIsOpen();
        MovieDao movieDao= new MovieDao(mRealm);
        ArrayList<Movie>result=new ArrayList<>();
        result=movieDao.loadAll();
        return result;
    }

    @Override
    public void delMovie(Movie e) {
        checkIfRealmIsOpen();
        MovieDao movieDao= new MovieDao(mRealm);
        if(e!=null){
            if(movieDao.findById(e.getId())){
                movieDao.remove(e);
            }


        }

    }


    private void checkIfRealmIsOpen(){
        if(mRealm==null || mRealm.isClosed()){
            throw new IllegalStateException("RealmManager: Realm is closed, call open() method first");
        }
    }

}
