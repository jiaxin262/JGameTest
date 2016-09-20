package com.jia.jason.jgametest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jia.jason.jgametest.R;
import com.jia.jason.jgametest.util.CopyCrashUtil;

import java.io.File;

/**
 * Created by xin.jia
 * since 2016/5/6
 */
public class CopyCrashFragment extends Fragment implements View.OnClickListener {


    private TextView tvCopyCrash;

    boolean result;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.atom_flight_copy_crash, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvCopyCrash.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        result = CopyCrashUtil.startCopy(new File("data/data/com.Qunar/files"));
        showResult(result);
    }

    private void showResult(boolean result) {
        if (result) {
            Toast.makeText(getContext(), "copy success!!!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "copy failed!!!", Toast.LENGTH_SHORT).show();
        }
    }
}
