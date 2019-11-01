package com.pcdeveloper.darkmovies.data.db.dao;

import androidx.annotation.NonNull;

import com.pcdeveloper.darkmovies.data.db.dao.base.Dao;
import com.pcdeveloper.darkmovies.data.db.dao.base.DaoCrud;
import com.pcdeveloper.darkmovies.data.models.Genres;

import java.util.List;

import io.realm.Realm;

public class GenresDao extends Dao implements DaoCrud<Genres> {


    public GenresDao(@NonNull Realm mRealm) {
        super(mRealm);
    }

    @Override
    public void save(Genres tosave) {

    }

    @Override
    public void save(List<Genres> tosaveArray) {

    }

    @Override
    public Genres loadById(int id) {
        return null;
    }

    @Override
    public Boolean findById(int id) {
        return null;
    }

    @Override
    public void remove(@NonNull Genres object) {

    }

    @Override
    public int count() {
        return 0;
    }
}
