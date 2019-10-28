package com.pcdeveloper.darkmovies.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pcdeveloper.darkmovies.R;
import com.pcdeveloper.darkmovies.data.DataManager;
import com.pcdeveloper.darkmovies.data.models.Image;
import com.pcdeveloper.darkmovies.data.models.Movie;
import com.pcdeveloper.darkmovies.databinding.AdapterMovieBinding;
import com.pcdeveloper.darkmovies.ui.main.MainViewModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
        Boolean b=mDataManager.createImageDao().findById(currentMovie.getId());//verifica se a image já existe no banco
        if(!b){//caso não existe pega as configurações para essa image e baixa da Api ,depois salva no banco
            Log.d("Pc","Não Baixado-->"+currentMovie.getTitle());
            Glide.with(mContext)
                    .load(mDataManager.getImageConfig()+currentMovie.getPosterPath())
                    .apply(new RequestOptions().placeholder(R.drawable.logo1))
                    .into( holder.getImageView());
            try{
                Bitmap bitmap = ((BitmapDrawable) holder.getImageView().getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageInByte = baos.toByteArray();
                baos.close();
                Image  x=new Image();
                x.setPoster(imageInByte);
                x.setId(currentMovie.getId());
                mDataManager.createImageDao().save(x);
            }catch (IOException  e){
                e.printStackTrace();
            }
        }else{//a imagem ja foi baixa em algum momento,devemos apenas carregar ela
            Log.d("Pc","Baixado-->"+currentMovie.getTitle());
            Image image=mDataManager.createImageDao().loadById(currentMovie.getId());
            if(image!=null && image.getPoster()!=null){
                Log.d("Pc","-->"+image.getId());
                Glide.with(mContext)
                        .load(image.getPoster())
                        .into( holder.getImageView());
            }else{
                Log.d("Pc","NULLL");
            }


        }
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
