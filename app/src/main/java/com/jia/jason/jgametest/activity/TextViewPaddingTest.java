package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.jia.jason.jgametest.R;

/**
 * Created by jiaxin on 2016/12/2.
 */

public class TextViewPaddingTest extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tv_padding_layout);

        TextView tv = (TextView) findViewById(R.id.tv_padding_test);
        //tv.setTextColor(0);
    }
}
