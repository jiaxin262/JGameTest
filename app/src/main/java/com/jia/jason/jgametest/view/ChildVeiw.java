package com.jia.jason.jgametest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jia.jason.jgametest.R;

/**
 * Created by jiaxin on 2016/11/29.
 */

public class ChildVeiw extends View {

    Context context;
    LinearLayout ll;
    ViewGroup rootView;
    TextView tv;

    public ChildVeiw(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChildVeiw(Context context, ViewGroup rootView) {
        super(context);
        this.context = context;
        this.rootView = rootView;
        initView();
    }

    public ChildVeiw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        ll = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.child_view_layout, rootView, false);
        rootView.addView(ll);
        tv = (TextView) ll.findViewById(R.id.keyword_2);
        tv.setText("");
        tv.setText("vvvvv");
        tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.atom_flight_segmented_button_left));
    }


}
