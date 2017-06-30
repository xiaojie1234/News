package com.example.xiaojie.news.ui.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import com.example.xiaojie.news.R;
import com.example.xiaojie.news.utils.AllUtils;

/**
 * Created by xiaojie on 2017/6/26.
 */

public class StartActivity extends BaseActivity {

    private static final int DELAY=1500;


    @Override
    protected void initData() {

    }


    @Override
    protected void initView() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(DELAY);
                AllUtils.skipActivity(StartActivity.this, GuideActivity.class);
                finish();
            }
        }).start();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_start;
    }

    @Override
    public void initListener() {

    }
}
