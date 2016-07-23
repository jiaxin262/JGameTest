package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jia.jason.jgametest.R;
import com.jia.jason.jgametest.adapter.JListViewAdapter;
import com.jia.jason.jgametest.helper.BitmapHelper;
import com.jia.jason.jgametest.model.JListItem;
import com.jia.jason.jgametest.view.ListViewContainer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xin.jia
 * since 2016/3/2
 */
public class ListViewsActivity extends BaseActivity {

    private List<JListItem> itemList = new ArrayList<>();
    private JListViewAdapter topAdapter;
    private JListViewAdapter bottomAdapter;
    public ListView topListView;
    public ListView bottomListView;
    public ListViewContainer listViewContainer;
    public LinearLayout continuePullRelease;
    public TextView continuePullTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_views);

        itemList = JListItem.mockItems(20);
        topAdapter = new JListViewAdapter(this, itemList);
        itemList = JListItem.mockItems2(20);
        bottomAdapter = new JListViewAdapter(this, itemList);

        listViewContainer = (ListViewContainer) findViewById(R.id.list_view_container);
        topListView = (ListView) findViewById(R.id.top_list_view);
        bottomListView = (ListView) findViewById(R.id.bottom_list_view);
        continuePullRelease = (LinearLayout) findViewById(R.id.continue_pull_release);
        continuePullTv = (TextView) findViewById(R.id.atom_flight_tv_continue_pull);
        final View continuePullView = getLayoutInflater().inflate(R.layout.atom_flight_ota_continue_pull_item2, null);

        topListView.post(new Runnable() {
            @Override
            public void run() {
                int lastVisiblePosition = topListView.getLastVisiblePosition();
                int lastItemBottomPos = 0;
                if (topListView.getChildAt(lastVisiblePosition) != null) {
                    lastItemBottomPos = topListView.getChildAt(lastVisiblePosition).getBottom();
                }
                if (lastItemBottomPos + BitmapHelper.dip2px(50) >=
                        listViewContainer.getBottom() - listViewContainer.getTop()) {   //一屏显示不完
                    continuePullRelease.setVisibility(View.GONE);
                    topListView.addFooterView(continuePullView);
                    listViewContainer.setShowPullType(listViewContainer.SHOW_OUT_ONE_SCREEN);
                } else {
                    continuePullRelease.setVisibility(View.VISIBLE);
                    listViewContainer.setShowPullType(listViewContainer.SHOW_IN_ONE_SCREEN);
                }
            }
        });

        topListView.setAdapter(topAdapter);
        bottomListView.setAdapter(bottomAdapter);

        listViewContainer.setContinuePullTv(continuePullTv);
        listViewContainer.setContinuePullFooterView((TextView)continuePullView.findViewById(R.id.atom_flight_tv_continue_pull));

        topListView.setOnScrollListener(listViewContainer.getOnScrollListener(0));
        bottomListView.setOnScrollListener(listViewContainer.getOnScrollListener(1));
    }
}
