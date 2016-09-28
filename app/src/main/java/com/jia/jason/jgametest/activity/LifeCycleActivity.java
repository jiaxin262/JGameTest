package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

/**
 * Created by xin.jia
 * since 2016/9/28
 */
public class LifeCycleActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("lifeCycle","onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("lifeCycle","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("lifeCycle","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("lifeCycle","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("lifeCycle","onDestroy");
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("lifeCycle","onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("lifeCycle","onSaveInstanceState");
    }
}
