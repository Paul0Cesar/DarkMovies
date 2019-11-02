package com.pcdeveloper.darkmovies.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

public class Prefs implements PrefsHelper {

    private Context mContext;
    private String KEY_FAVORYTES="KEY_FAVORYTES";
    private String PREF_FILE_NAME="DARK_MOVIES_PREFS";
    private final SharedPreferences mPrefs;

    @Inject
    public Prefs(Context mContext) {
        this.mContext = mContext;
        this.mPrefs=mContext.getSharedPreferences(PREF_FILE_NAME,Context.MODE_PRIVATE);
    }

    @Override
    public void addFavorites(long id) {
        SharedPreferences.Editor ed = mPrefs.edit();
        if(mPrefs.contains(KEY_FAVORYTES)){
            String fav=mPrefs.getString(KEY_FAVORYTES,null);
            if(!(fav.contains(id+""))){
                fav=fav+id+";";
                ed.putString(KEY_FAVORYTES, fav);
            }else{
                fav=fav.replace(id+";","");
                ed.putString(KEY_FAVORYTES, fav);
            }

        }else{
            String nova2=id+";";
            ed.putString(KEY_FAVORYTES, nova2);
        }
        ed.apply();

    }

    @Override
    public Boolean isFavorite(long id) {
        if(mPrefs.contains(KEY_FAVORYTES)){
            String fav=mPrefs.getString(KEY_FAVORYTES,null);
           if(fav!=null){
               if(!(fav.contains(id+""))){
                   return false;
               }else{
                   return true;
               }
           }else{
               return false;
           }
        }else{
            return false;
        }

    }
}
