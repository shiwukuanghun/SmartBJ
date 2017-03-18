package com.itheima.smartbj.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by HuangBin on 2017/3/16 18:56.
 */

/**
 * 基类， 封装一些activity的公共功能
 * 有利于项目的拓展和维护
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        init();
    }

    /**
     * 子类可以复写该方法做一些子类的初始化
     */
    protected void init(){
        //初始化公共的功能
    }

    /**
     * 子类必须实现该方法，实现布局文件的id
     * @return
     */
    public abstract int getLayoutResId();

    /**
     * 抽取跳转到某个Activity的方法
     * @param activity
     */
    public void navigateTo(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        finish();
    }
}
