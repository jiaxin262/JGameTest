package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.jia.jason.jgametest.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xin.jia
 * since 2016/7/30
 */
public class ListAdapterActivity extends BaseActivity implements AbsListView.OnScrollListener {

    private ListView listView;
    private ArrayAdapter arrayAdapter;
    private String[] dataSource;

    private SimpleAdapter simpleAdapter;
    private List<Map<String, Object>> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_adapter_layout);

        dataSource = getResources().getStringArray(R.array.list_items);
        listView = (ListView) findViewById(R.id.list_view_adapter_lv);
        //arrayAdapter = new ArrayAdapter<String>(this, R.layout.array_adapter_item_layotu, dataSource);
        //listView.setAdapter(arrayAdapter);
        dataList = new ArrayList<>();
        simpleAdapter = new SimpleAdapter(this, getDataList(), R.layout.item_layout,
                new String[]{"src", "text"}, new int[]{R.id.cube, R.id.textView});
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
    }

    private List<Map<String, Object>> getDataList() {
        for (int i = 0; i < 20; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("src", R.drawable.app_lunar_lander);
            map.put("text", "aaaaaaaaaaaaaaaaaa");
            dataList.add(map);
        }
        return dataList;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        Log.e("scrollState:", scrollState+"");
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        String prompt = position + "-" + id;
        Toast.makeText(ListAdapterActivity.this, prompt, Toast.LENGTH_SHORT).show();
    }
}
