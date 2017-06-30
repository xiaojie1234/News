package com.example.xiaojie.news.ui.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.xiaojie.news.R;
import com.example.xiaojie.news.adapter.NewsAdapter;
import com.example.xiaojie.news.bean.News;
import com.example.xiaojie.news.databinding.FragmentNewsItemBinding;
import com.example.xiaojie.news.databinding.NewsListHeaderBinding;
import com.example.xiaojie.news.ui.activity.NewsDetailActivity;
import com.example.xiaojie.news.utils.AllUtils;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiaojie on 2017/6/27.
 */

public class NewsItemFragment extends BaseFragment {

    private String id;
    private FragmentNewsItemBinding binding;
    private NewsListHeaderBinding headerBinding;
    private LayoutInflater inflater;
    private ListView listView;
    private SpringView mSpringView;
    private int mDefaultPage;
    private int page;
    private NewsAdapter adapter;
    private List<News.ResultBean> resultBeen;

    @Override
    protected void initData(View root) {

        binding = DataBindingUtil.bind(root);
        inflater = LayoutInflater.from(getContext());
        listView = binding.listView;
        mDefaultPage=1;
        page=1;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_news_item;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    protected void initData() {
        RequestNewsData();

    }

    private void RequestNewsData() {
        String newsUrl = AllUtils.getUrl(id,mDefaultPage);
        requestDataWithXUtils(newsUrl);

    }

    private void requestDataWithXUtils(String newsUrl) {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, newsUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String json = responseInfo.result;
                json = json.replace(id,"result");
                Gson gson = new Gson();
                News news = gson.fromJson(json,News.class);
                resultBeen = news.getResult();

                showData(resultBeen);

            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });
    }


    private void showData(List<News.ResultBean> resultBeen) {
        News.ResultBean firstBean = resultBeen.get(0);
        if(firstBean.getAds() != null && firstBean.getAds().size() > 0){
            resultBeen.remove(0);
            headerBinding = DataBindingUtil.inflate(inflater,R.layout.news_list_header,listView,false);
            List<News.ResultBean.AdsBean> adsBeen = firstBean.getAds();
            for(int i=0;i<adsBeen.size();i++){
                final News.ResultBean.AdsBean bean = adsBeen.get(i);
                TextSliderView textSliderView = new TextSliderView(getContext());
                textSliderView.description(bean.getTitle())
                        .image(bean.getImgsrc());
                headerBinding.slider.addSlider(textSliderView);
            }
            listView.addHeaderView(headerBinding.getRoot());
        }
        adapter = new NewsAdapter(getContext(),resultBeen);
        binding.listView.setAdapter(adapter);
    }


    @Override
    protected void initView() {
        initSpringView();
    }

    @Override
    protected void initListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News.ResultBean bean = resultBeen.get(position-1);
                AllUtils.skipActivityWithParam(getContext(), NewsDetailActivity.class, bean);
            }
        });

    }

    private void initSpringView() {
        mSpringView = binding.springView;
        mSpringView.setHeader(new DefaultHeader(getContext()));
        mSpringView.setFooter(new DefaultFooter(getContext()));
        mSpringView.setType(SpringView.Type.FOLLOW);
        mSpringView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                String url = AllUtils.getUrl(id,mDefaultPage);
                HttpUtils utils = new HttpUtils();
                utils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String json = responseInfo.result;
                        json = json.replace(id,"result");
                        Gson gson = new Gson();
                        News news = gson.fromJson(json,News.class);
                        resultBeen.clear();
                        resultBeen.addAll(news.getResult());
                        adapter.notifyDataSetChanged();
                        mSpringView.onFinishFreshAndLoad();
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {

                    }
                });

            }

            @Override
            public void onLoadmore() {
                page++;
                String url = AllUtils.getUrl(id,page);
                HttpUtils utils = new HttpUtils();
                utils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String json = responseInfo.result;
                        json = json.replace(id,"result");
                        Gson gson = new Gson();
                        News news = gson.fromJson(json,News.class);
                        resultBeen.addAll(news.getResult());
                        adapter.notifyDataSetChanged();
                        mSpringView.onFinishFreshAndLoad();
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {

                    }
                });

            }
        });
    }
}
