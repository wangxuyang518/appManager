package com.xylibrary.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.xylibrary.widget.LoadingDialig;

import xinyi.com.xylibrary.R;

/**
 * Created by jiajun.wang on 2018/3/19.
 */

public abstract class BaseActivity extends AppCompatActivity implements com.xylibrary.base.IBaseView {

    @Nullable
    public   TextView centerTitle=null;

    @Nullable
    public Toolbar toolBar;
    public LoadingDialig mLoadingDialig;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayoutResource());
        toolBar = findViewById(R.id.toolBar);
        mLoadingDialig = new LoadingDialig(this);
        initView();
    }
    public abstract int getLayoutResource();
    public abstract void initView();
    @Override
    public void showNoNetWork() {

    }
    @Override
    public void showLoading() {
        if (mLoadingDialig != null)
            mLoadingDialig.show();
    }

    @Override
    public void showLoadFail() {

    }

    @Override
    public void showContentView() {

    }

    @Override
    public void hideLoading() {
        if (mLoadingDialig != null)
            mLoadingDialig.dismiss();
    }

    @Override
    public void showEmptyView() {

    }

}
