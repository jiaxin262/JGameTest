package com.jia.jason.jgametest.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jia.jason.jgametest.R;
import com.jia.jason.jgametest.adapter.JListViewAdapter;
import com.jia.jason.jgametest.model.JListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xin.jia
 * since 2016/3/4
 */
public class ListViewAndOtherActivity extends BaseActivity implements AbsListView.OnScrollListener{

    private Context context = this;
    private ListView listView;
    private List<JListItem> itemList = new ArrayList<>();
    private JListViewAdapter adapter;
    private LinearLayout continuePullView2;
    private boolean canPullUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_and_other);

        listView = (ListView) findViewById(R.id.lv_and_other);
        itemList = JListItem.mockItems();
        adapter = new JListViewAdapter(context, itemList);

        continuePullView2 = (LinearLayout) findViewById(R.id.atom_flight_ota_continue_pull_layout);
        final View continuePullView = getLayoutInflater().inflate(R.layout.atom_flight_ota_continue_pull_item2, null);
        listView.post(new Runnable() {
            @Override
            public void run() {
                int numItemVisible = listView.getLastVisiblePosition() - listView.getFirstVisiblePosition();
                if (adapter.getCount() - 1 > numItemVisible) {
                    listView.addFooterView(continuePullView);
                } else {
                    continuePullView2.setVisibility(View.VISIBLE);
                    canPullUp = true;
                }
            }
        });
        listView.setAdapter(adapter);
        //listView.setOnScrollListener(this);

    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        switch(view.getId()) {
            case R.id.lv_and_other:
                final int lastItem = firstVisibleItem + visibleItemCount;
                if(lastItem == totalItemCount) {
                    canPullUp = true;
                } else {
                    canPullUp = false;
                }
        }
    }
}
