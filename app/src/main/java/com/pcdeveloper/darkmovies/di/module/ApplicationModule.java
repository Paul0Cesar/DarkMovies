package com.pcdeveloper.darkmovies.di.module;

import android.app.Application;
import android.content.Context;


import com.pcdeveloper.darkmovies.data.AppDataManager;
import com.pcdeveloper.darkmovies.data.DataManager;
import com.pcdeveloper.darkmovies.data.db.DbHelper;
import com.pcdeveloper.darkmovies.data.db.DbOpenHelper;
import com.pcdeveloper.darkmovies.data.network.ApiHelper;
import com.pcdeveloper.darkmovies.data.network.AppApiHelper;
import com.pcdeveloper.darkmovies.data.prefs.Prefs;
import com.pcdeveloper.darkmovies.data.prefs.PrefsHelper;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {


    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(DbOpenHelper appDbHelper){
        return appDbHelper;
    }

    @Provides
    @Singleton
    PrefsHelper provideAppPreferences(Prefs appPreferences){
        return appPreferences;
    }


    @Provides
    @Singleton
    ApiHelper provideAppApi(AppApiHelper appApiHelper){
        return appApiHelper;
    }









}
