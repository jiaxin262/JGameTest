package com.jia.jason.jgametest.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jia.jason.jgametest.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by xin.jia
 * since 2016/8/9
 */
public class Fragment2 extends Fragment {

    TextView tvFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_layotu2, container, false);
        tvFragment = (TextView) view.findViewById(R.id.fragment_tv);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvFragment.setText("This is Fragment2");
    }
}
