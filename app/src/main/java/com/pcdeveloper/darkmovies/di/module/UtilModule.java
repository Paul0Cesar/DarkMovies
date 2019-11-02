package com.pcdeveloper.darkmovies.di.module;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.pcdeveloper.darkmovies.adapters.CastAdapter;
import com.pcdeveloper.darkmovies.adapters.MvAdapter;
import com.pcdeveloper.darkmovies.adapters.MovieAdapter;
import com.pcdeveloper.darkmovies.ui.infos.MovieInfosctivity;
import com.pcdeveloper.darkmovies.ui.main.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilModule {


    @Provides
    MovieAdapter provideMovieAdapter(){
        return new MovieAdapter();
    }

    @Provides
    CastAdapter provideCastAdapter(){
        return new CastAdapter();
    }

    @Provides
    MvAdapter providesFavAdapter(){return new MvAdapter();}

    @Provides
    GridLayoutManager  provideGridLayout(MainActivity activity){
        return new GridLayoutManager(activity,3);
    }

    @Provides
    LinearLayoutManager provideLinearLayout(MovieInfosctivity activity){
        return new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
    }


}
