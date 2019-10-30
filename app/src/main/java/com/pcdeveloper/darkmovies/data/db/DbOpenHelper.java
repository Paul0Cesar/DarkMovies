package com.pcdeveloper.darkmovies.data.db;

import android.content.Context;

import com.pcdeveloper.darkmovies.data.db.dao.MovieDao;

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
    public MovieDao createMovieDao() {
        checkIfRealmIsOpen();
        return new MovieDao(mRealm);
    }

    private void checkIfRealmIsOpen(){
        if(mRealm==null || mRealm.isClosed()){
            throw new IllegalStateException("RealmManager: Realm is closed, call open() method first");
        }
    }

}
