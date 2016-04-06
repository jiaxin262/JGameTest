package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.view.Window;
import com.jia.jason.jgametest.view.GBallView;

/**
 * Created by xin.jia
 * since 2016/2/25
 */
public class GBallActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GBallView(this));
    }

}
