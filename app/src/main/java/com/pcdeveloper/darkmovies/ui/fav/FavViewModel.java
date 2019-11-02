package com.pcdeveloper.darkmovies.ui.fav;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pcdeveloper.darkmovies.adapters.MvAdapter;
import com.pcdeveloper.darkmovies.data.DataManager;
import com.pcdeveloper.darkmovies.data.models.Movie;
import com.pcdeveloper.darkmovies.ui.base.BaseViewModel;
import com.pcdeveloper.darkmovies.util.Constants;

import java.util.ArrayList;

public class FavViewModel extends BaseViewModel {


    private MutableLiveData<ArrayList<Movie>> mMovies=new MutableLiveData<>();
    private MutableLiveData<String> mNavigator=new MutableLiveData<>();
    private String INFOS_MOVIE=Constants.INFOS_MOVIE;
    private Movie toSee;


    public FavViewModel(DataManager dataManager) {
        super(dataManager);
    }


    public LiveData<ArrayList<Movie>>getMovies(){
        return mMovies;
    }

    public LiveData<String> getNavigator(){return  mNavigator;}



    public  void initData(){
        ArrayList<Movie>movies=getDataManager().getAlLMovies();
        if(movies!=null){
            mMovies.postValue(movies);
        }
    }

    public long getMovieToSee(){//retorna o filme que vai ser exibido
        return toSee.getId();
    }



    public MvAdapter.onClickListenerAdapter onClick(){
        return new MvAdapter.onClickListenerAdapter() {
            @Override
            public void onClick(Movie e) {
                if(e!=null){
                    toSee=e;
                    mNavigator.postValue(INFOS_MOVIE);
                }
            }
        };
    }


}
