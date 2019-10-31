package com.pcdeveloper.darkmovies.data.db.dao.base;

import androidx.annotation.NonNull;

import io.realm.Realm;

public class Dao {

    protected Realm mRealm;

    public Dao(@NonNull Realm mRealm) {
        this.mRealm = mRealm;
    }

}
