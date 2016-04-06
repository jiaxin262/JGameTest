package com.jia.jason.jgametest.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by xin.jia
 * since 2016/3/2
 */
public class ListViewContainer extends RelativeLayout {

    /**
     * 自动上滑
     */
    public static final int AUTO_UP = 0;
    /**
     * 自动下滑
     */
    public static final int AUTO_DOWN = 1;
    /**
     * 动画完成
     */
    public static final int DONE = 2;
    /**
     * 动画速度
     */
    //public static final float SPEED = 8.5f;

    private boolean isMeasured = false;

    /**
     * 用于计算手滑动的速度
     */
    //private VelocityTracker vt;
    private int mViewHeight;
    private int mViewWidth;

    private View topView;
    private View centerVeiw;
    private View bottomView;

    private boolean canPullDown;
    private boolean canPullUp;
    private int state = DONE;
    private int mCurrentViewIndex = 0;

    private float mMoveLen;
    //private MyTimer mTimer;
    private float mLastY;
    private int mEvents;
    private Context context;
    private GestureDetectorCompat gestureDetectorCompat;
    private ValueAnimator valueAnimator;

//    private Handler handler = new Handler() {
//
//        @Override
//        public void handleMessage(Message msg) {
//            if (mMoveLen != 0) {
//                if (state == AUTO_UP) {
//                    mMoveLen -= SPEED;
//                    if (mMoveLen <= -mViewHeight) {
//                        mMoveLen = -mViewHeight;
//                        state = DONE;
//                        mCurrentViewIndex = 1;
//                        continuePullTv.setText("释放，返回组合特价包");
//                        continuePullFooterView.setText("释放，返回组合特价包");
//                    }
//                } else if (state == AUTO_DOWN) {
//                    mMoveLen += SPEED;
//                    if (mMoveLen >= 0) {
//                        mMoveLen = 0;
//                        state = DONE;
//                        mCurrentViewIndex = 0;
//                        continuePullTv.setText("继续拖动，分开购买省180元");
//                        continuePullFooterView.setText("继续拖动，分开购买省180元");
//                    }
//                } else {
//                    mTimer.cancel();
//                }
//                requestLayout();
//            }
//        }
//
//    };

    public TextView continuePullTv;
    public TextView continuePullFooterView;
    public int showPullType;    // 0-列表不足一屏， 1-列表超出一屏
    public final int SHOW_IN_ONE_SCREEN = 0;
    public final int SHOW_OUT_ONE_SCREEN = 1;

    public ListViewContainer(Context context) {
        super(context);
        init(context);
    }

