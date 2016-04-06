package com.jia.jason.jgametest.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;

import com.jia.jason.jgametest.view.GravitySensorView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xin.jia
 * since 2016/3/8
 */
public class ListAddSetActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GravitySensorView(this));

        Log.e("heightPixels:", getResources().getDisplayMetrics().heightPixels+"");
        //testListAdd();
        //testListSet();
    }

    public void testListAdd() {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(11);
        integerList.add(13);
        integerList.add(1, 9);
        Log.e("integerList:", integerList.toString());
        Log.e("integerList.size", integerList.size()+"");
    }

    public void testListSet() {
//        List<Integer> integerList = new ArrayList<>();
//        integerList.add(11);
//        integerList.add(13);
//        integerList.set(0, 9);
//        Log.e("integerList:", integerList.toString());
//        Log.e("integerList.size", integerList.size()+"");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int activityHeight = frame.top;
    }
}
