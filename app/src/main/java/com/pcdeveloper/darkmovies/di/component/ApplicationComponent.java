package com.pcdeveloper.darkmovies.di.component;


import android.app.Application;
import android.content.Context;

import com.pcdeveloper.darkmovies.App;
import com.pcdeveloper.darkmovies.di.builder.ActivityBuilder;
import com.pcdeveloper.darkmovies.di.module.ApplicationModule;
import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class,ApplicationModule.class, ActivityBuilder.class})
public interface ApplicationComponent {


    void inject(App app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }


}