    public ListViewContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ListViewContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        //mTimer = new MyTimer(handler);
        this.context = context;
        gestureDetectorCompat = new GestureDetectorCompat(context, new MyOnGestureDetector());
    }

    public void setContinuePullTv(TextView continuePullTv) {
        this.continuePullTv = continuePullTv;
    }

    public void setContinuePullFooterView(TextView continuePullFooterView) {
        this.continuePullFooterView = continuePullFooterView;
    }

    public AbsListView.OnScrollListener getOnScrollListener(int layer) {
        return layer == 0 ? new TopOnScrollListener() : new BottomScrollListener();
    }

    public void setShowPullType(int showPullType) {
        this.showPullType = showPullType;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        this.gestureDetectorCompat.onTouchEvent(ev);
        super.dispatchTouchEvent(ev);
        return true;
    }

    private class MyOnGestureDetector extends SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            //distanceY  上拉为正，下拉为负
            if (canPullUp && mCurrentViewIndex == 0) {
                mMoveLen -= distanceY;
                // 防止上下越界
                if (mMoveLen > 0) {
                    mMoveLen = 0;
                    mCurrentViewIndex = 0;
                } else if (mMoveLen < -mViewHeight) {
                    mMoveLen = -mViewHeight;
                    mCurrentViewIndex = 1;
                }
                if (mMoveLen < -ViewConfiguration.get(context).getScaledTouchSlop()) {
                    // 防止事件冲突
                    e2.setAction(MotionEvent.ACTION_CANCEL);
                }
            } else if (canPullDown && mCurrentViewIndex == 1) {
                mMoveLen -= distanceY;
                // 防止上下越界
                if (mMoveLen < -mViewHeight) {
                    mMoveLen = -mViewHeight;
                    mCurrentViewIndex = 1;
                } else if (mMoveLen > 0) {
                    mMoveLen = 0;
                    mCurrentViewIndex = 0;
                }
                if (mMoveLen > ViewConfiguration.get(context).getScaledTouchSlop() - mViewHeight) {
                    // 防止事件冲突
                    e2.setAction(MotionEvent.ACTION_CANCEL);
                }
            } else {
                mEvents++;
            }
            requestLayout();
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (mMoveLen == 0 || mMoveLen == -mViewHeight) {
                return true;
            }
            //velocityY < 0 上滑， > 0 下滑
            if (Math.abs(velocityY) < 500) {
                // 速度小于一定值的时候当作静止释放，这时候两个View往哪移动取决于滑动的距离
                if (mMoveLen <= -mViewHeight / 2) {
                    state = AUTO_UP;
                } else if (mMoveLen > -mViewHeight / 2) {
                    state = AUTO_DOWN;
                }
            } else {
                // 抬起手指时速度方向决定两个View往哪移动
                if (velocityY < 0) {
                    state = AUTO_UP;
                } else {
                    state = AUTO_DOWN;
                }
            }
            //mTimer.schedule(2);
            executeAnimation(mMoveLen);
            return true;
        }
    }

    private void executeAnimation(float startYPos) {
        if (mMoveLen != 0) {
            if (state == AUTO_UP) {
                valueAnimator = ValueAnimator.ofFloat(startYPos, -mViewHeight);
            } else if (state == AUTO_DOWN) {
                valueAnimator = ValueAnimator.ofFloat(startYPos, 0);
            } else {
                return;
            }
            valueAnimator.setDuration(700);
            //valueAnimator.setInterpolator(new BounceInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mMoveLen = (float) valueAnimator.getAnimatedValue();
                    if (mMoveLen == -mViewHeight) {
                        state = DONE;
                        mCurrentViewIndex = 1;
                        continuePullTv.setText("释放，返回组合特价包");
                        continuePullFooterView.setText("释放，返回组合特价包");
                    } else if (mMoveLen == 0) {
                        state = DONE;
                        mCurrentViewIndex = 0;
                        continuePullTv.setText("继续拖动，分开购买省180元");
                        continuePullFooterView.setText("继续拖动，分开购买省180元");
                    }
                    requestLayout();
                }
            });
            valueAnimator.start();
        }
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        switch (ev.getActionMasked()) {
//            case MotionEvent.ACTION_DOWN:
//                if (vt == null)
//                    vt = VelocityTracker.obtain();
//                else
//                    vt.clear();
//                mLastY = ev.getY();
//                vt.addMovement(ev);
//                mEvents = 0;
//                break;
//            case MotionEvent.ACTION_POINTER_DOWN:
//            case MotionEvent.ACTION_POINTER_UP:
//                // 多一只手指按下或抬起时舍弃将要到来的第一个事件move，防止多点拖拽的bug
//                mEvents = -1;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                vt.addMovement(ev);
//                if (canPullUp && mCurrentViewIndex == 0 && mEvents == 0) {
//                    mMoveLen += (ev.getY() - mLastY);
//                    // 防止上下越界
//                    if (mMoveLen > 0) {
//                        mMoveLen = 0;
//                        mCurrentViewIndex = 0;
//                    } else if (mMoveLen < -mViewHeight) {
//                        mMoveLen = -mViewHeight;
//                        mCurrentViewIndex = 1;
//
//                    }
//                    if (mMoveLen < -8) {
//                        // 防止事件冲突
//                        ev.setAction(MotionEvent.ACTION_CANCEL);
//                    }
//                } else if (canPullDown && mCurrentViewIndex == 1 && mEvents == 0) {
//                    mMoveLen += (ev.getY() - mLastY);
//                    // 防止上下越界
//                    if (mMoveLen < -mViewHeight) {
//                        mMoveLen = -mViewHeight;
//                        mCurrentViewIndex = 1;
//                    } else if (mMoveLen > 0) {
//                        mMoveLen = 0;
//                        mCurrentViewIndex = 0;
//                    }
//                    if (mMoveLen > 8 - mViewHeight) {
//                        // 防止事件冲突
//                        ev.setAction(MotionEvent.ACTION_CANCEL);
//                    }
//                } else
//                    mEvents++;
//                mLastY = ev.getY();
//                requestLayout();
//                break;
//            case MotionEvent.ACTION_UP:
//                mLastY = ev.getY();
//                vt.addMovement(ev);
//                vt.computeCurrentVelocity(700);
//                // 获取Y方向的速度
//                float mYV = vt.getYVelocity();
//                if (mMoveLen == 0 || mMoveLen == -mViewHeight)
//                    break;
//                if (Math.abs(mYV) < 500) {
//                    // 速度小于一定值的时候当作静止释放，这时候两个View往哪移动取决于滑动的距离
//                    if (mMoveLen <= -mViewHeight / 2) {
//                        state = AUTO_UP;
//                    } else if (mMoveLen > -mViewHeight / 2) {
//                        state = AUTO_DOWN;
//                    }
//                } else {
//                    // 抬起手指时速度方向决定两个View往哪移动
//                    if (mYV < 0)
//                        state = AUTO_UP;
//                    else
//                        state = AUTO_DOWN;
//                }
//                mTimer.schedule(2);
//                try {
//                    vt.recycle();
//                    vt = null;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                break;
//
//        }
//        super.dispatchTouchEvent(ev);
//        return true;
//    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        topView.layout(0, (int) mMoveLen, mViewWidth, topView.getMeasuredHeight() + (int) mMoveLen);
        if (showPullType == SHOW_IN_ONE_SCREEN) {
            centerVeiw.layout(0, getHeight() - centerVeiw.getMeasuredHeight() + (int) mMoveLen,
                    mViewWidth, getHeight() + (int) mMoveLen);
            bottomView.layout(0, getHeight() + (int) mMoveLen, mViewWidth,
                    getHeight() + (int) mMoveLen + bottomView.getMeasuredHeight());
        } else if (showPullType == SHOW_OUT_ONE_SCREEN) {
            bottomView.layout(0, topView.getMeasuredHeight() + (int) mMoveLen,
                    mViewWidth, topView.getMeasuredHeight() + (int) mMoveLen + bottomView.getMeasuredHeight());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!isMeasured) {
            isMeasured = true;

            mViewHeight = getMeasuredHeight();
            mViewWidth = getMeasuredWidth();

            topView = getChildAt(0);
            centerVeiw = getChildAt(1);
            bottomView = getChildAt(2);

        }
    }

    public class TopOnScrollListener implements AbsListView.OnScrollListener{

        boolean isLastItemFullyVisible = false;
        int lastItem;
        View lastItemView;

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            lastItem = firstVisibleItem + visibleItemCount;
            lastItemView = view.getChildAt(view.getChildCount() - 1);
            if (lastItemView != null) {
                isLastItemFullyVisible = lastItemView.getBottom() <= (mViewHeight == 0 ? getResources().getDisplayMetrics().heightPixels : mViewHeight);
            }
            canPullUp = lastItem == totalItemCount && isLastItemFullyVisible;
        }
    }

    public class BottomScrollListener implements AbsListView.OnScrollListener{

        boolean isFirstItemFullyVisible = false;

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (view.getChildAt(firstVisibleItem) != null) {
                isFirstItemFullyVisible = view.getChildAt(firstVisibleItem).getTop() == 0;
            }
            canPullDown = firstVisibleItem == 0 && isFirstItemFullyVisible;
        }
    }

//    class MyTimer {
//        private Handler handler;
//        private Timer timer;
//        private MyTask mTask;
//
//        public MyTimer(Handler handler) {
//            this.handler = handler;
//            timer = new Timer();
//        }
//
//        public void schedule(long period) {
//            if (mTask != null) {
//                mTask.cancel();
//                mTask = null;
//            }
//            mTask = new MyTask(handler);
//            timer.schedule(mTask, 0, period);
//        }
//
//        public void cancel() {
//            if (mTask != null) {
//                mTask.cancel();
//                mTask = null;
//            }
//        }
//
//        class MyTask extends TimerTask {
//            private Handler handler;
//
//            public MyTask(Handler handler) {
//                this.handler = handler;
//            }
//
//            @Override
//            public void run() {
//                handler.obtainMessage().sendToTarget();
//            }
//
//        }
//    }

}
