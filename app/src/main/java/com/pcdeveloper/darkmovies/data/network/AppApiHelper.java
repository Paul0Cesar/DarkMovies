package com.pcdeveloper.darkmovies.data.network;

import android.content.Context;
import android.util.Log;

import com.pcdeveloper.darkmovies.data.db.DbHelper;
import com.pcdeveloper.darkmovies.data.models.Movie;
import com.pcdeveloper.darkmovies.data.models.Poster;
import com.pcdeveloper.darkmovies.data.models.PageMovie;
import com.pcdeveloper.darkmovies.data.network.CallBack.CallBackto;
import com.pcdeveloper.darkmovies.util.Constants;
import com.pcdeveloper.darkmovies.util.Methods;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class AppApiHelper implements  ApiHelper {

    private Context mContext;
    private Retrofit mRetrofit;
    private String BASE_URL= Constants.BASE_URL;
    private String API_KEY=Constants.API_KEY;
    private ApiService mService;


    @Inject
    public AppApiHelper(Context mContext) {
        this.mContext = mContext;
        startConnection();
    }

    private void startConnection(){
        if(this.mRetrofit==null){//devemos criar um objeto de conexão
            this.mRetrofit  = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        this.mService=this.mRetrofit.create(ApiService.class);
    }


    @Override
    public void getNowPlaying(String language, final int page, final CallBackto<PageMovie> callBackto) {
        callBackto.isRefreshing(true);
        if(mService!=null){
            Call<PageMovie> call=this.mService.getNowPlaying(API_KEY,language,page);
            call.enqueue(new Callback<PageMovie>() {
                @Override
                public void onResponse(Call<PageMovie> call, Response<PageMovie> response) {
                    if(response.isSuccessful()){
                        PageMovie pageMovie=response.body();
                        if(pageMovie!=null && pageMovie.getPosters()!=null){
                            if(pageMovie.getPosters()!=null && pageMovie.getPosters().size()>0){
                                callBackto.result(pageMovie);
                            }else{
                                callBackto.onErro(Methods.err("getNowPlaying","Erro na Requsição dos Filmes",-1),null);
                            }
                            callBackto.isRefreshing(false);
                        }
                    }else{
                        callBackto.onErro(Methods.err("getNowPlaying","Erro na Requsição dos Filmes",response.code()),null);
                        callBackto.isRefreshing(false);
                    }
                }

                @Override
                public void onFailure(Call<PageMovie> call, Throwable t) {
                    callBackto.onErro(Methods.err("getNowPlaying","Erro na Requsição dos Filmes",t.hashCode()),t);
                    callBackto.isRefreshing(false);
                }
            });
        }else{//show Erro
            callBackto.onErro(Methods.err("getNowPlaying","Erro no Service {Vazio}",-1),null);
            callBackto.isRefreshing(false);
        }
    }

    @Override
    public void getInfosByMovieId(long movie_id, String language,final CallBackto<Movie>  callBackto) {
        callBackto.isRefreshing(true);
        if(mService!=null){
            Call<Movie>call=mService.getInfosByMovie(movie_id,API_KEY,language);
            call.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    if(response.isSuccessful()){
                        callBackto.result(response.body());
                    }else{

                    callBackto.onErro(Methods.err("getInfosByMovieId","Erro na Requsição do Filme",-1),null);
                    }
                    callBackto.isRefreshing(false);
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {
                    callBackto.onErro(Methods.err("getInfosByMovieId","Erro na Requsição do Filme",t.hashCode()),t);

                    callBackto.isRefreshing(false);

                }
            });
        }else{
            callBackto.onErro(Methods.err("getInfosByMovieId","Erro no Service {Vazio}",-1),null);
            callBackto.isRefreshing(false);
        }

    }


}
