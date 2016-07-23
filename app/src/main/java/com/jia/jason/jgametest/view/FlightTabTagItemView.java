package com.jia.jason.jgametest.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jia.jason.jgametest.R;
import com.jia.jason.jgametest.helper.BitmapHelper;

/**
 * Created by meng.jiang on 2015/8/5.
 */
public class FlightTabTagItemView extends LinearLayout {
    private View tabCursor;
    protected Context mContext;
    private int index;

    private final boolean isCheck = false;
    private TextView tvTabName;
    private int layoutTagId;
    private View layout;
    private ColorStateList selectedTextColor = null;
    private ColorStateList normalTextColor = null;

    private boolean isShowRed = false;

    public FlightTabTagItemView(Context context, String nickName, int orientation, float textSize,
                                ColorStateList normalTextColor, ColorStateList selectedTextColor) {
        this(context, null);
        this.mContext = context;
        this.selectedTextColor = selectedTextColor;
        this.normalTextColor = normalTextColor;
        this.setOrientation(orientation);
        if (HORIZONTAL == orientation) {
            LayoutInflater.from(getContext()).inflate(R.layout.atom_flight_otatab_corner_item_horizontal, this);
        } else {
            LayoutInflater.from(getContext()).inflate(R.layout.atom_flight_otatab_corner_item_vertical, this);
        }

        tabCursor = findViewById(R.id.atom_flight_tabcursor);
        tvTabName = (TextView) this.findViewById(R.id.atom_flight_tvTabName);
        tvTabName.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        tvTabName.setTextColor(normalTextColor);


    }

    public FlightTabTagItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void updateSelectState(boolean isSelected) {
        if (isSelected) {
            //TODO:
            tabCursor.setVisibility(View.VISIBLE);
            layout.setVisibility(View.VISIBLE);
            tvTabName.setTextColor(selectedTextColor);
        } else {
            //TODO:
            tabCursor.setVisibility(View.INVISIBLE);
            if (layout != null) {
                layout.setVisibility(View.INVISIBLE);
            }
            tvTabName.setTextColor(normalTextColor);
        }
    }

    private Paint mRedPaint;

    private int r = BitmapHelper.dip2px(5);
    private int padding = BitmapHelper.dip2px(2);

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if(isShowRed){
            if(mRedPaint == null){
                mRedPaint = new Paint();
                mRedPaint.setAntiAlias(true);
                mRedPaint.setColor(Color.RED);
            }

            canvas.drawCircle(getWidth() - r - padding, r + padding, r, mRedPaint);
        }
    }

    public void setBackgroundRes(int normalRdsId, int pressedResId, boolean isSelect) {
        updateSelectState(isSelect);
    }

    public void setBackgroundRes(Drawable normalDrawable, Drawable selectDrawable, boolean isSelect) {
        updateSelectState(isSelect);
    }

    public void setContext(Context context) {
        this.mContext = context;
    }

    public int getLayoutTagId() {
        return this.layoutTagId;
    }

    public void setLayoutTagId(int layoutTagId) {
        this.layoutTagId = layoutTagId;
    }

    /**
     * 设置tab的内容
     * @param tabName
     */
    public void setTabName(CharSequence tabName) {
        tvTabName.setText(tabName);
    }

    public boolean isCheck() {
        return this.isCheck;
    }

    public View getLayout() {
        return layout;
    }

    public void setLayout(View layout) {
        this.layout = layout;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}