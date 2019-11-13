package com.pcdeveloper.darkmovies.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel> extends Fragment {

    private BaseActivity mActivity;
    private View mView;
    private T mDataBinding;
    private V mViewModel;

    public abstract int getBindingVariable();

    public abstract @LayoutRes
    int getLayoutId();

    public abstract V getViewModel();


    public BaseActivity getBaseActiviy() {
        return mActivity;
    }

    protected T getDataBinding() {
        return mDataBinding;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDataBinding.setVariable(getBindingVariable(), mViewModel);
        mDataBinding.setLifecycleOwner(this);
        mDataBinding.executePendingBindings();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        mView = mDataBinding.getRoot();
        return mView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        mViewModel = getViewModel();
        setHasOptionsMenu(false);//Atualiza para o novo menu yes/no
    }
}
