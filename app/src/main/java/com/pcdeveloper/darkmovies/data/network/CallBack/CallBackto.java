package com.pcdeveloper.darkmovies.data.network.CallBack;

import androidx.lifecycle.MutableLiveData;

import com.pcdeveloper.darkmovies.data.models.PageMovie;

public interface CallBackto<T> {

    void isRefreshing(Boolean refreshing);
    void onErro(String err,Throwable throwable);
    void result(T result);
}
