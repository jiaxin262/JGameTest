package com.jia.jason.jgametest.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jia.jason.jgametest.R;
import com.jia.jason.jgametest.helper.BitmapHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiaxin on 2016/11/30.
 */

public class HorizontalScrollActivity extends BaseActivity {

    public static final String TAG = "HScrollActivity";
    LinearLayout hScrollLlLine1;
    LinearLayout hScrollLlLine2;
    ImageView imageView;

    private final int ITEM_SIZE = 15;
    String[] contents = new String[ITEM_SIZE];
    List<Integer> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horizontal_scroll_lables_layout);

        hScrollLlLine1 = (LinearLayout) findViewById(R.id.j_h_scroll_line1);
        hScrollLlLine2 = (LinearLayout) findViewById(R.id.j_h_scroll_line2);
        LinearLayout ll = (LinearLayout) findViewById(R.id.recommend_1_bg_ll);
        ll.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_1));

        mockData(ITEM_SIZE);
        for (int i = 0; i < ITEM_SIZE; i++) {
            if (i % 2 == 0) {
                hScrollLlLine1.addView(generateCapsuleLabel(contents[i]), getCapsuleLayoutParam());
            } else {
                hScrollLlLine2.addView(generateCapsuleLabel(contents[i]), getCapsuleLayoutParam());
            }
        }

        imageView = (ImageView) findViewById(R.id.recommend_bg_test_img);
        imageView.setOnClickListener(this);

        hScrollLlLine1.getChildAt(-1);
        Log.d(TAG, -3%2+"");

        Log.d(TAG, "BitmapHelper.dip2px(15)=" + BitmapHelper.dip2px(15));

    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, imageView.getWidth()+","+imageView.getMeasuredWidth());
    }

    private void mockData(int itemSize) {
        String[] s = {"宇宙中心五道口", "北京五道口繁华吗", "北京五道口的旅游景点", "五道口 潮流", "五道口 酒吧街", "北大金融学 参考书", "阿斯顿发生的冯绍峰", "阿拉蕾"};
        for (int i = 0; i < itemSize; i++) {
            contents[i] = "" + s[i / 3];
        }
    }

    private View generateCapsuleLabel(String content) {
        TextView capsuleTv = new TextView(HorizontalScrollActivity.this);
        capsuleTv.setText(content);
        capsuleTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
        capsuleTv.setMaxEms(9);
        capsuleTv.setMaxLines(1);
        //capsuleTv.setMaxWidth(BitmapHelper.dip2px(130));
        capsuleTv.setEllipsize(TextUtils.TruncateAt.END);
        capsuleTv.setBackgroundDrawable(getResources().getDrawable(R.drawable.capsule_selector));
        capsuleTv.setOnClickListener(this);
        return capsuleTv;
    }

    private LinearLayout.LayoutParams getCapsuleLayoutParam() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = BitmapHelper.dip2px(10);
        return layoutParams;
    }


}
