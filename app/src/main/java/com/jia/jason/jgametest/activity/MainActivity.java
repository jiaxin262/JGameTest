package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.widget.GridView;
import com.jia.jason.jgametest.R;
import com.jia.jason.jgametest.adapter.IndexItemAdapter;
import com.jia.jason.jgametest.model.IndexItemEnums;
import com.jia.jason.jgametest.model.IndexItemModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    IndexItemAdapter indexItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView indexGridView = (GridView) findViewById(R.id.index_grid_view);
        indexItemAdapter = new IndexItemAdapter(this, getIndexItems());
        indexItemAdapter.setOnClickAction(new Action<Class<? extends BaseActivity>>() {
            @Override
            public void execute(Class<? extends BaseActivity> args) {
                jStartActivity(args);
            }
        });
        indexGridView.setAdapter(indexItemAdapter);

    }

    private List<IndexItemModel> getIndexItems() {
        List<IndexItemModel> indexItemModelList = new ArrayList<>();
        IndexItemEnums[] indexItemEnumses = IndexItemEnums.values();
        for (IndexItemEnums indexItemEnums : indexItemEnumses) {
            IndexItemModel indexItemModel = new IndexItemModel();
            indexItemModel.setItemName(indexItemEnums.getItemName());
            indexItemModel.setActivityClass(indexItemEnums.getClassName());
            indexItemModel.setBgColor(getResources().getColor(R.color.j_balck_and_gray));
            indexItemModelList.add(indexItemModel);
        }
        return indexItemModelList;
    }

}