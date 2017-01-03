package com.jia.jason.jgametest.view;

import android.util.Log;

/**
 * Created by jiaxin on 2017/1/3.
 */

public class RecommendViewBase implements IRecommendView {

    public static final String TAG = "RecommendViewBase";

    @Override
    public void hide() {
        Log.d(TAG, "RecommendViewBase.hide()");
    }
}
