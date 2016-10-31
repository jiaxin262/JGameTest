package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jia.jason.jgametest.R;

/**
 * Created by jiaxin on 16/10/31.
 */

public class ViewTouchTestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch_test_layout);

        LinearLayout llBigView = (LinearLayout) findViewById(R.id.j_touch_big_view);
        TextView tvSmallView = (TextView) findViewById(R.id.j_touch_small_view);

        llBigView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("ViewTouchTest-", "bigView-onTouch");
                return false;
            }
        });

        llBigView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ViewTouchTest-", "bigView-onClick");
            }
        });

        tvSmallView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("ViewTouchTest-", "smallView");
                return false;
            }
        });
        tvSmallView.setClickable(true);

    }
}
