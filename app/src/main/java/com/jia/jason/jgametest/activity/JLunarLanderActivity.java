package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jia.jason.jgametest.R;
import com.jia.jason.jgametest.view.JLunarView;

/**
 * Created by xin.jia
 * since 2016/2/29
 */
public class JLunarLanderActivity extends BaseActivity {

    private JLunarView.JLunarThread jLunarThread;
    private JLunarView jLunarView;
    private TextView tvGameStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.j_lunar_layout);

        jLunarView = (JLunarView) findViewById(R.id.j_lunar);
        jLunarThread = jLunarView.getThread();
        jLunarView.setTextView((TextView) findViewById(R.id.lunar_text));
        tvGameStart = (TextView) findViewById(R.id.game_start);
        jLunarView.setGameStartTextView(tvGameStart);
        tvGameStart.setOnClickListener(this);

        if (savedInstanceState == null) {
            jLunarThread.setState(JLunarView.JLunarThread.STATE_READY);
        } else {
            jLunarThread.restoreState(savedInstanceState);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.game_start:
                jLunarThread.doStart();
                tvGameStart.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        jLunarView.getThread().pause(); // pause game when Activity pauses
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // just have the View's thread save its state into our Bundle
        super.onSaveInstanceState(outState);
        jLunarThread.saveState(outState);
    }

}
