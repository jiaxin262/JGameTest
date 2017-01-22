package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jiaxin on 2017/1/19.
 */

public class IteratorTestActivity extends BaseActivity {
    public static final String TAG = "IteratorTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            strings.add("-"+i);
        }
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            if ("-3".equals(s)) {
                iterator.remove();
            }
        }
        for (String s : strings) {
            Log.d(TAG, s);
        }
    }

}
