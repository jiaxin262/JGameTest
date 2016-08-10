package com.jia.jason.jgametest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jia.jason.jgametest.R;
import com.jia.jason.jgametest.adapter.ViewsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xin.jia
 * since 2016/8/9
 */
public class Fragment1 extends Fragment {

    TextView tvFragment;
    LinearLayout llFragment;
    ViewPager viewPager;
    PagerTabStrip tabs;

    ReplyListener listener;

    List<View> viewList = new ArrayList<>();
    PagerAdapter pagerAdapter;
    List<String> titleList = new ArrayList<>();

    public interface ReplyListener {
        public void reply(String string);
    }

    public void setListener(ReplyListener listener) {
        this.listener = listener;
    }

    //每次创建Fragment都会重新绘制View
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("Fragment1:", " onCreateView");
        View view = inflater.inflate(R.layout.fragment_test_layotu, container, false);
        tvFragment = (TextView) view.findViewById(R.id.fragment_tv);
        llFragment = (LinearLayout) view.findViewById(R.id.ll_fragment);
        viewPager = (ViewPager) view.findViewById(R.id.vp_fragments);
        tabs = (PagerTabStrip) view.findViewById(R.id.vp_pagerTabs);
        return view;
    }

    //当Fragment所在Activity启动完成后调用
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("Fragment1:", " onActivityCreated");
        tvFragment.setText("This is Fragment1");
        getDataFromActivity(getArguments());
        listener.reply("activity,fragment已接收到信息");

        titleList.add("First");
        titleList.add("Sec");
        titleList.add("Third");
        titleList.add("Fourth");
        View view1 = View.inflate(getActivity(), R.layout.view_pager_view1, null);
        View view2 = View.inflate(getActivity(), R.layout.view_pager_view2, null);
        View view3 = View.inflate(getActivity(), R.layout.view_pager_view3, null);
        View view4 = View.inflate(getActivity(), R.layout.view_pager_view4, null);
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);

        pagerAdapter = new ViewsAdapter(viewList, titleList);
        viewPager.setAdapter(pagerAdapter);

        tabs.setBackgroundColor(getResources().getColor(R.color.atom_flight_text_gray_trans));
        tabs.setDrawFullUnderline(false);
        tabs.setTabIndicatorColor(getResources().getColor(R.color.red));
        tabs.setTextColor(getResources().getColor(R.color.red));
    }

    private void getDataFromActivity(Bundle bundle) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        params.topMargin = 20;
        String text = bundle.getString("text", "没有数据");
        TextView textView = new TextView(getActivity());
        textView.setText(text);
        llFragment.addView(textView, params);
    }

    //Fragment被添加到Activity时调用，只调用一次
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("Fragment1:", " onAttach");
    }

    //创建Fragment时调用，只调用一次
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Fragment1:", " onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("Fragment1:", " onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("Fragment1:", " onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("Fragment1:", " onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("Fragment1:", " onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("Fragment1:", " onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Fragment1:", " onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("Fragment1:", " onDetach");
    }
}
