package com.pcdeveloper.darkmovies.ui.base;

import androidx.lifecycle.ViewModel;
import com.pcdeveloper.darkmovies.data.DataManager;
import javax.inject.Inject;

public abstract class BaseViewModel extends ViewModel {

    private DataManager dataManager;


    public BaseViewModel(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

}
