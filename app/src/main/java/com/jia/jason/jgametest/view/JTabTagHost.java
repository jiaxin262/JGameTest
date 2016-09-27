package com.jia.jason.jgametest.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.jia.jason.jgametest.R;

import java.util.ArrayList;

public class JTabTagHost extends LinearLayout {
    private static final int DEFAULT_TEXT_SIZE_DP = 18;
    private Context mContext = null;
    private ArrayList<JTabTagItemView> itemList = null;
    private int currentIndex = 0;
    private ColorStateList selectedTextColor = null;
    private ColorStateList normalTextColor = null;
    private float textSize = 0.0f;
    private int bodyLayoutId = -1;
    private QOnSelectedItemListener listener;

    // added by Chaos
    private Drawable mLeftSelectedDrawable = null;
    private Drawable mCenterSelectedDrawable = null;
    private Drawable mRightSelectedDrawable = null;

    private Drawable mLeftNormalDrawable = null;
    private Drawable mCenterNormalDrawable = null;
    private Drawable mRightNormalDrawable = null;


    public int getbodyLayoutId() {
        return this.bodyLayoutId;
    }

    public void setbodyLayoutId(int bodylayoutTagId) {
        this.bodyLayoutId = bodylayoutTagId;
    }

    public JTabTagHost(Context context) {
        this(context, null);
    }

    public JTabTagHost(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        itemList = new ArrayList<JTabTagItemView>();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.atom_flight_tabCornerHost);
        textSize = a.getDimensionPixelSize(R.styleable.atom_flight_tabCornerHost_android_textSize,
                (int) dip2px(JTabTagHost.DEFAULT_TEXT_SIZE_DP));

        ColorStateList selectedColor = a.getColorStateList(R.styleable.atom_flight_tabCornerHost_atom_flight_selectedTextColor);
        ColorStateList normalColor = a.getColorStateList(R.styleable.atom_flight_tabCornerHost_atom_flight_normalTextColor);
        selectedTextColor = selectedColor == null ? ColorStateList.valueOf(Color.parseColor(getResources().getString(
                R.color.j_bg_gray))) : selectedColor;
        normalTextColor = normalColor == null ? ColorStateList.valueOf(Color.BLACK) : normalColor;

        mLeftNormalDrawable = a.getDrawable(R.styleable.atom_flight_tabCornerHost_atom_flight_leftNormalBackground);
        mCenterNormalDrawable = a.getDrawable(R.styleable.atom_flight_tabCornerHost_atom_flight_centerNormalBackground);
        mRightNormalDrawable = a.getDrawable(R.styleable.atom_flight_tabCornerHost_atom_flight_rightNormalBackground);
        mLeftSelectedDrawable = a.getDrawable(R.styleable.atom_flight_tabCornerHost_atom_flight_leftSelectedBackground);
        mCenterSelectedDrawable = a.getDrawable(R.styleable.atom_flight_tabCornerHost_atom_flight_centerSelectedBackground);
        mRightSelectedDrawable = a.getDrawable(R.styleable.atom_flight_tabCornerHost_atom_flight_rightSelectedBackground);

