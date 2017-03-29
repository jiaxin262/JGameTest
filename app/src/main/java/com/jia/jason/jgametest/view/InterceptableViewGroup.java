
package com.jia.jason.jgametest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class InterceptableViewGroup extends RelativeLayout {
    public static final String TAG = "InterceptableViewGroup";

    private boolean intercept = false;

    public InterceptableViewGroup(Context context) {
        super(context);
    }

    public InterceptableViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptableViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, "onInterceptTouchEvent");
        if (intercept) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public void setIntercept(boolean intercept) {
        this.intercept = intercept;
    }
}
