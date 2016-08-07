package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.jia.jason.jgametest.R;

/**
 * Created by xin.jia
 * since 2016/8/7
 */
public class FragmentTestActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{

    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_test_layout2);

        radioGroup = (RadioGroup) findViewById(R.id.rg_fragments);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_f1:

                break;
            case R.id.rb_f2:

                break;
            case R.id.rb_f3:

                break;
            case R.id.rb_f4:

                break;
        }
    }
}
