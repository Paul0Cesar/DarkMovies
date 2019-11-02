package com.pcdeveloper.darkmovies.data.prefs;

public interface PrefsHelper  {

    void addFavorites(long id);
    Boolean isFavorite(long id);
}
