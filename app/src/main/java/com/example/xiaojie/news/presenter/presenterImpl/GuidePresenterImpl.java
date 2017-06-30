package com.example.xiaojie.news.presenter.presenterImpl;

import android.view.View;

import com.example.xiaojie.news.presenter.GuidePresenter;
import com.example.xiaojie.news.view.GuideView;

/**
 * Created by xiaojie on 2017/6/26.
 */

public class GuidePresenterImpl implements GuidePresenter{

    private GuideView mGuideView;

    public GuidePresenterImpl(GuideView v){
        mGuideView = v;
    }


}
