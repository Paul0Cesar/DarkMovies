package com.pcdeveloper.darkmovies.data.db.dao;

import androidx.annotation.NonNull;

import com.pcdeveloper.darkmovies.data.db.dao.base.Dao;
import com.pcdeveloper.darkmovies.data.db.dao.base.DaoCrud;
import com.pcdeveloper.darkmovies.data.models.Cast;
import com.pcdeveloper.darkmovies.data.models.Genres;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class GenresDao extends Dao implements DaoCrud<Genres> {


    public GenresDao(@NonNull Realm mRealm) {
        super(mRealm);
    }

    @Override
    public void save(final Genres tosave) {
        super.mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mRealm.copyToRealmOrUpdate(tosave);
            }
        });
    }

    @Override
    public void save(final List<Genres> tosaveArray) {
        super.mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mRealm.copyToRealmOrUpdate(tosaveArray);
            }
        });
    }

    @Override
    public Genres loadById(int id) {
        return null;
    }

    public ArrayList<Genres>getByMovieId(long movie_id){
        List<Genres> mv=super.mRealm.where(Genres.class).equalTo("movieId",movie_id).findAll();
        return (ArrayList<Genres>) mv;
    }

    @Override
    public Boolean findById(int id) {
        Genres mv=super.mRealm.where(Genres.class).equalTo("id",id).findFirst();
        if(mv!=null){
            return  true;
        }else{
            return  false;
        }
    }

    @Override
    public void remove(@NonNull final Genres object) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Genres mv=realm.where(Genres.class).equalTo("id",object.getId()).findFirst();
                mv.deleteFromRealm();
            }
        });
    }

    @Override
    public int count() {
        return 0;
    }
}
