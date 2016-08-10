package com.jia.jason.jgametest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jia.jason.jgametest.R;

/**
 * Created by xin.jia
 * since 2016/8/9
 */
public class Fragment1 extends Fragment {

    TextView tvFragment;
    LinearLayout llFragment;
    ReplyListener listener;

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
