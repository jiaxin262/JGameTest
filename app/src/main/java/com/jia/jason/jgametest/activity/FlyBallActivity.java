package com.jia.jason.jgametest.activity;

import android.os.Bundle;

import com.jia.jason.jgametest.view.MovementView;

public class FlyBallActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MovementView(this));
    }

}
