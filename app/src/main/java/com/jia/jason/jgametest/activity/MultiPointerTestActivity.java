package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.jia.jason.jgametest.R;

/**
 * Created by jiaxin on 2016/12/5.
 */

public class MultiPointerTestActivity extends BaseActivity {

    public static final String TAG = "MultiPointerActivity";
    public static final String DISPATCH_TAG = "MultiPointerActivity";
    public static final String ONTOUCH_TAG = "MultiPointerActivity";

    int moveCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_pointer_layout);

    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.d(TAG, "dispatchTouchEvent");
//        int action = ev.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                Log.d(TAG, DISPATCH_TAG + "ACTION_DOWN");
//                break;
//            case MotionEvent.ACTION_POINTER_DOWN:
//                Log.d(TAG, DISPATCH_TAG + "ACTION_POINTER_DOWN");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                Log.d(TAG, DISPATCH_TAG + "ACTION_MOVE");
//                break;
//            case MotionEvent.ACTION_POINTER_UP:
//                Log.d(TAG, DISPATCH_TAG + "ACTION_POINTER_UP");
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.d(TAG, DISPATCH_TAG + "ACTION_UP");
//                break;
//            case MotionEvent.ACTION_CANCEL:
//                Log.d(TAG, DISPATCH_TAG + "ACTION_CANCEL");
//                break;
//        }
//        return super.dispatchTouchEvent(ev);
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Log.d(TAG, "onTouchEvent");
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, ONTOUCH_TAG + "ACTION_DOWN" + "-pointerID:" + event.getPointerId(0));

                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.d(TAG, ONTOUCH_TAG + "ACTION_POINTER_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                if (moveCount < 2) {
                    Log.d(TAG, ONTOUCH_TAG + "ACTION_MOVE");
                    moveCount++;
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                Log.d(TAG, ONTOUCH_TAG + "ACTION_POINTER_UP" + "-pointerID:" + event.getPointerId(0));
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, ONTOUCH_TAG + "ACTION_UP");
                moveCount = 0;
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, ONTOUCH_TAG + "ACTION_CANCEL");
                break;
            default:
                Log.d(TAG, event.getAction() + "," + event.getActionMasked());
        }
        return super.onTouchEvent(event);
    }
}
