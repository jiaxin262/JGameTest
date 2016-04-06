package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.view.Window;

import com.jia.jason.jgametest.view.MovementView;

public class FlyBallActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MovementView(this));
    }

}
