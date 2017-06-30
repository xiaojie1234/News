package com.example.xiaojie.news.ui.fragment;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.xiaojie.news.R;
import com.example.xiaojie.news.adapter.VideoAdapter;
import com.example.xiaojie.news.bean.VideoBean;
import com.example.xiaojie.news.databinding.FragmentViewlisteningBinding;
import com.example.xiaojie.news.utils.AllUtils;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaojie on 2017/6/27.
 */

public class ViewListeningFragment extends BaseFragment{

    private int page;
    private FragmentViewlisteningBinding mBinding;
    private RecyclerView mRecyclerView;
    List<VideoBean.ResultBean> beanList;

    @Override
    protected void initData(View root) {
        mBinding = DataBindingUtil.bind(root);
        mRecyclerView = mBinding.recyclerView;
        beanList = new ArrayList<>();
        page = 1;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_viewlistening;
    }

    @Override
    protected void initData() {
        requestVideoData();

    }

    private void requestVideoData() {
        String url = AllUtils.getVideoUrl(page);
        HttpUtils utils = new HttpUtils();

        utils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String json = responseInfo.result;
                json = json.replace("V9LG4B3A0","result");
                System.out.println(json);
                Gson gson = new Gson();
                VideoBean bean = gson.fromJson(json,VideoBean.class);
                beanList.clear();
                beanList.addAll(bean.getResult());
                if(beanList != null && beanList.size() > 0){
                    showInRecyclerView();
                }else{
                    requestVideoData();
                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
            }
        });
    }

    private void showInRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        VideoAdapter adapter = new VideoAdapter(getContext());
        adapter.addAll(beanList);
        mBinding.recyclerView.setAdapter(adapter);
//        adapter.setmOnClickListener(new VideoAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(VideoBean.ResultBean bean) {
//
//            }
//        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }
    @BindingAdapter({"app:VideoImageUrl"})
    public static void LoadVideoImg(ImageView imageView, String VideoImageUrl){
        if(VideoImageUrl != null){
            Picasso.with(imageView.getContext()).load(VideoImageUrl).into(imageView);
        }
    }
}
