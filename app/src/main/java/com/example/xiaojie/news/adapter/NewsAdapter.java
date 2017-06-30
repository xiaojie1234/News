package com.example.xiaojie.news.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.xiaojie.news.R;
import com.example.xiaojie.news.bean.News;
import com.example.xiaojie.news.databinding.NewsListType1Binding;
import com.example.xiaojie.news.databinding.NewsListType2Binding;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by xiaojie on 2017/6/28.
 */

public class NewsAdapter extends BaseAdapter {

    private static Context context;
    private List<News.ResultBean> resultBeen;
    private LayoutInflater inflater;
    private NewsListType1Binding type1Binding;
    private NewsListType2Binding type2Binding;
    private static final int NEWS_ITEM_1_IMG = 0;
    private static final int NEWS_ITME_3_IMG = 1;

    public NewsAdapter(Context context, List<News.ResultBean> resultBeen) {
        this.context = context;
        this.resultBeen = resultBeen;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return resultBeen == null ? 0 : resultBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return resultBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(getItemViewType(position) == NEWS_ITEM_1_IMG){
            if(convertView == null){
                type1Binding = DataBindingUtil.inflate(inflater,R.layout.news_list_type_1,parent,false);
                convertView = type1Binding.getRoot();
                convertView.setTag(type1Binding);

            }else{
                type1Binding = (NewsListType1Binding)convertView.getTag();
            }
            type1Binding.setNewBean(resultBeen.get(position));
        }else{
            if(convertView == null){
                type2Binding = DataBindingUtil.inflate(inflater, R.layout.news_list_type_2, parent, false);
                convertView = type2Binding.getRoot();
                convertView.setTag(type2Binding);
            }else{
                type2Binding = (NewsListType2Binding)convertView.getTag();
            }
            type2Binding.setNewBean(resultBeen.get(position));

        }
        return convertView;

    }

    @Override
    public int getItemViewType(int position) {
        News.ResultBean bean = (News.ResultBean) getItem(position);
        if(bean.getImgextra() != null && bean.getImgextra().size() > 0){
            return NEWS_ITME_3_IMG;
        }else{
            return NEWS_ITEM_1_IMG;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
    @BindingAdapter({"imageUrl"})
    public static void setImage(ImageView imageView, String imageUrl){
        if(imageUrl != null){
            Picasso.with(context).load(imageUrl).into(imageView);
        }
    }
}
