package com.example.xiaojie.news.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by xiaojie on 2017/6/26.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Toast mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        initData();
        initView();
        initListener();

    }

    protected abstract void initData();

    protected abstract void initView();

    public abstract int getLayoutResId();

    public void showToast(String msg){
        if(mToast == null){
            mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }
    public abstract void initListener();
}
