package com.example.xiaojie.news.ui.activity;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;


import com.example.xiaojie.news.R;
import com.example.xiaojie.news.databinding.ActivityMainBinding;
import com.example.xiaojie.news.ui.fragment.FindFragment;
import com.example.xiaojie.news.ui.fragment.NewsFragment;
import com.example.xiaojie.news.ui.fragment.ReadFragment;
import com.example.xiaojie.news.ui.fragment.SettingFragment;
import com.example.xiaojie.news.ui.fragment.ViewListeningFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private RadioGroup mRadioGroup;
    private ViewPager mViewPager;
    private ActivityMainBinding binding;
    List<Fragment> fragmentList;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mActionBarDrawerToggle;


    @Override
    protected void initData() {
        binding = DataBindingUtil.setContentView(this,getLayoutResId());
        binding.setPresenter(new Presenter());
        fragmentList = new ArrayList<>();
        fragmentList.add(new NewsFragment());
        fragmentList.add(new ViewListeningFragment());
        fragmentList.add(new ReadFragment());
        fragmentList.add(new FindFragment());
        fragmentList.add(new SettingFragment());
    }

    @Override
    protected void initView() {
        mViewPager = binding.pagerView;
        mRadioGroup = binding.radioGroup;


        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mRadioGroup.check(R.id.btn_news);
                        break;
                    case 1:
                        mRadioGroup.check(R.id.btn_viewlistening);
                        break;
                    case 2:
                        mRadioGroup.check(R.id.btn_read);
                        break;
                    case 3:
                        mRadioGroup.check(R.id.btn_find);
                        break;
                    case 4:
                        mRadioGroup.check(R.id.btn_settings);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        initNavigation();
        initToolbar();
        initActionBarDrawerToggle();
    }

    private void initActionBarDrawerToggle(){
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,0,0);
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
    }

    private void initToolbar(){
        mToolbar = binding.toolBar;
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("广东交通职业技术学院");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_main01){
            showToast("mainMenu01");
        }
        return super.onOptionsItemSelected(item);
    }

    private void initNavigation() {
        mDrawerLayout = binding.drawerLayout;
        mNavigationView = binding.navigationView;
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.menu01){
                    showToast("菜单一");
                    mDrawerLayout.closeDrawers();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initListener() {

    }

    public class Presenter{

        public void onClickNews(View v){
            mViewPager.setCurrentItem(0);
        }

        public void onClickViewListening(View v){
            mViewPager.setCurrentItem(1);
        }

        public void onClickRead(View v){
            mViewPager.setCurrentItem(2);
        }

        public void onClickFind(View v){
            mViewPager.setCurrentItem(3);
        }

        public void onClickSetting(View v){
            mViewPager.setCurrentItem(4);
        }

    }
}
