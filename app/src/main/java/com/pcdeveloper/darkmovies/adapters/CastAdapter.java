package com.pcdeveloper.darkmovies.adapters;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.pcdeveloper.darkmovies.R;
import com.pcdeveloper.darkmovies.data.models.Cast;
import com.pcdeveloper.darkmovies.databinding.AdapterCastBinding;

import java.util.ArrayList;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.MyViewHolder> {


    private ArrayList<Cast> casts = new ArrayList<>();

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterCastBinding adapterCastBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_cast, parent, false);
        return new CastAdapter.MyViewHolder(adapterCastBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (this.casts.get(position) != null) {
            holder.bind(this.casts.get(position));
        }

    }

    @Override
    public int getItemCount() {
        if (casts != null) {
            return casts.size();
        } else {
            return 0;
        }
    }

    public void setItens(ArrayList<Cast> casts) {
        this.casts = casts;
        notifyDataSetChanged();

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private AdapterCastBinding adapterCastBinding;

        public MyViewHolder(@NonNull AdapterCastBinding adapterCastBinding) {
            super(adapterCastBinding.getRoot());
            this.adapterCastBinding = adapterCastBinding;
        }

        public void bind(Cast cast) {
            this.adapterCastBinding.setItem(cast);
        }
    }
}
