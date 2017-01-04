package com.jia.jason.jgametest.view;

import android.content.Context;
import android.util.Log;
import android.widget.FrameLayout;

/**
 * Created by jiaxin on 2017/1/3.
 */

public class RecommendViewProxy implements IRecommendView {

    public static final String TAG = "RecommendViewProxy";

    private RecommendViewBase mRecommendView;
    private int uiType;

    public RecommendViewProxy(int uiType, FrameLayout container) {
        this.uiType = uiType;
        if (uiType == 1) {
            mRecommendView = new RecommendView1(uiType);
        } else if (uiType == 2) {
            mRecommendView = new RecommendView2(uiType, container);
        } else {
            mRecommendView = new RecommendView1(uiType);
        }
    }

    @Override
    public void hide() {
        Log.d(TAG, mRecommendView.toString());
        mRecommendView.hide();
    }

    @Override
    public void onPageStarted() {
        mRecommendView.onPageStarted();
    }

}