        a.recycle();
    }

    public void refershBackground() {
        if (itemList != null && itemList.size() > 0) {
            JTabTagItemView v = null;
            int startIndex = 0;
            int endIndex = 0;
            for (int i = 0; i < itemList.size(); i++) {
                v = itemList.get(i);

                if (v.getVisibility() == View.GONE && startIndex == v.getIndex()) {
                    startIndex++;
                    if (startIndex >= itemList.size()) {
                        startIndex = itemList.size() - 1;
                    }
                }

                if (v.getVisibility() == View.VISIBLE) {
                    endIndex = v.getIndex();
                }
            }

            for (int i = 0; i < itemList.size(); i++) {
                v = itemList.get(i);
                boolean isSelect = v.getIndex() == currentIndex;
                if (v.getIndex() == startIndex) {
                    if (mLeftNormalDrawable != null) {
                        v.setBackgroundRes(mLeftNormalDrawable, mLeftSelectedDrawable, isSelect);
                    } else {
                        v.setBackgroundRes(R.drawable.atom_flight_segmented_button_left, R.drawable.atom_flight_segmented_button_left_pressed,
                                isSelect);
                    }
                } else if (v.getIndex() == endIndex) {
                    if (mLeftNormalDrawable != null) {
                        v.setBackgroundRes(mRightNormalDrawable, mRightSelectedDrawable, isSelect);
                    } else {
                        v.setBackgroundRes(R.drawable.atom_flight_segmented_button_right,
                                R.drawable.atom_flight_segmented_button_right_pressed, isSelect);
                    }
                } else {
                    if (mLeftNormalDrawable != null) {
                        v.setBackgroundRes(mCenterNormalDrawable, mCenterSelectedDrawable, isSelect);
                    } else {
                        v.setBackgroundRes(R.drawable.atom_flight_segmented_button_center,
                                R.drawable.atom_flight_segmented_button_center_pressed, isSelect);
                    }
                }
            }
        }
    }

    public void addItem(final TabItem tabItem, final int cruTextSize) {
        this.textSize = cruTextSize;
        final JTabTagItemView siv = new JTabTagItemView(mContext, tabItem.nickName, this.getOrientation(),
                textSize, normalTextColor, selectedTextColor);
        siv.setTabName(tabItem.name);
        siv.setLayoutTagId(tabItem.layoutTagId);
        siv.setIndex(itemList.size());
        if (bodyLayoutId != -1) {
            View layoutBodyView = ((Activity) mContext).findViewById(bodyLayoutId);
            View v = tabItem.view ;
            v.setVisibility(View.GONE);
            siv.setLayout(v);
        }
        siv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex != siv.getIndex()) {
                    itemList.get(currentIndex).updateSelectState(false);
                    siv.updateSelectState(true);
                    currentIndex = siv.getIndex();
                    if (listener != null) {
                        listener.onItemSelected(siv, siv.getLayoutTagId());
                    }
                }
            }
        });

        this.addView(siv, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1));
        if (itemList.size() == 0) {
            siv.updateSelectState(true);
            currentIndex = 0;
        } else {
            siv.updateSelectState(false);
        }
        itemList.add(siv);
        refershBackground();
    }

    private float dip2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    public void setCurrentIndex(int index) {
        if (itemList != null) {
            for (int i = 0; i < itemList.size(); i++) {
                itemList.get(i).updateSelectState(false);
            }
            if( index >= itemList.size() ) {
                index = 0;
            }
            itemList.get(index).updateSelectState(true);
            if (listener != null) {
                listener.onItemSelected(itemList.get(index), itemList.get(index).getLayoutTagId());
            }
            currentIndex = index;
        }
    }

    public void setCurrentIndexButNotCallListener(int index){
        if (itemList != null) {
            for (int i = 0; i < itemList.size(); i++) {
                itemList.get(i).updateSelectState(false);
            }
            itemList.get(index).updateSelectState(true);
            currentIndex = index;
        }
    }

    public void setItemLabelByIndex(int index, CharSequence text) {
        itemList.get(index).setTabName(text);

    }

    public void setItemVisibleByIndex(int index, int visible) {
        itemList.get(index).setVisibility(visible);
        refershBackground();
    }

    public static class TabItem {
        public String nickName;
        public String name;
        public int layoutTagId;
        public View view;

        public TabItem(String name, String nickName, int layoutTagId,View view) {
            this.name = name;
            this.nickName = nickName;
            this.layoutTagId = layoutTagId;
            this.view = view;
        }
    }

    public interface QOnSelectedItemListener {
        public void onItemSelected(View v, int layoutTagId);
    }

    public void setSelectedListener(QOnSelectedItemListener listener) {
        this.listener = listener;
    }

}