package com.jia.jason.jgametest.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jia.jason.jgametest.R;
import com.jia.jason.jgametest.helper.BitmapHelper;

/**
 * Created by jiaxin on 2016/12/5.
 */

public class ScrollTestActivity extends BaseActivity {

    public static final String TAG = "ScrollTestActivity";
    private ScrollView scrollView;
    private LinearLayout scrollContainerLl;
    private HorizontalScrollView hScrollView;
    private LinearLayout hScrollContainerLl;

    private final int CHILD_SIZE = 15;
    int[] colors = {Color.RED, Color.YELLOW, Color.BLACK, Color.BLUE, Color.GRAY, Color.GREEN, Color.CYAN,
            Color.WHITE, Color.RED, Color.YELLOW, Color.BLACK, Color.BLUE, Color.GRAY, Color.GREEN, Color.CYAN,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_test_layout);

        scrollView = (ScrollView) findViewById(R.id.j_scroll_view);
        scrollContainerLl = (LinearLayout) findViewById(R.id.j_scroll_container_ll);
        hScrollView = (HorizontalScrollView) findViewById(R.id.j_h_scroll_view);
        hScrollContainerLl = (LinearLayout) findViewById(R.id.j_h_scroll_container_ll);

        addChildViews();
        addHSChildViews();
    }

    private void addHSChildViews() {
        for (int i = 0; i < CHILD_SIZE; i++) {
            TextView tv = new TextView(this);
            tv.setText(""+i);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
            tv.setBackgroundColor(colors[i]);
            tv.setOnClickListener(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(BitmapHelper.dip2px(50), ViewGroup.LayoutParams.MATCH_PARENT);
            hScrollContainerLl.addView(tv, params);
        }
    }

    private void addChildViews() {
        for (int i = 0; i < CHILD_SIZE; i++) {
            TextView tv = new TextView(this);
            tv.setText(""+i);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
            tv.setBackgroundColor(colors[i]);
            tv.setOnClickListener(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, BitmapHelper.dip2px(500));
            scrollContainerLl.addView(tv, params);
        }
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onclick");
        scrollView.scrollTo(BitmapHelper.dip2px(100), BitmapHelper.dip2px(1000));
        hScrollView.scrollTo(BitmapHelper.dip2px(20), 100);
    }
}
