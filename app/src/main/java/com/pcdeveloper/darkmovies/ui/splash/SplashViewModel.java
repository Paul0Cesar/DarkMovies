package com.pcdeveloper.darkmovies.ui.splash;

import android.util.Log;

import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModel;

import com.pcdeveloper.darkmovies.data.Movie;

public class SplashViewModel extends ViewModel {

    private Movie movie;



    public void click(){
        Log.d("Pc","CLICKKKKK");

    }

    public String setSub_name(String sub_name){
        movie.setSub_name(sub_name);



    }
}
