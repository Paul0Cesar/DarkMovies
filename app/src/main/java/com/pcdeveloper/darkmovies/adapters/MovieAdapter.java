package com.pcdeveloper.darkmovies.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.pcdeveloper.darkmovies.R;
import com.pcdeveloper.darkmovies.data.models.PageMovie;
import com.pcdeveloper.darkmovies.data.models.Poster;
import com.pcdeveloper.darkmovies.databinding.AdapterMovieBinding;

import java.util.ArrayList;

/*
Poderia ter usado o mesmo adapter para exibição dos favoritos e dos novos filmes,contudo o fiz separado

Como?No onCreateViewHolder trocar o layout que ira ser inflado dependendo da view
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {


    private ArrayList<Poster> movies;
    private onClickListenerAdapter onClick;


    public void onClickListener(onClickListenerAdapter onClickListenerAdapter) {
        this.onClick = onClickListenerAdapter;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterMovieBinding movieListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_movie, parent, false);
        return new MyViewHolder(movieListItemBinding);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (movies != null) {
            Poster currentPoster = movies.get(position);
            holder.onBind(currentPoster, onClick);
        }
    }

    @Override
    public int getItemCount() {
        if (movies != null) {
            return movies.size();
        } else {
            return 0;
        }
    }

    public void setMovieList(PageMovie page) {
        if (page != null) {
            this.movies = page.getPosters();
            notifyDataSetChanged();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private AdapterMovieBinding movieListItemBinding;

        MyViewHolder(@NonNull AdapterMovieBinding itemView) {
            super(itemView.getRoot());
            this.movieListItemBinding = itemView;
        }


        void onBind(final Poster poster, final onClickListenerAdapter click) {
            this.movieListItemBinding.setItem(poster);
            this.movieListItemBinding.cardViewAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (click != null) {
                        click.onClick(movies.get(getAdapterPosition()));
                    }

                }
            });
        }

    }


    public interface onClickListenerAdapter {
        void onClick(Poster e);
    }
}
