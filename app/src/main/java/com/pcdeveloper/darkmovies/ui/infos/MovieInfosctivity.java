package com.pcdeveloper.darkmovies.ui.infos;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.pcdeveloper.darkmovies.BR;
import com.pcdeveloper.darkmovies.R;
import com.pcdeveloper.darkmovies.data.models.Movie;
import com.pcdeveloper.darkmovies.data.models.Poster;
import com.pcdeveloper.darkmovies.databinding.ActivityMovieInfosBinding;
import com.pcdeveloper.darkmovies.di.ViewModelProviderFactory;
import com.pcdeveloper.darkmovies.ui.base.BaseActivity;
import com.pcdeveloper.darkmovies.util.Constants;
import com.pcdeveloper.darkmovies.util.Err;

import javax.inject.Inject;

public class MovieInfosctivity extends BaseActivity<ActivityMovieInfosBinding,MovieInfosViewModel> {

    @Inject
    ViewModelProviderFactory factory;

    private MovieInfosViewModel movieInfosViewModel;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_movie_infos;
    }

    @Override
    public MovieInfosViewModel getViewModel() {
        movieInfosViewModel= ViewModelProviders.of(this,factory).get(MovieInfosViewModel.class);
        return movieInfosViewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


         init();
         getArgs();
         initObservers();


    }

    private void init() {
        Toolbar toolbar=getViewDataBinding().toolbar;
       setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//activ arrow to back in toolbar
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initObservers() {
       getViewModel().getMovieInfos().observe(this, new Observer<Movie>() {
           @Override
           public void onChanged(Movie movie) {
               if(movie !=null){
                   getViewDataBinding().setMovie(movie);
                   if(movie.getBackdropPath()!=null){
                       Glide.with(getBaseContext())
                               .load(Constants.BASE_URL_IMG_M + movie.getBackdropPath())
                               //.placeholder(R.drawable.ic_refresh_24dp)
                               .error(R.drawable.ic_broken_image_24dp)
                               .diskCacheStrategy(DiskCacheStrategy.ALL)
                               .into(getViewDataBinding().backdropImageview);
                   }

                  /* if(movie.getPosterPath()!=null){
                       Glide.with(getBaseContext())
                               .load(Constants.BASE_URL_IMG_M + movie.getPosterPath())
                               //.placeholder(R.drawable.ic_refresh_24dp)
                               .error(R.drawable.ic_broken_image_24dp)
                               .diskCacheStrategy(DiskCacheStrategy.ALL)
                               .into(getViewDataBinding().imagePoster);

                   }*/
               }
           }
       });

       getViewModel().getStatus().observe(this, new Observer<String>() {
           @Override
           public void onChanged(String s) {
               if(s!=null && s.length()>0){
                   Toast.makeText(getBaseContext(),s,Toast.LENGTH_SHORT).show();
               }
           }
       });

       getViewModel().getObserveErr().observe(this, new Observer<Err>() {
           @Override
           public void onChanged(Err err) {
               if(err.getErr()!=null){
                   Toast.makeText(getBaseContext(),err.getErr(),Toast.LENGTH_SHORT).show();
                   Toast.makeText(getBaseContext(),err.getT().getMessage(),Toast.LENGTH_SHORT).show();
               }
           }
       });
    }

    private void getArgs() {//pegando informações passadas para a view
        Intent i=getIntent();
        getViewModel().setMovieTosee(i.getLongExtra("movie",-1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movie_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
