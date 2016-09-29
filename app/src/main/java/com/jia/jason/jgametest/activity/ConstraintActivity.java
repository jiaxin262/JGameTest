package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.jia.jason.jgametest.JApplication;
import com.jia.jason.jgametest.R;

/**
 * Created by xin.jia
 * since 2016/9/29
 */

public class ConstraintActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constraint_layout);

        TextView iconTv = (TextView) findViewById(R.id.icon_font_tv);
        iconTv.setTypeface(JApplication.getIconFont());
        iconTv.setText(R.string.j_location);
    }
}
