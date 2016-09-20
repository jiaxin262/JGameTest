package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.view.WindowManager;
import com.jia.jason.jgametest.view.FingerView;

/**
 * Created by xin.jia
 * since 2016/2/19
 */
public class FingerPathActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new FingerView(this));
    }

}
