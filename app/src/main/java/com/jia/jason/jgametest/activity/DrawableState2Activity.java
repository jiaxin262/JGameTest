package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.jia.jason.jgametest.R;

/**
 * Created by xin.jia
 * since 2016/8/7
 */
public class DrawableState2Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_layout);

        TextView tvDraw = (TextView) findViewById(R.id.textView);
        tvDraw.setBackgroundResource(R.drawable.jason_shape);

    }
}
