package com.jia.jason.jgametest.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.jia.jason.jgametest.R;
import com.jia.jason.jgametest.model.JListItem;

import java.util.List;

/**
 * Created by xin.jia
 * since 2016/3/2
 */
public class JListViewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<JListItem> itemList;

    public JListViewAdapter(Context context, List<JListItem> itemList) {
        this.context = context;
        this.itemList = itemList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class ContentHolder{
        public ImageView cube;
        public TextView textView;
        public CheckBox checkBox;
    }


    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ContentHolder holder;
        if (view == null){
            //加载布局
            view = inflater.inflate(R.layout.item_layout,null);
            holder = new ContentHolder();
            holder.cube = (ImageView) view.findViewById(R.id.cube);
            holder.textView = (TextView) view.findViewById(R.id.textView);
            holder.checkBox = (CheckBox) view.findViewById(R.id.checkbox);
            view.setTag(holder);
        }else {
            holder = (ContentHolder) view.getTag();
        }
        final JListItem item = (JListItem) getItem(i);
        holder.cube.setBackgroundColor(item.color);
        holder.textView.setText(item.text);
        holder.checkBox.setChecked(item.isChecked);
        holder.checkBox.setClickable(false);
        view.setClickable(false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.isChecked = !item.isChecked;
                notifyDataSetChanged();
            }
        });
        return view;
    }
}
