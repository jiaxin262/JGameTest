package com.jia.jason.jgametest.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jia.jason.jgametest.R;

/**
 * Created by xin.jia
 * since 2016/8/7
 */
public class FragmentTest extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test_layotu, container, false);
    }
}
