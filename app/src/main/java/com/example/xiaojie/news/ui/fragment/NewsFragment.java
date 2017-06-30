package com.example.xiaojie.news.ui.fragment;

import android.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.xiaojie.news.R;
import com.example.xiaojie.news.databinding.FragmentNewsBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaojie on 2017/6/27.
 */

public class NewsFragment extends BaseFragment{

    private FragmentNewsBinding binding;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        binding = DataBindingUtil.inflate(inflater,getLayoutRes(),container,false);
//        mTabLayout = binding.newsTabLayout;
//        mViewPager = binding.newsViewPager;
//        mViewPager.setBackgroundColor(Color.GREEN);
//        initViewPager();
//        return binding.getRoot();
//    }


    @Override
    protected void initData(View root) {
        binding = DataBindingUtil.bind(root);
        mTabLayout = binding.newsTabLayout;
        mViewPager = binding.newsViewPager;
        initViewPager();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    private void initViewPager() {

        final String[] titles = new String[] {
                "头条", "社会", "科技", "财经", "体育", "汽车"
        };
        //http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
        final String[] channelIds = new String[] {
                "T1348647909107",
                "T1348648037603",
                "T1348649580692",
                "T1348648756099",
                "T1348649079062",
                "T1348654060988",
        };
        final List<Fragment> fragmentList = new ArrayList<>();
        for(int i=0;i<titles.length;i++){
            NewsItemFragment fragment = new NewsItemFragment();
            fragment.setId(channelIds[i]);
            fragmentList.add(fragment);
        }
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);

    }
}
