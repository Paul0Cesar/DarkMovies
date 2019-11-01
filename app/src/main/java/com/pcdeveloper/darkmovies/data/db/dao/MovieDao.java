package com.pcdeveloper.darkmovies.data.db.dao;

import androidx.annotation.NonNull;

import com.pcdeveloper.darkmovies.data.db.dao.base.Dao;
import com.pcdeveloper.darkmovies.data.db.dao.base.DaoCrud;
import com.pcdeveloper.darkmovies.data.models.Poster;

import java.util.List;
import io.realm.Realm;


public class MovieDao extends Dao implements DaoCrud<Poster> {

    public MovieDao(@NonNull Realm mRealm) {
        super(mRealm);
    }

    @Override
    public void save(final Poster tosave) {
        super.mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mRealm.copyToRealmOrUpdate(tosave);
            }
        });

    }

    @Override
    public void save(final List<Poster> tosaveArray) {
        super.mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mRealm.copyToRealmOrUpdate(tosaveArray);
            }
        });

    }



    @Override
    public Poster loadById(int id) {
        Poster mv=super.mRealm.where(Poster.class).equalTo("id",id).findFirst();
        return mv;
    }

    @Override
    public Boolean findById(int id) {
        Poster mv=super.mRealm.where(Poster.class).equalTo("id",id).findFirst();
        if(mv!=null){
            return  true;
        }else{
            return  false;
        }

    }

    @Override
    public void remove(@NonNull final Poster object) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                object.deleteFromRealm();
            }
        });

    }


    @Override
    public int count() {
        return (int) mRealm.where(Poster.class).count();
    }
}
