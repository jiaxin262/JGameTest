package com.jia.jason.jgametest.view;

import android.util.Log;

/**
 * Created by jiaxin on 2017/1/3.
 */

public class RecommendViewProxy implements IRecommendView {

    public static final String TAG = "RecommendViewProxy";

    private RecommendViewBase mRecommendView;
    private int uiType;

    public RecommendViewProxy() {
        this(0);
    }

    public RecommendViewProxy(int uiType) {
        this.uiType = uiType;
        if (uiType == 1) {
            mRecommendView = new RecommendView1();
        } else if (uiType == 2) {
            mRecommendView = new RecommendView2();
        } else {
            mRecommendView = new RecommendView1();
        }
    }

    @Override
    public void hide() {
        Log.d(TAG, mRecommendView.toString());
        mRecommendView.hide();
    }

}
