package com.pcdeveloper.darkmovies.util;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewClickListeners extends RecyclerView.OnScrollListener {

    private Click click;

    public RecyclerViewClickListeners(Click click) {
        this.click = click;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        GridLayoutManager grid = (GridLayoutManager) recyclerView.getLayoutManager();
        if (grid != null && grid.findLastCompletelyVisibleItemPosition() == recyclerView.getAdapter().getItemCount() - 1) {
            click.OnClick(true);
        } else {
            click.OnClick(false);
        }
    }


    public interface Click {
        void OnClick(Boolean t);
    }
}
