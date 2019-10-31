package com.pcdeveloper.darkmovies.ui.infos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.pcdeveloper.darkmovies.BR;
import com.pcdeveloper.darkmovies.R;
import com.pcdeveloper.darkmovies.data.models.Movie;
import com.pcdeveloper.darkmovies.databinding.ActivityMovieInfosBinding;
import com.pcdeveloper.darkmovies.di.ViewModelProviderFactory;
import com.pcdeveloper.darkmovies.ui.base.BaseActivity;
import com.pcdeveloper.darkmovies.util.Constants;

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
        toolbar.setTitle("Title");
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initObservers() {
        getViewModel().getImage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s!=null){
                    Glide.with(getBaseContext())
                            .load(Constants.BASE_URL_IMG +s)
                            .placeholder(R.drawable.ic_refresh_24dp)
                            .error(R.drawable.ic_broken_image_24dp)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(getViewDataBinding().backdropImageview);
                }
            }
        });

    }

    private void getArgs() {//pegando informações passas para a view
        Intent i=getIntent();
        getViewModel().setMovieTosee(new Gson().fromJson(i.getStringExtra("movie"),Movie.class));
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
