package com.example.xiaojie.news.ui.activity;

import android.animation.Animator;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.example.xiaojie.news.R;
import com.example.xiaojie.news.databinding.ActivityGuideBinding;
import com.example.xiaojie.news.presenter.GuidePresenter;
import com.example.xiaojie.news.presenter.presenterImpl.GuidePresenterImpl;
import com.example.xiaojie.news.utils.AllUtils;
import com.example.xiaojie.news.view.GuideView;

/**
 * Created by xiaojie on 2017/6/26.
 */

public class GuideActivity extends BaseActivity implements GuideView{


    private int[] mImage;
    private int index;
    private boolean mExitActivity;
    private ActivityGuideBinding binding;
    private GuidePresenter mGuidePresenter;
    private static final int DURATION = 3000;
    private MediaPlayer mMediaPlayer;


    @Override
    protected void initData() {
        binding = DataBindingUtil.setContentView(this, getLayoutResId());
        //与presenter绑定
        mGuidePresenter = new GuidePresenterImpl(this);
        binding.setPresenter(new presenter());
        binding.setBg(R.drawable.ad_new_version1_img1);
        index=0;
        mExitActivity=false;
        mImage = new int[]{
                R.drawable.ad_new_version1_img1,R.drawable.ad_new_version1_img2,
                R.drawable.ad_new_version1_img3,R.drawable.ad_new_version1_img4,
                R.drawable.ad_new_version1_img5,R.drawable.ad_new_version1_img6,
                R.drawable.ad_new_version1_img7,
        };


    }

    @Override
    protected void initView() {
        startViewAnimation();
        playMusic();
    }

    private void playMusic() {
        mMediaPlayer = MediaPlayer.create(this,R.raw.new_version);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();
    }
    private void stopMusic(){
        if(mMediaPlayer != null){
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mExitActivity=true;
        stopMusic();
    }

    private void startViewAnimation() {
        int imageId = mImage[index%mImage.length];
        binding.iv.setBackgroundResource(imageId);
        index++;
        binding.iv.setScaleX(1f);
        binding.iv.setScaleY(1f);
        binding.iv.animate().scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(DURATION)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if(!mExitActivity){
                            startViewAnimation();
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void enterMain() {
        stopMusic();
        AllUtils.skipActivity(GuideActivity.this, MainActivity.class);
        finish();
    }

    @BindingAdapter({"resId"})
    public static void setBackground(ImageView imageView, Integer resId){
        if(resId == null){
            imageView.setBackgroundResource(R.drawable.ad_new_version1_img1);
        }else{
            imageView.setBackgroundResource(resId);
        }
    }
    public class presenter{

        public void onClick(View v) {
            enterMain();
        }
    }
}
