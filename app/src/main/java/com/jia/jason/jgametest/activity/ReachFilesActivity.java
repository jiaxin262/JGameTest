package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jia.jason.jgametest.R;
import com.jia.jason.jgametest.helper.Question1;

import java.io.File;

/**
 * Created by xin.jia
 * since 2016/4/25
 */
public class ReachFilesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reach_files_layout);

        TextView textView = (TextView) findViewById(R.id.start_reach_files);
        textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        File f = new File(Question1.class.getResource("/").getPath() + "question1");
        new Question1().countChars(f);
    }
}
