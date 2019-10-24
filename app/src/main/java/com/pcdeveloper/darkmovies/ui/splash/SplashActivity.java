package com.pcdeveloper.darkmovies.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ActivityOptions;
import android.os.Bundle;

import com.pcdeveloper.darkmovies.R;
import com.pcdeveloper.darkmovies.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySplashBinding activitySplashBinding= DataBindingUtil.setContentView(this,R.layout.activity_splash);
        activitySplashBinding.setViewModel(new SplashViewModel());
        activitySplashBinding.executePendingBindings();
        //setContentView(R.layout.activity_splash);
    }
}
