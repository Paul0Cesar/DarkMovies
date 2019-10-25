package com.pcdeveloper.darkmovies.ui.base;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import dagger.android.AndroidInjection;


public abstract class BaseActivity <T extends ViewDataBinding,V extends BaseViewModel> extends AppCompatActivity {


    private T mViewDataBinding;
    private V mViewModel;


    public abstract int getBindingVariable();
    //@LayoutRes-.Informa que o retorno é de um componente(nesse Caso a view)
    public abstract @LayoutRes int getLayoutId();
    public abstract V getViewModel();
    public T getViewDataBinding() {
        return mViewDataBinding;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        initDataBinding();
    }

    private void initDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        if(this.mViewModel==null){
            this.mViewModel=getViewModel();
        }
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }

}
