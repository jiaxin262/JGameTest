package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jia.jason.jgametest.R;
import com.jia.jason.jgametest.helper.BitmapHelper;

/**
 * Created by jiaxin on 2016/11/30.
 */

public class HorizontalScrollActivity extends BaseActivity {

    LinearLayout hScrollLlLine1;
    LinearLayout hScrollLlLine2;

    private final int ITEM_SIZE = 15;
    String[] contents = new String[ITEM_SIZE];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horizontal_scroll_lables_layout);

        hScrollLlLine1 = (LinearLayout) findViewById(R.id.j_h_scroll_line1);
        hScrollLlLine2 = (LinearLayout) findViewById(R.id.j_h_scroll_line2);

        mockData(ITEM_SIZE);
        for (int i = 0; i < ITEM_SIZE; i++) {
            if (i % 2 == 0) {
                hScrollLlLine1.addView(generateCapsuleLabel(contents[i]), getCapsuleLayoutParam());
            } else {
                hScrollLlLine2.addView(generateCapsuleLabel(contents[i]), getCapsuleLayoutParam());
            }
        }
    }

    private void mockData(int itemSize) {
        String[] s = {"abcdefghijklmnopqrstabcdefghijklmnopqrst", "萨达", "12345678901234561234567890123456", "一二三四五六七八九十零一二三四五六七八九十零", "aed d阿斯蒂芬", "第三方", "阿斯顿发生的冯绍峰", "阿拉蕾"};
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
