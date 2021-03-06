package com.pcdeveloper.darkmovies.di;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.pcdeveloper.darkmovies.data.DataManager;
import com.pcdeveloper.darkmovies.ui.fav.FavViewModel;
import com.pcdeveloper.darkmovies.ui.home.HomeViewModel;
import com.pcdeveloper.darkmovies.ui.infos.MovieInfosViewModel;
import com.pcdeveloper.darkmovies.ui.main.MainViewModel;
import com.pcdeveloper.darkmovies.ui.search.SearchViewModel;
import com.pcdeveloper.darkmovies.ui.splash.SplashViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private final DataManager dataManager;


    @Inject
    public ViewModelProviderFactory(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            return (T) new SplashViewModel(dataManager);
        } else if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(dataManager);
        } else if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(dataManager);
        } else if (modelClass.isAssignableFrom(MovieInfosViewModel.class)) {
            return (T) new MovieInfosViewModel(dataManager);
        } else if (modelClass.isAssignableFrom(FavViewModel.class)) {
            return (T) new FavViewModel(dataManager);
        } else if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) new SearchViewModel(dataManager);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}