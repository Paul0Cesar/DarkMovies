package com.pcdeveloper.darkmovies.ui.fav;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.pcdeveloper.darkmovies.BR;
import com.pcdeveloper.darkmovies.R;
import com.pcdeveloper.darkmovies.adapters.FavAdapter;
import com.pcdeveloper.darkmovies.data.models.Movie;
import com.pcdeveloper.darkmovies.databinding.FragmentFavoriteBinding;
import com.pcdeveloper.darkmovies.di.ViewModelProviderFactory;
import com.pcdeveloper.darkmovies.ui.base.BaseFragment;
import com.pcdeveloper.darkmovies.ui.infos.MovieInfosctivity;
import com.pcdeveloper.darkmovies.util.Constants;

import java.util.ArrayList;

import javax.inject.Inject;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends BaseFragment<FragmentFavoriteBinding,FavViewModel> {

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    FavAdapter mAdapter;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_favorite;
    }

    @Override
    public FavViewModel getViewModel() {
        FavViewModel favViewModel= ViewModelProviders.of(this,factory).get(FavViewModel.class);
        return favViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycler();
        initObservers();


    }

    @Override
    public void onResume() {
        super.onResume();
        getViewModel().initData();
    }

    private void initObservers() {
        getViewModel().getMovies().observe(this, new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                mAdapter.setMovieList(movies);
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

    private void initRecycler() {
        RecyclerView recyclerView=getDataBinding().recyclerFav;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getBaseContext(), LinearLayout.VERTICAL));
        mAdapter.onClickListener(getViewModel().onClick());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
    }
}
