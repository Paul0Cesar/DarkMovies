package com.pcdeveloper.darkmovies.data.network;

import com.pcdeveloper.darkmovies.data.models.Cast;
import com.pcdeveloper.darkmovies.data.models.Countrie;
import com.pcdeveloper.darkmovies.data.models.Movie;
import com.pcdeveloper.darkmovies.data.models.PageMovie;
import com.pcdeveloper.darkmovies.data.network.CallBack.CallBackto;
import com.pcdeveloper.darkmovies.data.network.webObjects.IsoMap;

import java.util.ArrayList;

public interface ApiHelper {


    void  getNowPlaying(String language, int page,  CallBackto<PageMovie> callBackto);

    void getInfosByMovieId (long movie_id,String language, CallBackto<Movie> callBackto);

    void searchMovies(String key,CallBackto<ArrayList<Movie>> callBackto);

    void getCoordToMap(String iso, CallBackto<IsoMap> callBackto);
}
