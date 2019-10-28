package com.pcdeveloper.darkmovies.ui.home;

import android.util.Log;

import androidx.databinding.Bindable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pcdeveloper.darkmovies.data.DataManager;
import com.pcdeveloper.darkmovies.data.models.Movie;
import com.pcdeveloper.darkmovies.data.models.PageMovie;
import com.pcdeveloper.darkmovies.data.network.CallBack.CallBackto;
import com.pcdeveloper.darkmovies.ui.base.BaseViewModel;
import com.pcdeveloper.darkmovies.util.RecyclerViewClickListeners;

public class HomeViewModel extends BaseViewModel {

   private MutableLiveData<PageMovie> pageMovies=new MutableLiveData<>();
   private MutableLiveData<Boolean> isRefreshing=new MutableLiveData<>();
   private String LANGUAGE="pt-BR";
    //val hasError: MutableLiveData<Boolean> = MutableLiveData()




    public LiveData<Boolean> getIsRefreshing() {
        return isRefreshing;
    }


    public  LiveData<PageMovie> getMovies(){
        return pageMovies;
    }




    public HomeViewModel(DataManager dataManager) {
        super(dataManager);
    }


    public void  onRefreshList(){
        PageMovie pg=this.pageMovies.getValue();
        if(pg!=null && pg.getMovies()!=null){
            pg.setMovies(null);
            this.pageMovies.setValue(pg);
        }
        getNowPlaying(LANGUAGE,1);
    }

    private void getNowPlaying(String language, final int page){
        getDataManager().getNowPlaying(language,page, new CallBackto() {
            @Override
            public void isRefreshing(final Boolean refreshing) {
                if(!refreshing){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(600);
                                isRefreshing.postValue(refreshing);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }else{
                    isRefreshing.postValue(refreshing);
                }

            }

            @Override
            public void onErro(String err, Throwable throwable) {
                //mostrar erro
            }

            @Override
            public void result(PageMovie result) {
                if(pageMovies!=null && pageMovies.getValue()!=null && pageMovies.getValue().getMovies()!=null){
                    PageMovie mPage=pageMovies.getValue();
                    mPage.setNumberOfPage(result.getNumberOfPage());
                    mPage.setAllMovies(result.getMovies());
                    pageMovies.postValue(mPage);
                }else{
                    pageMovies.postValue(result);
                }

            }
        });
    }


    public RecyclerViewClickListeners.Click getClick(){
        return new RecyclerViewClickListeners.Click() {
            @Override
            public void OnClick(Boolean t) {
               if(t){
                   if(pageMovies!=null && pageMovies.getValue()!=null){
                       int i=pageMovies.getValue().getNumberOfPage();
                       getNowPlaying(LANGUAGE,++i);
                   }
               }
            }
        };

    }

    public void onClick(Movie e){
        Log.d("Pc","CHEGOU AQUIII!!!");
    }

}
