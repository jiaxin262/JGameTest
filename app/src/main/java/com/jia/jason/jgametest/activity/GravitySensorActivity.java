package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import com.jia.jason.jgametest.view.GravitySensorView;

/**
 * Created by xin.jia
 * since 2016/2/27
 */
public class GravitySensorActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GravitySensorView(this));
    }
}
