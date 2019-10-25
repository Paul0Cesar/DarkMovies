package com.pcdeveloper.darkmovies.di;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.pcdeveloper.darkmovies.data.DataManager;
import com.pcdeveloper.darkmovies.ui.splash.SplashViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

@SuppressWarnings("unchecked")



@Singleton
public class ViewModelProviderFactory implements ViewModelProvider.Factory {

    private final DataManager dataManager;


    @Inject
    public ViewModelProviderFactory(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            return (T) new SplashViewModel(dataManager);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}