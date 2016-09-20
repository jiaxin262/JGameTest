package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.jia.jason.jgametest.R;

/**
 * Created by xin.jia
 * since 2016/8/11
 */
public class ViewFlipperActivity extends BaseActivity {

    ViewFlipper viewFlipper;
    int[] resIds = {R.drawable.view_flipper_0, R.drawable.view_flipper_1, R.drawable.view_flipper_2, R.drawable.view_flipper_4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_flipper_layout);

        viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
        for (int resId : resIds) {
            viewFlipper.addView(getImageView(resId));
        }
        viewFlipper.setInAnimation(this, R.anim.right_in);
        viewFlipper.setOutAnimation(this, R.anim.left_out);
        viewFlipper.setFlipInterval(1500);
        viewFlipper.startFlipping();
    }

    private ImageView getImageView(int resId) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(resId);
        return imageView;
    }
}
