package com.jia.jason.jgametest.view;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by jiaxin on 2017/1/3.
 */

public class RecommendViewBase implements IRecommendView, View.OnClickListener {

    public static final String TAG = "RecommendViewBase";

    ViewGroup recommendLayout;
    FrameLayout mContainer;

    private int flag;

    public RecommendViewBase(int flag, FrameLayout container) {
        this.flag = flag;
        mContainer = container;
        initView();
    }

    public void initView() {
        Log.d(TAG, "RecommendViewBase.initView()");
    }

    @Override
    public void hide() {
        Log.d(TAG, "RecommendViewBase.hide()");
    }

    @Override
    public void onPageStarted() {
        Log.d(TAG, "RecommendViewBase.onPageStart()");
    }


    @Override
    public void onClick(View v) {
        Log.d(TAG, "RecommendViewBase.onClick()");
    }
}
