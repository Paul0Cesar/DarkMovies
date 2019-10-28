package com.pcdeveloper.darkmovies.ui.splash;




import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.pcdeveloper.darkmovies.BR;
import com.pcdeveloper.darkmovies.R;
import androidx.lifecycle.ViewModelProviders;
import com.pcdeveloper.darkmovies.databinding.ActivitySplashBinding;
import com.pcdeveloper.darkmovies.di.ViewModelProviderFactory;
import com.pcdeveloper.darkmovies.ui.base.BaseActivity;
import com.pcdeveloper.darkmovies.ui.main.MainActivity;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel>{

    @Inject
    ViewModelProviderFactory factory;

     SplashViewModel mSplashViewModel;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashViewModel getViewModel() {
        mSplashViewModel = ViewModelProviders.of(this,factory).get(SplashViewModel.class);
        return mSplashViewModel;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                openMainActivity();
            }
        },2000);

    }

    public void openMainActivity(){
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
