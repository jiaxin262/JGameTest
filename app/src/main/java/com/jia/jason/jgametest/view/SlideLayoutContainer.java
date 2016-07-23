package com.jia.jason.jgametest.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.jia.jason.jgametest.activity.Action;

/**
 * 第一个ListView滑到底部后，继续拖动，滑到第二个ListView;
 * 在第二个ListView中下拉可回到第一个ListView;
 * Created by xin.jia
 * since 2016/3/7
 */
public class SlideLayoutContainer extends RelativeLayout{

    /**
     * 自动上滑
     */
    private static final int AUTO_UP = 0;
    /**
     * 自动下滑
     */
    private static final int AUTO_DOWN = 1;
    /**
     * 动画完成
     */
    private static final int DONE = 2;

    private int animationDuration = 800;
    private boolean isMeasured = false;
    public int mViewHeight;
    private int mViewWidth;

    private boolean canPullDown;
    private boolean canPullUp;
    private int state = DONE;
    private int mCurrentViewIndex;
    public float mMoveLen;
    private boolean canTopViewShowFully;    // 0-列表不足一屏， 1-列表超出一屏
    private boolean isAnimating;
    private boolean isItemSelect;    //屏蔽切换tab时触发的onScrollListener
    private int viewType;   //0-ListView, 1-ScrollView, 2-Layout

    private Context context;
    private GestureDetectorCompat gestureDetectorCompat;
    private ValueAnimator valueAnimator;
    private View topView;
    private View centerView;
    private View bottomView;
    private Action<Integer> actionAfterAnim;

    public SlideLayoutContainer(Context context) {
        super(context);
        init(context);
    }

