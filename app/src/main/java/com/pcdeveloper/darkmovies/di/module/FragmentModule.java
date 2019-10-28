package com.pcdeveloper.darkmovies.di.module;

import com.pcdeveloper.darkmovies.ui.home.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public  abstract class FragmentModule {

    @ContributesAndroidInjector(modules = UtilModule.class)
    abstract HomeFragment provideHomeFragmentFactory();
}
