
package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jia.jason.jgametest.R;
import com.jia.jason.jgametest.view.InterceptableViewGroup;

public class InterceptTestActivity extends BaseActivity {
    public static final String TAG = "InterceptTestActivity";

    Button button;
    InterceptableViewGroup container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intercept_layout);

        button = (Button) findViewById(R.id.j_button);
        button.setOnClickListener(this);

        container = (InterceptableViewGroup) findViewById(R.id.j_container);
        TextView textView = new TextView(this);
        textView.setText("InterceptableViewGroup");
        container.addView(textView);
        Button button = new Button(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "点击上面的按钮");
            }
        });
        button.setText("InterceptableButton");
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        container.addView(button, params);
//        container.setIntercept(true);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.j_button) {
            Log.d(TAG, "点击底下的按钮");
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent");
        return true;
    }
}
