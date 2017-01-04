package com.jia.jason.jgametest.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jia.jason.jgametest.R;

/**
 * Created by jiaxin on 2017/1/3.
 */

public class RecommendView2 extends RecommendViewBase {

    private TextView tv;

    public RecommendView2(int flag, FrameLayout container) {
        super(flag, container);
    }

    @Override
    public void initView() {
        super.initView();
        Log.d(TAG, "RecommendView2.initView()");

        recommendLayout = (ViewGroup) LayoutInflater.from(mContainer.getContext()).inflate(R.layout.j_child_view_layout, mContainer, false);
        tv = (TextView) recommendLayout.findViewById(R.id.j_child_view_layout_tv);
        tv.setOnClickListener(this);
        mContainer.addView(recommendLayout);
    }

    @Override
    public void hide() {
        super.hide();
        Log.d(TAG, "RecommendView2.hide()");
    }

    @Override
    public void onPageStarted() {
        super.onPageStarted();
        Log.d(TAG, "RecommendView2.onPageStart()");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Log.d(TAG, "RecommendView2.onClick()");

    }
}
