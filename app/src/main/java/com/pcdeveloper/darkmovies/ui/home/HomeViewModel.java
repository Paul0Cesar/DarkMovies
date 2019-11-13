package com.pcdeveloper.darkmovies.ui.home;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pcdeveloper.darkmovies.adapters.MovieAdapter;
import com.pcdeveloper.darkmovies.data.DataManager;
import com.pcdeveloper.darkmovies.data.models.Poster;
import com.pcdeveloper.darkmovies.data.models.PageMovie;
import com.pcdeveloper.darkmovies.data.network.CallBack.CallBackto;
import com.pcdeveloper.darkmovies.ui.base.BaseViewModel;
import com.pcdeveloper.darkmovies.util.Constants;
import com.pcdeveloper.darkmovies.util.Err;
import com.pcdeveloper.darkmovies.util.RecyclerViewClickListeners;

public class HomeViewModel extends BaseViewModel {

    private MutableLiveData<PageMovie> pageMovies = new MutableLiveData<>();
    private MutableLiveData<Boolean> isRefreshing = new MutableLiveData<>();
    private String LANGUAGE = Constants.LANGUAGE;
    private MutableLiveData<String> mNavigator = new MutableLiveData<>();
    private int flag = 0;
    private Poster toSee;
    private String INFOS_MOVIE = Constants.INFOS_MOVIE;
    //val hasError: MutableLiveData<Boolean> = MutableLiveData()


    public LiveData<Boolean> getIsRefreshing() {
        return isRefreshing;
    }

    public LiveData<PageMovie> getMovies() {
        return pageMovies;
    }

    LiveData<String> getNavigator() {
        return mNavigator;
    }

    long getMovieToSee() {//retorna o filme que vai ser exibido
        return toSee.getId();
    }


    public HomeViewModel(DataManager dataManager) {
        super(dataManager);
    }


    public void onRefreshList() {
        PageMovie pg = this.pageMovies.getValue();
        if (pg != null && pg.getPosters() != null) {
            pg.setPosters(null);
            this.pageMovies.setValue(pg);
        }
        getNowPlaying(LANGUAGE, 1);
    }

    void initList() {
        if (flag == 0) {
            getNowPlaying(LANGUAGE, 1);
            flag = 1;
        }
    }

    private void getNowPlaying(String language, final int page) {
        getDataManager().getNowPlaying(language, page, new CallBackto<PageMovie>() {
            @Override
            public void isRefreshing(final Boolean refreshing) {
                isRefreshing.postValue(refreshing);
            }

            @Override
            public void onErro(String err, Throwable throwable) {
                Err e = new Err(err, throwable);
                HomeViewModel.super.mErr.setValue(e);
            }

            @Override
            public void result(PageMovie result) {
                if (pageMovies != null) {
                    if (pageMovies.getValue() != null && pageMovies.getValue().getPosters() != null) {
                        PageMovie mPage = pageMovies.getValue();
                        mPage.setNumberOfPage(result.getNumberOfPage());
                        mPage.setAllMovies(result.getPosters());
                        pageMovies.postValue(mPage);
                    } else {
                        pageMovies.postValue(result);
                    }
                }

            }
        });
    }


    RecyclerViewClickListeners.Click getClick() {
        return new RecyclerViewClickListeners.Click() {
            @Override
            public void OnClick(Boolean t) {
                if (t) {
                    if (pageMovies != null && pageMovies.getValue() != null) {
                        int i = pageMovies.getValue().getNumberOfPage();
                        getNowPlaying(LANGUAGE, ++i);
                    }
                }
            }
        };

    }


    MovieAdapter.onClickListenerAdapter onClick() {
        return new MovieAdapter.onClickListenerAdapter() {
            @Override
            public void onClick(Poster e) {
                if (e != null) {
                    toSee = e;
                    mNavigator.postValue(INFOS_MOVIE);
                }
            }
        };
    }

}
