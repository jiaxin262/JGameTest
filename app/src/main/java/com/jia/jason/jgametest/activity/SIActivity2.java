package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jia.jason.jgametest.R;

/**
 * Created by jiaxin on 16/10/12.
 */

public class SIActivity2 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_layout);
        Log.d("Activity2", "Task id is:" + getTaskId());

        TextView tvTask = (TextView) findViewById(R.id.j_task_tv);
        Button btnTask = (Button) findViewById(R.id.j_task_btn);

        tvTask.setText("Activity2");
        btnTask.setText("点击开启Activity3");
        btnTask.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.j_task_btn) {
            jStartActivity(SIActivity3.class);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Activity2", "onstart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Activity2", "onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Activity2", "destroy");
    }
}
