package com.pcdeveloper.darkmovies.di.module;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pcdeveloper.darkmovies.adapters.MovieAdapter;
import com.pcdeveloper.darkmovies.data.AppDataManager;
import com.pcdeveloper.darkmovies.data.DataManager;
import com.pcdeveloper.darkmovies.ui.main.MainActivity;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilModule {


    @Provides
    MovieAdapter proviceMovieAdapter(AppDataManager dataManager){
        return new MovieAdapter(dataManager);
    }

    @Provides
    RecyclerView.LayoutManager  provideGridLayout(MainActivity activity){
        return new GridLayoutManager(activity,3);
    }


}
