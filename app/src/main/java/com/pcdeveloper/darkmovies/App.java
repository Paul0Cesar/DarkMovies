package com.pcdeveloper.darkmovies;

import android.app.Activity;
import android.app.Application;

import com.pcdeveloper.darkmovies.data.DataManager;
import com.pcdeveloper.darkmovies.di.component.DaggerApplicationComponent;

import javax.inject.Inject;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class App extends Application implements HasActivityInjector {


    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    DataManager mDataManager;


    @Override
    public void onCreate() {
        super.onCreate();

        DaggerApplicationComponent.builder()
                .application(this)
                .build()
                .inject(this);

        mDataManager.openDb();

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public void onTerminate() {
        mDataManager.closeDb();
        super.onTerminate();
    }
}
