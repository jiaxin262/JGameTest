package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.jia.jason.jgametest.R;
import com.jia.jason.jgametest.view.IRecommendView;
import com.jia.jason.jgametest.view.RecommendViewProxy;

/**
 * Created by jiaxin on 2017/1/3.
 */

public class ProxyTestActivity extends BaseActivity {

    public static final String TAG = "ProxyTestActivity";

    private IRecommendView mRecommendView;
    private FrameLayout emptyLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_layout);

        emptyLl = (FrameLayout) findViewById(R.id.j_empty_view_ll);
        emptyLl.setOnClickListener(this);
        init();
    }

    private void init() {
        mRecommendView = new RecommendViewProxy(2, emptyLl);
        mRecommendView.hide();
        Log.d(TAG, mRecommendView.toString());
    }

    @Override
    public void onClick(View v) {
        mRecommendView.onPageStarted();
    }
}
