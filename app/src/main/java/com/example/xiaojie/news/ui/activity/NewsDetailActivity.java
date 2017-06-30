package com.example.xiaojie.news.ui.activity;

import android.databinding.DataBindingUtil;
import android.text.AndroidCharacter;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.xiaojie.news.R;
import com.example.xiaojie.news.bean.News;
import com.example.xiaojie.news.databinding.ActivityNewsdetailBinding;

/**
 * Created by xiaojie on 2017/6/29.
 */

public class NewsDetailActivity extends BaseActivity {

    private ActivityNewsdetailBinding newsdetailBinding;
    private ProgressBar mProgressBar;
    private WebView mWebView;

    @Override
    protected void initData() {
        newsdetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_newsdetail);
        if(getIntent().getSerializableExtra("new") != null){

        }else {
            News.ResultBean bean = (News.ResultBean) getIntent().getSerializableExtra("news");
            String url = bean.getUrl();
            newsdetailBinding.webView.loadUrl(url);
        }


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()){
            mWebView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mProgressBar = newsdetailBinding.progressBar;
        mProgressBar.setMax(100);
        initWebView();
    }

    private void initWebView() {
        mWebView = newsdetailBinding.webView;
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressBar.setVisibility(View.GONE);
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setProgress(newProgress);

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_newsdetail;
    }

    @Override
    public void initListener() {

    }
}
