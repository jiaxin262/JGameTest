package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.util.Log;

import com.jia.jason.jgametest.view.IRecommendView;
import com.jia.jason.jgametest.view.RecommendViewProxy;

/**
 * Created by jiaxin on 2017/1/3.
 */

public class ProxyTestActivity extends BaseActivity {

    public static final String TAG = "ProxyTestActivity";

    IRecommendView mRecommendView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intit();
    }

    private void intit() {
        mRecommendView = new RecommendViewProxy(2);
        mRecommendView.hide();
        Log.d(TAG, mRecommendView.toString());
    }

}
