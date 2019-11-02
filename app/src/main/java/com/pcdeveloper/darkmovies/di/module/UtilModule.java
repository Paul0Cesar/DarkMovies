package com.pcdeveloper.darkmovies.di.module;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pcdeveloper.darkmovies.adapters.CastAdapter;
import com.pcdeveloper.darkmovies.adapters.FavAdapter;
import com.pcdeveloper.darkmovies.adapters.MovieAdapter;
import com.pcdeveloper.darkmovies.data.AppDataManager;
import com.pcdeveloper.darkmovies.data.DataManager;
import com.pcdeveloper.darkmovies.ui.infos.MovieInfosctivity;
import com.pcdeveloper.darkmovies.ui.main.MainActivity;

import java.util.ArrayList;

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
    FavAdapter providesFavAdapter(){return new FavAdapter();}

    @Provides
    GridLayoutManager  provideGridLayout(MainActivity activity){
        return new GridLayoutManager(activity,3);
    }

    @Provides
    LinearLayoutManager provideLinearLayout(MovieInfosctivity activity){
        return new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
    }


}
