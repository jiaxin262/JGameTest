package com.jia.jason.jgametest.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jia.jason.jgametest.R;

/**
 * Created by jiaxin on 2017/1/4.
 */

public class BuildValueTestActivity extends BaseActivity {
    public static final String TAG = "BuildValueTestActivity";

    LinearLayout ll;
    int textColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.build_value_layut);

        ll = (LinearLayout) findViewById(R.id.j_build_value_ll);
        ll.addView(getTextView("Build.BRAND:   " + Build.BRAND));
        ll.addView(getTextView("Build.MODEL:   " + Build.MODEL));
        ll.addView(getTextView("Build.BOARD:   " + Build.BOARD));
        ll.addView(getTextView("Build.BOOTLOADER:   " + Build.BOOTLOADER));
        ll.addView(getTextView("Build.DEVICE:   " + Build.DEVICE));
        ll.addView(getTextView("Build.DISPLAY:   " + Build.DISPLAY));
        ll.addView(getTextView("Build.FINGERPRINT:   " + Build.FINGERPRINT));
        ll.addView(getTextView("Build.HARDWARE:   " + Build.HARDWARE));
        ll.addView(getTextView("Build.HOST:   " + Build.HOST));
        ll.addView(getTextView("Build.ID:   " + Build.ID));
        ll.addView(getTextView("Build.MANUFACTURER:   " + Build.MANUFACTURER));
        ll.addView(getTextView("Build.PRODUCT:   " + Build.PRODUCT));
        ll.addView(getTextView("Build.SERIAL:   " + Build.SERIAL));
        ll.addView(getTextView("Build.TAGS:   " + Build.TAGS));
        ll.addView(getTextView("Build.TYPE:   " + Build.TYPE));
        ll.addView(getTextView("Build.UNKNOWN:   " + Build.UNKNOWN));
        ll.addView(getTextView("Build.USER:   " + Build.USER));
        ll.addView(getTextView("Build.TIME:   " + Build.TIME));
        ll.addView(getTextView("Build.SUPPORTED_ABIS:   " + Build.SUPPORTED_ABIS));
        ll.addView(getTextView("Build.RADIO:   " + Build.RADIO));
        ll.addView(getTextView("Build.CPU_ABI:   " + Build.CPU_ABI));
        ll.addView(getTextView("Build.CPU_ABI2:   " + Build.CPU_ABI2));
        ll.addView(getTextView("Build.SUPPORTED_32_BIT_ABIS:   " + Build.SUPPORTED_32_BIT_ABIS));
        ll.addView(getTextView("Build.SUPPORTED_64_BIT_ABIS:   " + Build.SUPPORTED_64_BIT_ABIS));
        ll.addView(getTextView("Build.getRadioVersion:   " + Build.getRadioVersion()));

        String color = "#FF00FF,#ff4422";
        String[] colors = color.split(",");
        textColor = Color.parseColor(colors[0]);
        Log.d(TAG, "textColor:"+textColor);
        ll.addView(getTextView("testColor"));

    }

    private View getTextView(String s) {
        TextView tv = new TextView(this);
        tv.setText(s);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
        if (textColor != 0) {
            tv.setTextColor(textColor);
        }
        return tv;
    }
}
