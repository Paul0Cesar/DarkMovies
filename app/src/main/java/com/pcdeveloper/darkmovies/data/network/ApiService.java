package com.pcdeveloper.darkmovies.data.network;


import com.pcdeveloper.darkmovies.data.models.Movie;
import com.pcdeveloper.darkmovies.data.models.PageMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiService {

    @GET("movie/now_playing")
    Call<PageMovie>getNowPlaying(@Query("api_key") String api_key, @Query("language") String language,@Query("page") int page);

    @GET("movie/{movie_id}")
    Call<Movie>getInfosByMovie(@Path("movie_id") long movie_id,@Query("api_key") String api_key, @Query("language") String language);

}
