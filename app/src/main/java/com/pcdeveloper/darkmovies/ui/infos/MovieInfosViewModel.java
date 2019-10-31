package com.pcdeveloper.darkmovies.ui.infos;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pcdeveloper.darkmovies.data.DataManager;
import com.pcdeveloper.darkmovies.data.models.Movie;
import com.pcdeveloper.darkmovies.ui.base.BaseViewModel;

public class MovieInfosViewModel extends BaseViewModel {

    private MutableLiveData<String>image=new MutableLiveData<>();
    private MutableLiveData<Movie> mMovie=new MutableLiveData<>();
    public MovieInfosViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public void setMovieTosee(Movie e){
        if(e!=null){
            image.setValue(e.getBackdropPath());
            mMovie.setValue(e);
        }

    }

    public LiveData<Movie>getMovieInfos(){
        return mMovie;
    }


    public LiveData<String> getImage(){
        return image;
    }
}
