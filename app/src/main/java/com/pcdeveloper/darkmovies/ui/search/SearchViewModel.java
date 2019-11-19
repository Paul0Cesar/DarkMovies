package com.pcdeveloper.darkmovies.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pcdeveloper.darkmovies.adapters.MvAdapter;
import com.pcdeveloper.darkmovies.data.DataManager;
import com.pcdeveloper.darkmovies.data.models.Movie;
import com.pcdeveloper.darkmovies.data.network.CallBack.CallBackto;
import com.pcdeveloper.darkmovies.ui.base.BaseViewModel;
import com.pcdeveloper.darkmovies.util.Constants;
import com.pcdeveloper.darkmovies.util.Err;
import java.util.ArrayList;

public class SearchViewModel extends BaseViewModel {

    private MutableLiveData<ArrayList<Movie>> mMovies = new MutableLiveData<>();
    private MutableLiveData<String> mNavigator = new MutableLiveData<>();
    private String INFOS_MOVIE = Constants.INFOS_MOVIE;
    private Movie toSee;

    public SearchViewModel(DataManager dataManager) {
        super(dataManager);
    }

    void setFilter(String filter) {

        getDataManager().searchMovies(filter, new CallBackto<ArrayList<Movie>>() {
            @Override
            public void isRefreshing(Boolean refreshing) {
                //nao utilizado no momento
            }

            @Override
            public void onErro(String err, Throwable throwable) {
                Err e = new Err(err, throwable);
                SearchViewModel.super.mErr.setValue(e);
            }

            @Override
            public void result(ArrayList<Movie> result) {
                mMovies.postValue(result);
            }
        });

    }

    long getMovieToSee() {//retorna o filme que vai ser exibido
        return toSee.getId();
    }

    LiveData<ArrayList<Movie>> getMovies() {
        return mMovies;
    }

    LiveData<String> getNavigator() {
        return mNavigator;
    }

    MvAdapter.onClickListenerAdapter onClick() {
        return new MvAdapter.onClickListenerAdapter() {
            @Override
            public void onClick(Movie e) {
                if (e != null) {
                    toSee = e;
                    mNavigator.postValue(INFOS_MOVIE);
                }
            }
        };
    }
}
