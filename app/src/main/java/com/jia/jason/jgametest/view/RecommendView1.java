package com.jia.jason.jgametest.view;

import android.util.Log;

/**
 * Created by jiaxin on 2017/1/3.
 */

public class RecommendView1 extends RecommendViewBase {

    public RecommendView1(int flag) {
        super(flag, null);
    }

    @Override
    public void hide() {
        super.hide();
        Log.d(TAG, "RecommendView1.hide()");
    }
}
