package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.widget.TextView;

import com.jia.jason.jgametest.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jiaxin on 2016/12/2.
 */

public class TextViewPaddingTest extends BaseActivity {

    public static final String TAG = "TextViewPaddingTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tv_padding_layout);

        TextView tv = (TextView) findViewById(R.id.tv_padding_test);
        tv.setSingleLine(true);
        tv.setEllipsize(TextUtils.TruncateAt.END);
        //tv.setTextColor(0);


        for (int i = 0; i < 5; i++) {
            Log.d(TAG, i+"");
            for (int j = 0; j < 5; j++) {
                Log.d(TAG, j+"");
                if (j == 3) {
                    break;
                }
            }
        }

        List<String> list = new LinkedList<>();
        for (int i = 0; i < 6; i++) {
            list.add(i, "a"+i);
        }
        list.add(2, "add3");
        Log.d(TAG, list.get(2));
        for (String s : list) {
            Log.d(TAG, s);
        }

        SparseArray<String> array = new SparseArray<>();
        for (int i = 0; i < 10; i++) {
            array.put(i, "b"+i);
        }
        array.put(3, "put3");
        array.put(9, "put9");
        array.put(15, "put15");
        Log.d(TAG, array.size()+"");
        Log.d(TAG, array.get(9));
        Log.d(TAG, array.get(15)+"");
        for (int i = 0; i < array.size(); i++) {
            Log.d(TAG, array.get(i)+"");
        }
    }
}
