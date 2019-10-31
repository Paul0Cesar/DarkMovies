package com.pcdeveloper.darkmovies.data;

import com.pcdeveloper.darkmovies.data.db.DbHelper;
import com.pcdeveloper.darkmovies.data.network.ApiHelper;
import com.pcdeveloper.darkmovies.data.prefs.PrefsHelper;

public interface DataManager extends DbHelper, PrefsHelper, ApiHelper {


}
