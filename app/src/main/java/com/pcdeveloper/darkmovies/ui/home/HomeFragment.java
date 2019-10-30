package com.pcdeveloper.darkmovies.ui.home;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pcdeveloper.darkmovies.BR;
import com.pcdeveloper.darkmovies.R;
import com.pcdeveloper.darkmovies.adapters.MovieAdapter;
import com.pcdeveloper.darkmovies.data.models.PageMovie;
import com.pcdeveloper.darkmovies.databinding.FragmentHomeBinding;
import com.pcdeveloper.darkmovies.di.ViewModelProviderFactory;
import com.pcdeveloper.darkmovies.ui.base.BaseFragment;
import com.pcdeveloper.darkmovies.util.RecyclerViewClickListeners;

import javax.inject.Inject;


public class HomeFragment extends BaseFragment<FragmentHomeBinding,HomeViewModel> {



    @Inject
    ViewModelProviderFactory factory;

   @Inject
    MovieAdapter mMovieAdapter;

    @Inject
    RecyclerView.LayoutManager  layoutManager;

    private HomeViewModel homeViewModel;

    private RecyclerView mRecyclerView;



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
        observeMovies();

    }

    private void initRecyclerView() {
        mRecyclerView = getDataBinding().recyclerMovies;
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mMovieAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerViewClickListeners(getViewModel().getClick()));

    }

    private void observeMovies() {
        getViewModel().getMovies().observe(this, new Observer<PageMovie>() {
            @Override
            public void onChanged(PageMovie pageMovie) {
                mMovieAdapter.setMovieList(pageMovie.getMovies());
            }
        });

    }

}
