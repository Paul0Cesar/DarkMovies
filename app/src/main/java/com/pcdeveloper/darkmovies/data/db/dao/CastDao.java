package com.pcdeveloper.darkmovies.data.db.dao;

import androidx.annotation.NonNull;

import com.pcdeveloper.darkmovies.data.db.dao.base.Dao;
import com.pcdeveloper.darkmovies.data.db.dao.base.DaoCrud;
import com.pcdeveloper.darkmovies.data.models.Cast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class CastDao extends Dao implements DaoCrud<Cast> {

    public CastDao(@NonNull Realm mRealm) {
        super(mRealm);
    }

    @Override
    public void save(final Cast tosave) {
        super.mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mRealm.copyToRealmOrUpdate(tosave);
            }
        });

    }

    @Override
    public void save(final List<Cast> tosaveArray) {
        super.mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mRealm.copyToRealmOrUpdate(tosaveArray);
            }
        });
    }

    @Override
    public Cast loadById(int id) {
      return null;
    }

    public ArrayList<Cast>getCastsByMovie(long movie_id){
        List<Cast> mv=super.mRealm.where(Cast.class).equalTo("movieId",movie_id).findAll();
        return (ArrayList<Cast>) mv;
    }

    @Override
    public Boolean findById(int id) {
        return null;
    }

    public Boolean findById(long id) {
        Cast mv=super.mRealm.where(Cast.class).equalTo("id",id).findFirst();
        if(mv!=null){
            return  true;
        }else{
            return  false;
        }
    }
    @Override
    public void remove(@NonNull final Cast object) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Cast mv=realm.where(Cast.class).equalTo("id",object.getId()).findFirst();
                mv.deleteFromRealm();
            }
        });
    }

    @Override
    public int count() {
        return 0;
    }
}
