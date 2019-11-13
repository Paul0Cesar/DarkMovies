package com.pcdeveloper.darkmovies.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.pcdeveloper.darkmovies.R;
import com.pcdeveloper.darkmovies.data.models.Movie;
import com.pcdeveloper.darkmovies.databinding.AdapterFavBinding;

import java.util.ArrayList;

public class MvAdapter extends RecyclerView.Adapter<MvAdapter.MyViewHolder> {

    private ArrayList<Movie> mMovies;
    private MvAdapter.onClickListenerAdapter onClick;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterFavBinding adapterFavBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_fav, parent, false);
        return new MyViewHolder(adapterFavBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (mMovies != null) {
            Movie e = this.mMovies.get(position);
            holder.bind(e, onClick);
        }

    }

    public void setMovieList(ArrayList<Movie> movies) {
        if (movies != null) {
            this.mMovies = movies;
        }
        notifyDataSetChanged();
    }

    public void onClickListener(MvAdapter.onClickListenerAdapter onClickListenerAdapter) {
        this.onClick = onClickListenerAdapter;
    }

    @Override
    public int getItemCount() {
        if (mMovies != null) {
            return mMovies.size();
        } else {
            return 0;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private AdapterFavBinding adapterFavBinding;

        MyViewHolder(@NonNull AdapterFavBinding itemView) {
            super(itemView.getRoot());
            this.adapterFavBinding = itemView;
        }

        void bind(Movie e, final MvAdapter.onClickListenerAdapter onClick) {
            if (e != null) {
                adapterFavBinding.setItem(e);
                adapterFavBinding.linearFav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onClick != null) {
                            onClick.onClick(mMovies.get(getAdapterPosition()));
                        }
                    }
                });
            }
        }
    }

    public interface onClickListenerAdapter {
        void onClick(Movie e);
    }
}
