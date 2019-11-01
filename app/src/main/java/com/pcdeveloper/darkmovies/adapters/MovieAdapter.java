package com.pcdeveloper.darkmovies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.pcdeveloper.darkmovies.R;
import com.pcdeveloper.darkmovies.data.models.PageMovie;
import com.pcdeveloper.darkmovies.data.models.Poster;
import com.pcdeveloper.darkmovies.databinding.AdapterMovieBinding;
import com.pcdeveloper.darkmovies.util.Constants;

import java.util.ArrayList;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {


    private ArrayList<Poster> movies;

    private Context mContext;
    private onClickListenerAdapter onClick;


    public void onClickListener(onClickListenerAdapter onClickListenerAdapter){
        this.onClick=onClickListenerAdapter;
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
        Poster currentPoster = movies.get(position);
        holder.onBind(currentPoster,onClick);
    }

    @Override
    public int getItemCount() {
       if(movies!=null){
           return  movies.size();
       }else{
           return 0;
       }
    }

    public void setMovieList(PageMovie page){
        if(page!=null){
            this.movies= page.getPosters();
            notifyDataSetChanged();
        }
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

        public void onBind(final Poster poster, final onClickListenerAdapter click){
            this.movieListItemBinding.setItem(poster);
            this.movieListItemBinding.cardViewAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(click!=null){
                        click.onClick(movies.get(getAdapterPosition()));
                    }

                }
            });
        }

    }


    public interface onClickListenerAdapter{
        void onClick(Poster e);
    }
}
