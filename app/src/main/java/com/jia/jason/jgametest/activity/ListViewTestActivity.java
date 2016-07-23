package com.jia.jason.jgametest.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.jia.jason.jgametest.R;
import com.jia.jason.jgametest.adapter.JListViewAdapter;
import com.jia.jason.jgametest.model.JListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xin.jia
 * since 2016/3/2
 */
public class ListViewTestActivity extends BaseActivity implements AbsListView.OnScrollListener{

    private Context context = this;
    private ListView listView1;
    private List<JListItem> itemList = new ArrayList<>();
    private JListViewAdapter topAdapter;
    private View emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_test);

        SparseArray<JListItem> sMap = new SparseArray<>();
        JListItem item = new JListItem();
        item.text = "aaa";
        sMap.put(0, item);
        JListItem item1 = sMap.get(0);
        item1.text = "bbb";
        Log.e(" sMap.toString():", sMap.get(0).text);

        SparseArray<String> ssMap = new SparseArray<>();
        ssMap.put(0, "1111");
        String s = ssMap.get(0);
        s = "2222";
        Log.e("sssMap.toString():", ssMap.get(0));


        List list = new ArrayList(3);
        Log.e("list.size()==", ""+list.size());
        //Log.e("list.get(1)==", ""+list.get(1));

        View headerView = getLayoutInflater().inflate(R.layout.headerview, null);
        //View emptyView = getLayoutInflater().inflate(R.layout.sell_out_layout, null);
        emptyView = findViewById(R.id.empty_view);
        listView1 = (ListView) findViewById(R.id.test_list_view);
        itemList = JListItem.mockItems(20);
        topAdapter = new JListViewAdapter(context, itemList);
        listView1.setOnScrollListener(this);
        listView1.addHeaderView(headerView);
        listView1.setAdapter(topAdapter);
        int itemCount = topAdapter.getCount();
        if (itemCount == 0) {
            emptyView.setVisibility(View.VISIBLE);
        }
        //listView1.setEmptyView(emptyView);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        Log.e("scrollState", scrollState+"");
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        int lastItem = firstVisibleItem + visibleItemCount;
        Log.e("lastItem" + lastItem, "totalItemCount" + totalItemCount);
    }

    public static class ListUtils {
        public static void setDynamicHeight(ListView mListView) {
            ListAdapter mListAdapter = mListView.getAdapter();
            if (mListAdapter == null) {
                // when adapter is null
                return;
            }
            int height = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
            for (int i = 0; i < mListAdapter.getCount(); i++) {
                View listItem = mListAdapter.getView(i, null, mListView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                height += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = mListView.getLayoutParams();
            params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
            mListView.setLayoutParams(params);
            mListView.requestLayout();
        }
    }
}
