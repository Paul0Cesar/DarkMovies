package com.pcdeveloper.darkmovies.ui.infos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.pcdeveloper.darkmovies.BR;
import com.pcdeveloper.darkmovies.R;
import com.pcdeveloper.darkmovies.databinding.ActivityMovieInfosBinding;
import com.pcdeveloper.darkmovies.di.ViewModelProviderFactory;
import com.pcdeveloper.darkmovies.ui.base.BaseActivity;

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
         Intent i=getIntent();
         Log.d("Pc",i.getExtras().getString("movie"));

    }
}
