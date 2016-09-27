package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.jia.jason.jgametest.R;

/**
 * Created by xin.jia
 * since 2016/9/22
 */
public class DrawableAnimationActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.drawable_animation_layout);

        ImageView imageView = (ImageView) findViewById(R.id.drawable_animation_iv);

    }
}
