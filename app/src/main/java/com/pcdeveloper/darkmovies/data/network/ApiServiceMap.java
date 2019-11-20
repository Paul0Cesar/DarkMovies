package com.pcdeveloper.darkmovies.data.network;

import com.pcdeveloper.darkmovies.data.models.PageMovie;
import com.pcdeveloper.darkmovies.data.network.webObjects.IsoMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServiceMap {


    @GET("alpha/{iso}")
    Call<IsoMap> convertISoMaṕ(@Path("iso")String iso);
}
