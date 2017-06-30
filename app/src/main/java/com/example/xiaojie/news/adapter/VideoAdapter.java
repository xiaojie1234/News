package com.example.xiaojie.news.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.tool.store.SetterStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.xiaojie.news.BR;
import com.example.xiaojie.news.R;
import com.example.xiaojie.news.bean.VideoBean;
import com.example.xiaojie.news.databinding.ItemVideoBinding;

import org.apache.commons.io.filefilter.TrueFileFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaojie on 2017/6/29.
 */

public class VideoAdapter extends RecyclerView.Adapter<BindingViewHolder>{

    private List<VideoBean.ResultBean> beanList;
    private Context context;
    private LayoutInflater inflater;
    private ItemVideoBinding binding;
//    private OnItemClickListener mOnClickListener;
//
//    public interface OnItemClickListener{
//        void onItemClick(VideoBean.ResultBean bean);
//    }

    public VideoAdapter(Context context) {
        super();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        beanList = new ArrayList<>();
        this.context = context;
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding;
        binding = DataBindingUtil.inflate(inflater, R.layout.item_video,parent,false);
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final BindingViewHolder holder, int position) {
        final VideoBean.ResultBean bean = beanList.get(position);
        holder.getmBinding().setVariable(BR.resultBean,bean);


        binding = DataBindingUtil.bind(holder.getmBinding().getRoot());
        binding.videoPlayer.setUp(bean.getMp4_url(),bean.getTitle());



        holder.getmBinding().executePendingBindings();


//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                if(mOnClickListener != null){
////                    mOnClickListener.onItemClick(bean);
////                }
////                Toast.makeText(context,"ok",Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

//    public void setmOnClickListener(OnItemClickListener mOnClickListener) {
//        this.mOnClickListener = mOnClickListener;
//    }

    public void addAll(List<VideoBean.ResultBean> bean){
        beanList.addAll(bean);
    }

}
