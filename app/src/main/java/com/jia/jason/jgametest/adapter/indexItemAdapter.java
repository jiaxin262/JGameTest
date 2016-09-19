package com.jia.jason.jgametest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jia.jason.jgametest.R;
import com.jia.jason.jgametest.activity.Action;
import com.jia.jason.jgametest.activity.BaseActivity;
import com.jia.jason.jgametest.model.IndexItemModel;

import java.util.List;

/**
 * Created by xin.jia
 * since 2016/9/19
 */
public class IndexItemAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<IndexItemModel> indexItems;
    private Action<Class<? extends BaseActivity>> onClickAction;

    public IndexItemAdapter(Context context, List<IndexItemModel> indexItems) {
        this.indexItems = indexItems;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return indexItems.size();
    }

    @Override
    public Object getItem(int position) {
        return indexItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ContentHolder holder;
        if (view == null){
            view = inflater.inflate(R.layout.index_item_layout,null);
            holder = new ContentHolder();
            holder.itemName = (TextView) view.findViewById(R.id.index_item_name);
            view.setTag(holder);
        }else {
            holder = (ContentHolder) view.getTag();
        }
        final IndexItemModel item = (IndexItemModel) getItem(i);
        holder.itemName.setText(item.itemName);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAction.execute(item.activityClass);
            }
        });
        return view;
    }

    class ContentHolder{
        public TextView itemName;
    }

    public void setOnClickAction(Action<Class<? extends BaseActivity>> action) {
        this.onClickAction = action;
    }
}
