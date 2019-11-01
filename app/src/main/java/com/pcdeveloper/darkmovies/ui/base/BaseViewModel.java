package com.pcdeveloper.darkmovies.ui.base;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.pcdeveloper.darkmovies.data.DataManager;
import com.pcdeveloper.darkmovies.util.Err;

import javax.inject.Inject;

public abstract class BaseViewModel extends ViewModel {


    private DataManager dataManager;
    protected MutableLiveData<Err>mErr=new MutableLiveData<>();


    public BaseViewModel(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public LiveData<Err>getObserveErr(){
        return mErr;
    }


    public DataManager getDataManager() {
        return dataManager;
    }

}
