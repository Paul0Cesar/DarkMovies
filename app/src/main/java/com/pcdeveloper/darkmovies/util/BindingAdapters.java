package com.pcdeveloper.darkmovies.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.pcdeveloper.darkmovies.R;
import com.pcdeveloper.darkmovies.adapters.CastAdapter;
import com.pcdeveloper.darkmovies.adapters.MvAdapter;
import com.pcdeveloper.darkmovies.adapters.MovieAdapter;
import com.pcdeveloper.darkmovies.data.models.Cast;
import com.pcdeveloper.darkmovies.data.models.Genres;
import com.pcdeveloper.darkmovies.data.models.Movie;
import com.pcdeveloper.darkmovies.data.models.PageMovie;

import java.util.ArrayList;
/*
Classe responsavel pela re-escrita de metodos expecificificos da view

Paulo-->Ainda estou meio confuso quanto ao uso dos bindAdapters,contudo
a facilidades deles é imensa,
antes esse codigo ficaria todo concentrado na view
agora esta todo aqui e pode ser reaproveitado .
 */


public class BindingAdapters {




    @BindingAdapter("adapter")
    public  static void AddMoviesItens(RecyclerView view, PageMovie pageMovie){
        if(view!=null){
            if(pageMovie!=null){
                MovieAdapter adapter= (MovieAdapter) view.getAdapter();
                if(adapter!=null){
                    adapter.setMovieList(pageMovie);
                }
            }
        }

    }

    @BindingAdapter("adapterFav")
    public  static void AddMoviesItensFav(RecyclerView view, ArrayList<Movie> movies){
        if(view!=null){
            if(movies!=null){
                MvAdapter adapter= (MvAdapter) view.getAdapter();
                if(adapter!=null){
                    adapter.setMovieList(movies);
                }
            }
        }

    }

    @BindingAdapter("adapterMvInfos")
    public  static void AddMoviesItens(RecyclerView view,ArrayList<Cast> e){
        if(view!=null){
            if(e!=null){
                CastAdapter adapter= (CastAdapter) view.getAdapter();
                adapter.setItens(e);
            }
        }

    }





    @BindingAdapter({"imageUrl", "isBackdrop"})//Responsavel pelo carregamento das imagens
    public static void bindImage(ImageView imageView, String imagePath, boolean isBackdrop) {
        String baseUrl;
        if (isBackdrop) {
            baseUrl = Constants.BASE_URL_IMG_M;
        } else {
            baseUrl = Constants.BASE_URL_IMG_P;
        }
       if(imagePath!=null){
           Glide.with(imageView.getContext())
                   .load(baseUrl+ imagePath)
                   //.placeholder(R.drawable.ic_refresh_24dp)
                   .error(R.drawable.ic_broken_image_24dp)
                   .diskCacheStrategy(DiskCacheStrategy.ALL)
                   .into(imageView);
       }


    }

    @BindingAdapter("imageUrlCircle")//Responsavel pelo carregamento das imagens
    public static void bindImage(ImageView imageView, String imagePath) {
        //baseUrl = Constants.BASE_URL_IMG_M;
        if(imagePath!=null){
            Glide.with(imageView.getContext())
                    .load(Constants.BASE_URL_IMG_P+ imagePath)
                    //.placeholder(R.drawable.ic_refresh_24dp)
                    .error(R.drawable.ic_broken_image_24dp)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }


    }



    @BindingAdapter("visibleGone")//tira a visibilidade da view
    public static void showHide(View view, Boolean show) {
        if (show) {
            view.setVisibility(View.VISIBLE);
        }else{
            view.setVisibility(View.GONE);
        }
    }


    @BindingAdapter("itemsChip")//Responsavel por criar chips para o chip group
    public static void setItems(ChipGroup view, ArrayList<Genres> genres) {
        if (genres == null || view.getChildCount() > 0)
            return;

        Context context = view.getContext();
        for (Genres genre : genres) {
            Chip chip = new Chip(context);
            chip.setText(genre.getName());
            chip.setChipStrokeWidth(0.8f);
            chip.setChipStrokeColor(ColorStateList.valueOf(
                    context.getResources().getColor(R.color.colorAccent)));
            chip.setChipBackgroundColorResource(android.R.color.transparent);
            view.addView(chip);
        }
    }
}
