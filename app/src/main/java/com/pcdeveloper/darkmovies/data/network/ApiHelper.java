package com.pcdeveloper.darkmovies.data.network;

import com.pcdeveloper.darkmovies.data.models.Movie;
import com.pcdeveloper.darkmovies.data.models.PageMovie;
import com.pcdeveloper.darkmovies.data.network.CallBack.CallBackto;

public interface ApiHelper {


    void  getNowPlaying(String language, int page,  CallBackto<PageMovie> callBackto);

    void getInfosByMovieId (long movie_id,String language, CallBackto<Movie> callBackto);
}
