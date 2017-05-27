package com.tctogether.zxing;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * @author CXP
 * @desc 初始化
 * @time 2017/1/12 19:51
 * @email q978515743@163.com
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化二维码
        ZXingLibrary.initDisplayOpinion(this);
    }
}
