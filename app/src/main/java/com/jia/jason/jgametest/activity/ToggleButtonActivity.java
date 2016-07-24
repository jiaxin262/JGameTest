package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.jia.jason.jgametest.R;

/**
 * Created by xin.jia
 * since 2016/7/24
 */
public class ToggleButtonActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    private ToggleButton toggleButton;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toggle_button_layout);

        toggleButton = (ToggleButton) findViewById(R.id.tg_toggleButton);
        imageView = (ImageView) findViewById(R.id.tg_imageView);

        toggleButton.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == toggleButton) {
            imageView.setBackgroundResource(isChecked ? R.drawable.light_on : R.drawable.light_off);
        }
    }
}
