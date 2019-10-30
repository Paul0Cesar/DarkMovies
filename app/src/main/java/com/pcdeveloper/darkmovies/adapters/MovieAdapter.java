package com.pcdeveloper.darkmovies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.pcdeveloper.darkmovies.R;
import com.pcdeveloper.darkmovies.data.DataManager;
import com.pcdeveloper.darkmovies.data.models.Movie;
import com.pcdeveloper.darkmovies.databinding.AdapterMovieBinding;

import java.util.ArrayList;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {


    private ArrayList<Movie> movies;

    DataManager mDataManager;
    private Context mContext;

    public MovieAdapter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext=parent.getContext();
        AdapterMovieBinding movieListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_movie,parent,false);
        return new MyViewHolder(movieListItemBinding);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movie currentMovie = movies.get(position);
        holder.onBind(currentMovie);

        Glide.with(mContext)
                .load(mDataManager.getImageConfig()+currentMovie.getPosterPath())
                .placeholder(R.drawable.ic_refresh_24dp)
                .error(R.drawable.ic_broken_image_24dp)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into( holder.getImageView());
    }

    @Override
    public int getItemCount() {
       if(movies!=null){
           return  movies.size();
       }else{
           return 0;
       }
    }

    public void setMovieList(ArrayList<Movie> movies){
        /*
        if(  this.movies!=null &&  this.movies.size()>0){
            this.movies.addAll(movies);
        }else{
            this.movies=movies;
        }*/
        this.movies=movies;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private AdapterMovieBinding movieListItemBinding;

        public MyViewHolder(@NonNull AdapterMovieBinding itemView) {
            super(itemView.getRoot());
            this.movieListItemBinding=itemView;
        }

        public ImageView getImageView(){
            return this.movieListItemBinding.imageView;
        }

        public void onBind(Movie movie){
            this.movieListItemBinding.setItem(movie);
        }


    }
}
