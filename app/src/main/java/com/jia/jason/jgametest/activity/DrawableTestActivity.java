package com.jia.jason.jgametest.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

import com.jia.jason.jgametest.R;

/**
 * Created by xin.jia
 * since 2016/8/2
 */
public class DrawableTestActivity extends BaseActivity {

    TextView tvColorDraw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawable_test_layout);

        tvColorDraw = (TextView) findViewById(R.id.tv_color_drawable);
        //ColorDrawable colorDrawable = new ColorDrawable(0xffff0000);
        ColorDrawable colorDrawable = (ColorDrawable) tvColorDraw.getBackground();
        colorDrawable.setColor(0xffffffff);
        tvColorDraw.setTextColor(0xffab00ff);
        Color.parseColor("#ff00ff");

    }
}
