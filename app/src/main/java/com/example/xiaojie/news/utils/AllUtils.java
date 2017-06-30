package com.example.xiaojie.news.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.BaseObservable;
import android.os.Handler;
import android.os.Looper;

import com.example.xiaojie.news.bean.News;
import com.example.xiaojie.news.ui.activity.StartActivity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by xiaojie on 2017/6/26.
 */

public class AllUtils extends BaseObservable{

    private static Handler mHandler = new Handler(Looper.getMainLooper());
    public static Executor sExecutor = Executors.newSingleThreadExecutor();
    public static Intent intent;
    private static final String FIRST_SP="FIRST_SP";
    private static final String FIRST_RUN="FIRST_RUN";

    public static void skipActivity(Context context, Class destination){
        if(intent == null){
            intent = new Intent();
        }
        intent.setClass(context,destination);
        context.startActivity(intent);
    }

    public static void skipActivityWithParam(Context context, Class destination, News.ResultBean bean){
        Intent intent = new Intent();
        intent.setClass(context,destination);
        intent.putExtra("news",bean);
        context.startActivity(intent);
    }

    public static void runOnBackgroundThread(Runnable runnable){
        sExecutor.execute(runnable);
    }
    public static void runOnMainThread(Runnable runnable){
        mHandler.post(runnable);
    }
    public static boolean isFirstRun(Context context){
        SharedPreferences sp = context.getSharedPreferences(FIRST_SP,context.MODE_PRIVATE);
        return sp.getBoolean(FIRST_RUN,true);
    }
    public static void setIsFirstRun(Context context, boolean b){
        SharedPreferences sp = context.getSharedPreferences(FIRST_RUN, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(FIRST_RUN,b);
        editor.commit();
    }

    public static String getUrl(String channelId, int page){
        return "http://c.m.163.com/nc/article/headline/"+channelId+"/"+(page-1)*20+"-20.html";
    }

    public static String getVideoUrl(int page){
        return "http://c.m.163.com/nc/video/list/V9LG4B3A0/y/"+(page-1)*20+"-20.html";
    }

}
