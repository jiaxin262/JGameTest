
package com.jia.jason.jgametest.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
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
        container.setIntercept(true);

        BottomLayer bottomLayer = new BottomLayer(this);
        Button button2 = new Button(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "点击上面...的按钮");
            }
        });
        button.setText("InterceptableButton");
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onTouch button");
                return false;
            }
        });
        bottomLayer.addView(button2);
        bottomLayer.setBackgroundColor(getResources().getColor(R.color.blue));
        RelativeLayout.LayoutParams bottomParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        container.addView(bottomLayer, bottomParams);
    }

    private class BottomLayer extends FrameLayout {

        private GestureDetector mGestureDetector;

        public BottomLayer(Context context) {
            super(context);
            mGestureDetector = new GestureDetector(getContext(), new GestureDetectorListener(this));
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            mGestureDetector.onTouchEvent(ev);
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    getParent().requestDisallowInterceptTouchEvent(true);
                    Log.d(TAG, "down");
                    break;
                case MotionEvent.ACTION_UP:
                    Log.d(TAG, "up");
                    break;
                case MotionEvent.ACTION_CANCEL:
                    Log.d(TAG, "cancel");
                    break;
            }
            return super.onInterceptTouchEvent(ev);
        }

    }

    class GestureDetectorListener extends GestureDetector.SimpleOnGestureListener {

        ViewGroup viewGroup;

        public GestureDetectorListener(ViewGroup viewGroup) {
            this.viewGroup = viewGroup;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            Log.d(TAG, "onDown");
            return super.onDown(e);
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.d(TAG, "onSingleTapUp");
            return super.onSingleTapUp(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d(TAG, "onScroll");
            viewGroup.getParent().requestDisallowInterceptTouchEvent(false);
            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.j_button) {
//            Log.d(TAG, "点击底下的按钮");
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Log.d(TAG, "onTouchEvent");
        return true;
    }
}
