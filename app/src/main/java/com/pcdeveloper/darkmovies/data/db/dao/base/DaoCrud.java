package com.pcdeveloper.darkmovies.data.db.dao.base;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public interface DaoCrud<T>{

    void save(T tosave );
    void save(List<T> tosaveArray);
    T loadById(int id);
    Boolean findById(int id);
    void remove(@NonNull T object);
    int  count();

}
