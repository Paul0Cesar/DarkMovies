package com.pcdeveloper.darkmovies.di.module;

import com.pcdeveloper.darkmovies.ui.fav.FavoriteFragment;
import com.pcdeveloper.darkmovies.ui.home.HomeFragment;
import com.pcdeveloper.darkmovies.ui.search.SearchFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public  abstract class FragmentModule {

    @ContributesAndroidInjector(modules = UtilModule.class)
    abstract HomeFragment provideHomeFragmentFactory();

    @ContributesAndroidInjector(modules = UtilModule.class)
    abstract SearchFragment provideSearchFragmentFactory();

    @ContributesAndroidInjector(modules = UtilModule.class)
    abstract FavoriteFragment provideFavoriteFragmentFactory();
}
