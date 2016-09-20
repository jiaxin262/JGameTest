package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jia.jason.jgametest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xin.jia
 * since 2016/8/1
 */
public class GridViewActivity extends BaseActivity implements AdapterView.OnItemSelectedListener{

    TextView textView;
    Spinner spinner;
    GridView gridView;
    List<String> gridDataList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view_layout);

        textView = (TextView) findViewById(R.id.spinner_text);
        spinner = (Spinner) findViewById(R.id.citySpinner);
        gridView = (GridView) findViewById(R.id.grid_view);
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.array_adapter_item_layotu, getGridData());
        gridView.setAdapter(arrayAdapter);
        gridView.setOnItemClickListener(this);

        textView.setText("您选择的是："+arrayAdapter.getItem(0));
        spinner.setAdapter(arrayAdapter);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(this);
    }

    private List<String> getGridData() {
        String itemName = "grid";
        gridDataList = new ArrayList<>();
        for (int i = 0; i < 50; i ++) {
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        textView.setText("您选择的是："+arrayAdapter.getItem(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
