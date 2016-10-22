package com.jia.jason.jgametest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jia.jason.jgametest.R;
import com.jia.jason.jgametest.model.BookItem;
import java.util.List;

/**
 * Created by jiaxin on 16/10/22.
 */

public class JBookListViewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<BookItem> itemList;

    public JBookListViewAdapter(Context context, List<BookItem> itemList) {
        this.context = context;
        this.itemList = itemList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ContentHolder{
        public TextView index;
        public TextView name;
        public TextView author;
        public TextView pages;
        public TextView price;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ContentHolder holder;
        if (view == null){
            //加载布局
            view = inflater.inflate(R.layout.book_item_layout,null);
            holder = new ContentHolder();
            holder.index = (TextView) view.findViewById(R.id.j_book_item_index);
            holder.name = (TextView) view.findViewById(R.id.j_book_item_name);
            holder.author = (TextView) view.findViewById(R.id.j_book_item_author);
            holder.pages = (TextView) view.findViewById(R.id.j_book_item_pages);
            holder.price = (TextView) view.findViewById(R.id.j_book_item_price);
            view.setTag(holder);
        }else {
            holder = (ContentHolder) view.getTag();
        }
        final BookItem item = (BookItem) getItem(position);
        holder.index.setText(item.getIndex()+"");
        holder.name.setText(item.getName());
        holder.author.setText(item.getAuthor());
        holder.pages.setText(item.getPages()+"");
        holder.price.setText(context.getString(R.string.book_price, item.getPrice()+""));
        return view;
    }

}
