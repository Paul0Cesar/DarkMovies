package com.pcdeveloper.darkmovies.ui.infos;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pcdeveloper.darkmovies.data.DataManager;
import com.pcdeveloper.darkmovies.data.models.Movie;
import com.pcdeveloper.darkmovies.data.models.Poster;
import com.pcdeveloper.darkmovies.data.network.CallBack.CallBackto;
import com.pcdeveloper.darkmovies.ui.base.BaseViewModel;
import com.pcdeveloper.darkmovies.util.Constants;
import com.pcdeveloper.darkmovies.util.Err;

public class MovieInfosViewModel extends BaseViewModel {

    private MutableLiveData<Movie> mMovie=new MutableLiveData<>();
    private MutableLiveData<String>mStatus=new MutableLiveData<>();

    public MovieInfosViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public void setMovieTosee(long e){
        //pegar info
        if(e!=-1){
           getDataManager().getInfosByMovieId(e, Constants.LANGUAGE, new CallBackto<Movie>() {
               @Override
               public void isRefreshing(Boolean refreshing) {
                   if(refreshing){
                       mStatus.setValue("Aguarde...");
                   }
               }

               @Override
               public void onErro(String err, Throwable throwable) {
                   Err e=new Err(err,throwable);
                   Log.d("Pc","Err-->"+err);
                   MovieInfosViewModel.super.mErr.setValue(e);
               }

               @Override
               public void result(Movie result) {
                   Log.d("Pc","Title-->"+result.getTitle());
                   mMovie.setValue(result);
               }
           });
        }else{//erro na passagem de parametros

        }

    }

    public LiveData<Movie>getMovieInfos(){
        return mMovie;
    }
    public LiveData<String>getStatus(){return mStatus;}


}
