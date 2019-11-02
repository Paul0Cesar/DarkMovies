package com.pcdeveloper.darkmovies.data.db;

import android.content.Context;
import android.util.Log;

import com.pcdeveloper.darkmovies.data.db.dao.CastDao;
import com.pcdeveloper.darkmovies.data.db.dao.GenresDao;
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
        CastDao castDao=new CastDao(mRealm);
        GenresDao genresDao=new GenresDao(mRealm);
        if(e!=null){
            ArrayList<Genres> genres=e.getGenres();
            ArrayList<Cast>casts=e.getCasts();

            ArrayList<Genres>tosave=new ArrayList<>();
            for(Genres x:genres){
                if(genresDao.findById(x.getId())){
                    x.setMovie_id(e.getId());
                    tosave.add(x);
                }
            }
            if(tosave!=null && tosave.size()>0){
                genresDao.save(tosave);
            }

            ArrayList<Cast>toSaveCast=new ArrayList<>();
            for(Cast xx:casts){
                if(castDao.findById(xx.getId())){
                    xx.setMovieId(e.getId());
                    toSaveCast.add(xx);
                }
            }
            if(toSaveCast!=null && toSaveCast.size()>0){
                castDao.save(casts);
            }

            movieDao.save(e);
        }
    }

    @Override
    public ArrayList<Movie> getAlLMovies() {
        checkIfRealmIsOpen();
        MovieDao movieDao= new MovieDao(mRealm);
        CastDao castDao=new CastDao(mRealm);
        GenresDao genresDao=new GenresDao(mRealm);
        ArrayList<Movie>result=new ArrayList<>();
        result=movieDao.loadAll();
        if(result!=null){
            for(Movie e:result){
                e.setCasts(castDao.getCastsByMovie(e.getId()));
                e.setGenres(genresDao.getByMovieId(e.getId()));
            }
        }

        return result;

    }

    @Override
    public void delMovie(Movie e) {
        checkIfRealmIsOpen();
        MovieDao movieDao= new MovieDao(mRealm);
        CastDao castDao=new CastDao(mRealm);
        GenresDao genresDao=new GenresDao(mRealm);

        if(e!=null){

            if(movieDao.findById(e.getId())){
                ArrayList<Genres> genres=e.getGenres();
                ArrayList<Cast>casts=e.getCasts();


                if(genres!=null){
                    for(Genres x:genres){
                        if(genresDao.findById(x.getId())){
                            genresDao.remove(x);
                        }
                    }
                }

                if(casts!=null){
                    for(Cast xx:casts){
                        if(castDao.findById(xx.getId())){
                            castDao.remove(xx);
                        }
                    }
                }

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