    public SlideLayoutContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SlideLayoutContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        gestureDetectorCompat = new GestureDetectorCompat(context, new MyOnGestureDetector());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!isMeasured) {
            isMeasured = true;
            mViewHeight = getMeasuredHeight();
            mViewWidth = getMeasuredWidth();

            switch (getChildCount()) {
                case 0:
                    break;
                case 1:
                    topView = getChildAt(0);
                    break;
                case 2:
                    topView = getChildAt(0);
                    bottomView = getChildAt(1);
                    break;
                case 3:
                    topView = getChildAt(0);
                    centerView = getChildAt(1);
                    bottomView = getChildAt(2);
                    break;
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (topView != null) {
            topView.layout(0, (int) mMoveLen, mViewWidth, topView.getMeasuredHeight() + (int) mMoveLen);
            if (canTopViewShowFully) {
                if (centerView != null) {
                    centerView.layout(0, getHeight() - centerView.getMeasuredHeight() + (int) mMoveLen,
                            mViewWidth, getHeight() + (int) mMoveLen);
                }
                if (bottomView != null) {
                    bottomView.layout(0, getHeight() + (int) mMoveLen, mViewWidth,
                            getHeight() + (int) mMoveLen + bottomView.getMeasuredHeight());
                }
            } else {
                if (centerView.getVisibility() == GONE){
                    bottomView.layout(0, topView.getMeasuredHeight() + (int) mMoveLen,
                            mViewWidth, topView.getMeasuredHeight() + (int) mMoveLen + bottomView.getMeasuredHeight());
                } else {
                    centerView.layout(0, topView.getMeasuredHeight() + (int) mMoveLen,
                            mViewWidth, topView.getMeasuredHeight() + (int) mMoveLen + centerView.getMeasuredHeight());
                    bottomView.layout(0, topView.getMeasuredHeight() + (int) mMoveLen + centerView.getMeasuredHeight(),
                            mViewWidth, topView.getMeasuredHeight() + (int) mMoveLen + centerView.getMeasuredHeight() + bottomView.getMeasuredHeight());
                }
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        this.gestureDetectorCompat.onTouchEvent(ev);
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_UP:
                if (isAnimating || mMoveLen == 0 || mMoveLen == -mViewHeight) {
                    break;
                }
                setState((mMoveLen <= -mViewHeight / 2) ? AUTO_UP : AUTO_DOWN);
                executeAnimation(mMoveLen);
                break;
        }
        super.dispatchTouchEvent(ev);
        return true;
    }

    private class MyOnGestureDetector extends GestureDetector.SimpleOnGestureListener {

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
                if (mMoveLen < -mViewHeight) {
                    mMoveLen = -mViewHeight;
                    mCurrentViewIndex = 1;
                } else if (mMoveLen > 0) {
                    mMoveLen = 0;
                    mCurrentViewIndex = 0;
                }
                if (mMoveLen > ViewConfiguration.get(context).getScaledTouchSlop() - mViewHeight) {
                    e2.setAction(MotionEvent.ACTION_CANCEL);
                }
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
                setState((mMoveLen <= -mViewHeight / 2) ? AUTO_UP : AUTO_DOWN);
            } else {
                // 抬起手指时速度方向决定两个View往哪移动
                setState(velocityY < 0 ? AUTO_UP : AUTO_DOWN);
            }
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
            valueAnimator.setDuration(animationDuration);
            valueAnimator.setInterpolator(new DecelerateInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mMoveLen = (float) valueAnimator.getAnimatedValue();
                    if (mMoveLen == -mViewHeight) {
                        state = DONE;
                        mCurrentViewIndex = 1;
                        if (actionAfterAnim != null) {
                            actionAfterAnim.execute(1);
                        }
                    } else if (mMoveLen == 0) {
                        state = DONE;
                        mCurrentViewIndex = 0;
                        if (actionAfterAnim != null) {
                            actionAfterAnim.execute(0);
                        }
                    }
                    requestLayout();
                }
            });
            valueAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    isAnimating = true;
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    isAnimating = false;
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    isAnimating = false;
                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            valueAnimator.start();
        }
    }

    public AbsListView.OnScrollListener getOnScrollListener(int layer) {
        return layer == 0 ? new TopViewScrollListener() : new BottomViewScrollListener();
    }

    public OnTouchListener getOnTouchListener() {
        return new ViewTouchListener();
    }

    public void setViewHeight(int viewHeight) {
        mViewHeight = viewHeight;
    }

    public int getViewHeight() {
        return mViewHeight;
    }

    public void setCurrentViewIndex(int index) {
        mCurrentViewIndex = index;
    }

    public int getCurrentViewIndex() {
        return mCurrentViewIndex;
    }

    public void setMoveLen(float mMoveLen) {
        this.mMoveLen = mMoveLen;
    }

    public float getMoveLen() {
        return mMoveLen;
    }

    public void setCanPullUp(boolean canPullUp) {
        this.canPullUp = canPullUp;
    }

    public void setCanPullDown(boolean canPullDown) {
        this.canPullDown = canPullDown;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setIsItemSelect(boolean isItemSelect){
        this.isItemSelect = isItemSelect;
    }

    public void setCanTopViewShowFully(boolean canShowFully) {
        this.canTopViewShowFully = canShowFully;
    }

    public void setActionAfterAnim(Action action) {
        actionAfterAnim = action;
    }

    public void setAnimationDuration(int duration) {
        animationDuration = duration;
    }

    public boolean isAnimating() {
        return isAnimating;
    }

    public class TopViewScrollListener implements AbsListView.OnScrollListener{

        boolean isLastItemFullyVisible;
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

    public class BottomViewScrollListener implements AbsListView.OnScrollListener{

        boolean isFirstItemFullyVisible;

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (isItemSelect) {
                isItemSelect = false;
            } else {
                if (view.getChildAt(firstVisibleItem) != null) {
                    isFirstItemFullyVisible = view.getChildAt(firstVisibleItem).getTop() == 0;
                }
                canPullDown = firstVisibleItem == 0 && isFirstItemFullyVisible;
            }
        }
    }

    public class ViewTouchListener implements OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            ScrollView sv = (ScrollView) v;
            if (mCurrentViewIndex == 0) {
                canPullUp = sv.getScrollY() == (sv.getChildAt(0).getMeasuredHeight() - sv.getMeasuredHeight());
            } else if (mCurrentViewIndex == 1) {
                canPullDown = sv.getScrollY() == 0;
            }
            return false;
        }
    }

}
