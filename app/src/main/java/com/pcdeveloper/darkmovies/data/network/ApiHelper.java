package com.pcdeveloper.darkmovies.data.network;

import androidx.lifecycle.MutableLiveData;

import com.pcdeveloper.darkmovies.data.models.Movie;
import com.pcdeveloper.darkmovies.data.models.PageMovie;
import com.pcdeveloper.darkmovies.data.network.CallBack.CallBackto;

public interface ApiHelper {

    //// language pt-BR ou en.
    void  getNowPlaying(String language, int page, CallBackto callBackto);
}
