package com.pcdeveloper.darkmovies.data.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.pcdeveloper.darkmovies.R;
import com.pcdeveloper.darkmovies.data.DataManager;
import com.pcdeveloper.darkmovies.data.db.DbHelper;
import com.pcdeveloper.darkmovies.data.models.Image;
import com.pcdeveloper.darkmovies.data.models.Movie;
import com.pcdeveloper.darkmovies.data.models.PageMovie;
import com.pcdeveloper.darkmovies.data.network.CallBack.CallBackto;

import java.io.ByteArrayOutputStream;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppApiHelper implements  ApiHelper {

    private Context mContext;
    private Retrofit mRetrofit;
    private String BASE_URL="http://api.themoviedb.org/3/";
    private String API_KEY="299944006d221f95281489351f1c19fa";
    private ApiService mService;
    private DbHelper dbHelper;

    //https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg
    //http://api.themoviedb.org/3/configuration?api_key=299944006d221f95281489351f1c19fa
    @Inject
    public AppApiHelper(Context mContext, DbHelper dbHelper) {
        this.mContext = mContext;
        this.dbHelper=dbHelper;
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
    public void getNowPlaying(String language, final int page, final CallBackto callBackto) {
        callBackto.isRefreshing(true);
        if(mService!=null){
            Call<PageMovie> call=this.mService.getNowPlaying(API_KEY,language,page);
            call.enqueue(new Callback<PageMovie>() {
                @Override
                public void onResponse(Call<PageMovie> call, Response<PageMovie> response) {
                    if(response.isSuccessful()){
                        PageMovie pageMovie=response.body();
                        if(pageMovie!=null && pageMovie.getMovies()!=null){
                            if(pageMovie.getMovies()!=null && pageMovie.getMovies().size()>0){
                                for(Movie e:pageMovie.getMovies()){
                                    Log.d("Pc","--->"+e.getTitle());
                                }
                            }else{
                                Log.d("Pc","--->VAZIO");
                            }
                            callBackto.isRefreshing(false);
                            callBackto.result(pageMovie);
                        }
                    }else{
                        callBackto.onErro("Erro na requisição dos filmes{1}!",null);
                    }
                }

                @Override
                public void onFailure(Call<PageMovie> call, Throwable t) {
                    callBackto.onErro("Erro na requisição dos filmes{2}!",t);
                }
            });
        }else{//show Erro

        }
    }


}
