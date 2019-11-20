package com.pcdeveloper.darkmovies.ui.search;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.pcdeveloper.darkmovies.BR;
import com.pcdeveloper.darkmovies.R;
import com.pcdeveloper.darkmovies.adapters.MvAdapter;
import com.pcdeveloper.darkmovies.data.models.Movie;
import com.pcdeveloper.darkmovies.databinding.FragmentSearchBinding;
import com.pcdeveloper.darkmovies.di.ViewModelProviderFactory;
import com.pcdeveloper.darkmovies.ui.base.BaseFragment;
import com.pcdeveloper.darkmovies.ui.infos.MovieInfosctivity;
import com.pcdeveloper.darkmovies.util.Constants;
import com.pcdeveloper.darkmovies.util.Err;

import java.util.ArrayList;

import javax.inject.Inject;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment<FragmentSearchBinding, SearchViewModel> {


    @Inject
    ViewModelProviderFactory factory;

    @Inject
    MvAdapter mAdapter;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public SearchViewModel getViewModel() {
        return ViewModelProviders.of(this, factory).get(SearchViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initSearch();
        initRecycler();
        initObsv();

    }

    private void initRecycler() {
        RecyclerView recyclerView = getDataBinding().recyclerResults;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        mAdapter.onClickListener(getViewModel().onClick());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
    }

    private void initSearch() {
        SearchView searchView = getDataBinding().searchView;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                getViewModel().setFilter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private void initObsv() {

        getViewModel().getMovies().observe(this, new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                mAdapter.setMovieList(movies);
            }
        });

        getViewModel().getNavigator().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    if (s.equals(Constants.INFOS_MOVIE)) {
                        long mv = getViewModel().getMovieToSee();
                        if (mv != 0) {
                            Intent i = new Intent(getContext(), MovieInfosctivity.class);
                            i.putExtra("movie", mv);
                            startActivity(i);

                        }
                    }

                }
            }
        });

        getViewModel().getObserveErr().observe(this, new Observer<Err>() {
            @Override
            public void onChanged(Err err) {
                if (err.getErr() != null) {
                    Toast.makeText(getContext(), err.getErr(), Toast.LENGTH_SHORT).show();
                    if (err.getT().getMessage() != null) {
                        Toast.makeText(getContext(), err.getT().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}
