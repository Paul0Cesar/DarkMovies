package com.pcdeveloper.darkmovies.data.db.dao;

import androidx.annotation.NonNull;

import com.pcdeveloper.darkmovies.data.db.dao.base.Dao;
import com.pcdeveloper.darkmovies.data.db.dao.base.DaoCrud;
import com.pcdeveloper.darkmovies.data.models.Image;
import java.util.List;
import io.realm.Realm;


public class ImageDao extends Dao implements DaoCrud<Image> {

    public ImageDao(@NonNull Realm mRealm) {
        super(mRealm);
    }

    @Override
    public void save(final Image tosave) {
        super.mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mRealm.copyToRealmOrUpdate(tosave);
            }
        });

    }

    @Override
    public void save(final List<Image> tosaveArray) {
        super.mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mRealm.copyToRealmOrUpdate(tosaveArray);
            }
        });

    }



    @Override
    public Image loadById(int id) {
        Image img=super.mRealm.where(Image.class).equalTo("id",id).findFirst();
        return img;
    }

    @Override
    public Boolean findById(int id) {
        Image img=super.mRealm.where(Image.class).equalTo("id",id).findFirst();
        if(img!=null){
            return  true;
        }else{
            return  false;
        }

    }

    @Override
    public void remove(@NonNull final Image object) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                object.deleteFromRealm();
            }
        });

    }


    @Override
    public int count() {
        return (int) mRealm.where(Image.class).count();
    }
}
