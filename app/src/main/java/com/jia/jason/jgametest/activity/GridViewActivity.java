package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.jia.jason.jgametest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xin.jia
 * since 2016/8/1
 */
public class GridViewActivity extends BaseActivity {

    Spinner spinner;
    GridView gridView;
    List<String> gridDataList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view_layout);

        spinner = (Spinner) findViewById(R.id.citySpinner);
        gridView = (GridView) findViewById(R.id.grid_view);
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.array_adapter_item_layotu, getGridData());
        gridView.setAdapter(arrayAdapter);
        gridView.setOnItemClickListener(this);
    }

    private List<String> getGridData() {
        String itemName = "grid";
        gridDataList = new ArrayList<>();
        for (int i = 0; i < 20; i ++) {
            String item = itemName + i;
            gridDataList.add(item);
        }
        return gridDataList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        super.onItemClick(parent, view, position, id);
        Toast.makeText(this, position+","+id, Toast.LENGTH_SHORT).show();
    }
}
