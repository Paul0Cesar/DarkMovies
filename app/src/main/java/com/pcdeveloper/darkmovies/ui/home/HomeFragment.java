package com.pcdeveloper.darkmovies.ui.home;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import com.pcdeveloper.darkmovies.BR;
import com.pcdeveloper.darkmovies.R;
import com.pcdeveloper.darkmovies.adapters.MovieAdapter;
import com.pcdeveloper.darkmovies.data.models.PageMovie;
import com.pcdeveloper.darkmovies.databinding.FragmentHomeBinding;
import com.pcdeveloper.darkmovies.di.ViewModelProviderFactory;
import com.pcdeveloper.darkmovies.ui.base.BaseFragment;
import com.pcdeveloper.darkmovies.ui.infos.MovieInfosctivity;
import com.pcdeveloper.darkmovies.util.Constants;
import com.pcdeveloper.darkmovies.util.RecyclerViewClickListeners;

import javax.inject.Inject;


public class HomeFragment extends BaseFragment<FragmentHomeBinding,HomeViewModel> {



    @Inject
    ViewModelProviderFactory factory;

   @Inject
    MovieAdapter mMovieAdapter;

    @Inject
    GridLayoutManager layoutManager;

    private HomeViewModel homeViewModel;

    private RecyclerView mRecyclerView;

    private String INFOS_MOVIE= Constants.INFOS_MOVIE;



    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public HomeViewModel getViewModel() {
        homeViewModel= ViewModelProviders.of(this,factory).get(HomeViewModel.class);
        return homeViewModel;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewModel().initList();
        initRecyclerView();
        setObservers();

    }

    private void initRecyclerView() {
        mRecyclerView = getDataBinding().recyclerMovies;
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mMovieAdapter.onClickListener(getViewModel().onClick());
        mRecyclerView.setAdapter(mMovieAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerViewClickListeners(getViewModel().getClick()));

    }

    private void setObservers() {

        getViewModel().getMovies().observe(this, new Observer<PageMovie>() {
            @Override
            public void onChanged(PageMovie pageMovie) {
                mMovieAdapter.setMovieList(pageMovie);
            }
        });

        getViewModel().getNavigator().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s!=null){
                   if(s.equals(Constants.INFOS_MOVIE)){
                       long mv=getViewModel().getMovieToSee();
                       if(mv!=0){
                           Intent i=new Intent(getContext(), MovieInfosctivity.class);
                           i.putExtra("movie",mv);
                           startActivity(i);
                       }
                   }

                }
            }
        });

    }

}
