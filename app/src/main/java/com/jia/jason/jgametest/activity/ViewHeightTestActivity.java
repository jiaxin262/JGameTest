package com.jia.jason.jgametest.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jia.jason.jgametest.R;
import com.jia.jason.jgametest.view.ChildVeiw;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiaxin on 2016/11/29.
 */

public class ViewHeightTestActivity extends BaseActivity {

    LinearLayout viewHeightLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_height_container_layout);

        viewHeightLayout = (LinearLayout) findViewById(R.id.view_height_layout);
        viewHeightLayout.setOnClickListener(this);

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        Log.d("aaa", list.subList(0, 5).toString());

        int a = 0;
        try {
            a = Color.parseColor("#00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("color", a+"");
    }

    @Override
    public void onClick(View v) {
        ChildVeiw childView = new ChildVeiw(ViewHeightTestActivity.this, viewHeightLayout);
    }
}
