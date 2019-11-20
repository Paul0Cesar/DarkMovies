package com.pcdeveloper.darkmovies.data.network;



import com.pcdeveloper.darkmovies.data.models.Movie;
import com.pcdeveloper.darkmovies.data.models.PageMovie;
import com.pcdeveloper.darkmovies.data.network.webObjects.CastResponse;
import com.pcdeveloper.darkmovies.data.network.webObjects.SearchResult;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiService {

    @GET("movie/now_playing")
    Call<PageMovie>getNowPlaying(@Query("api_key") String api_key, @Query("language") String language,@Query("page") int page);

    @GET("movie/{movie_id}")
    Call<Movie>getInfosByMovie(@Path("movie_id") long movie_id,@Query("api_key") String api_key, @Query("language") String language);

    @GET("movie/{movie_id}/credits")
    Call<CastResponse>getInfosCredits(@Path("movie_id") long movie_id, @Query("api_key") String api_key);

    @GET("search/movie")
    Call<SearchResult>searchMovie(@Query("api_key") String api_key, @Query("query") String key);


}
