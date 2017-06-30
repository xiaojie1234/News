package com.example.xiaojie.news.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.zip.Inflater;

/**
 * Created by xiaojie on 2017/6/27.
 */

public abstract class BaseFragment extends Fragment{

    private View mRootView;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRootView == null){
            mRootView = LayoutInflater.from(getContext()).inflate(getLayoutRes(),container,false);
            initData(mRootView);
            initData();
            initView();
            initListener();
        }

        return mRootView;
    }

    protected abstract void initData(View root);

    protected abstract int getLayoutRes();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initListener();
}
