package com.pcdeveloper.darkmovies.adapters;

import android.content.Context;
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

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.MyViewHolder> {

    private ArrayList<Movie>mMovies;
    private Context mContext;
    private FavAdapter.onClickListenerAdapter onClick;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext=parent.getContext();
        AdapterFavBinding adapterFavBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_fav,parent,false);
        return new MyViewHolder(adapterFavBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if(mMovies!=null){
            Movie e=this.mMovies.get(position);
            holder.bind(e,onClick);
        }

    }

    public void setMovieList(ArrayList<Movie> movies){
        if(movies!=null){
            this.mMovies=movies;
        }
        notifyDataSetChanged();
    }

    public void onClickListener(FavAdapter.onClickListenerAdapter onClickListenerAdapter){
        this.onClick=onClickListenerAdapter;
    }

    @Override
    public int getItemCount() {
       if(mMovies!=null){
           return mMovies.size();
       }else{
           return 0;
       }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private AdapterFavBinding adapterFavBinding;
        public MyViewHolder(@NonNull AdapterFavBinding itemView) {
            super(itemView.getRoot());
            this.adapterFavBinding=itemView;
        }

        public void bind(Movie e, final FavAdapter.onClickListenerAdapter onClick){
            if(e!=null){
                adapterFavBinding.setItem(e);
                adapterFavBinding.linearFav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(onClick!=null){
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
