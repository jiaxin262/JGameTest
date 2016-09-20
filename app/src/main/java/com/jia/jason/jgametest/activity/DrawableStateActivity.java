package com.jia.jason.jgametest.activity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jia.jason.jgametest.R;

/**
 * Created by xin.jia
 * since 2016/8/4
 */
public class DrawableStateActivity extends BaseActivity {

    private LinearLayout llDrawableState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawable_state_layout);

        llDrawableState = (LinearLayout) findViewById(R.id.ll_drawable_state);
        TextView tvDraw = (TextView) findViewById(R.id.tv_drawable_state);
        tvDraw.setBackgroundResource(R.drawable.jason_shape);
        GradientDrawable gd1 = (GradientDrawable) tvDraw.getBackground();
        gd1.setColor(Color.RED);
        addTextView();

        tvDraw.setOnClickListener(this);
    }

    private void addTextView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = 20;
        TextView textView = new TextView(this);
        textView.setBackgroundResource(R.drawable.jason_shape);
        llDrawableState.addView(textView, params);
    }

    @Override
    public void onClick(View v) {
//        GradientDrawable gd1 = (GradientDrawable) tvDraw.getBackground();
//        gd1.setColor(Color.BLUE);
//        addTextView();
        jStartActivity(DrawableState2Activity.class);
    }

}
