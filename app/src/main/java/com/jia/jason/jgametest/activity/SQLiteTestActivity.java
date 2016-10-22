package com.jia.jason.jgametest.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.jia.jason.jgametest.R;
import com.jia.jason.jgametest.adapter.JBookListViewAdapter;
import com.jia.jason.jgametest.helper.JDatabaseHelper;
import com.jia.jason.jgametest.model.BookItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiaxin on 16/10/22.
 */

public class SQLiteTestActivity extends BaseActivity {

    private JDatabaseHelper jDatabaseHelper;
    private JBookListViewAdapter booksAdapter;
    private ListView bookListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sql_test_layout);

        Button btnCreateDb = (Button) findViewById(R.id.j_sql_db_create_btn);
        btnCreateDb.setOnClickListener(this);
        Button btnAddData = (Button) findViewById(R.id.j_sql_add_data_btn);
        btnAddData.setOnClickListener(this);
        Button btnUpdateData = (Button) findViewById(R.id.j_sql_update_btn);
        btnUpdateData.setOnClickListener(this);
        Button btnDeleteData = (Button) findViewById(R.id.j_sql_delete_btn);
        btnDeleteData.setOnClickListener(this);
        Button btnQueryData = (Button) findViewById(R.id.j_sql_query_btn);
        btnQueryData.setOnClickListener(this);
        Button btnReplaceData = (Button) findViewById(R.id.j_sql_replace_btn);
        btnReplaceData.setOnClickListener(this);
        bookListView = (ListView) findViewById(R.id.j_db_books_lv);

        jDatabaseHelper = new JDatabaseHelper(this, "BookStore.db", null, 3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.j_sql_db_create_btn:
                jDatabaseHelper.getWritableDatabase();
                break;
            case R.id.j_sql_add_data_btn:
                addDataToDatabase();
                break;
            case R.id.j_sql_update_btn:
                updateData();
                break;
            case R.id.j_sql_delete_btn:
                deleteData();
                break;
            case R.id.j_sql_query_btn:
                queryData();
                break;
            case R.id.j_sql_replace_btn:
                replaceData();
                break;
        }
    }

    private void replaceData() {
        SQLiteDatabase db = jDatabaseHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(JDatabaseHelper.BOOK_TABLE_NAME, null, null);
//            if (true) {
//                throw new NullPointerException();
//            }
            ContentValues values = new ContentValues();
            values.put(JDatabaseHelper.BOOK_COLUMN_NAME, "new Book");
            values.put(JDatabaseHelper.BOOK_COLUMN_AUTHOR, "new author");
            values.put(JDatabaseHelper.BOOK_COLUMN_PAGES, 123);
            values.put(JDatabaseHelper.BOOK_COLUMN_PRICE, 222);
            db.insert(JDatabaseHelper.BOOK_TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("", e.toString());
        } finally {
            db.endTransaction();
        }
    }

    private void queryData() {
        SQLiteDatabase db = jDatabaseHelper.getWritableDatabase();
        Cursor cursor = db.query(JDatabaseHelper.BOOK_TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            List<BookItem> bookList = new ArrayList<>();
            int index = 1;
            do {
                BookItem bookItem = new BookItem();
                bookItem.setIndex(index++);
                bookItem.setName(cursor.getString(cursor.getColumnIndex(JDatabaseHelper.BOOK_COLUMN_NAME)));
                bookItem.setAuthor(cursor.getString(cursor.getColumnIndex(JDatabaseHelper.BOOK_COLUMN_AUTHOR)));
                bookItem.setPages(cursor.getInt(cursor.getColumnIndex(JDatabaseHelper.BOOK_COLUMN_PAGES)));
                bookItem.setPrice(cursor.getDouble(cursor.getColumnIndex(JDatabaseHelper.BOOK_COLUMN_PRICE)));
                bookList.add(bookItem);
            } while (cursor.moveToNext());
            booksAdapter = new JBookListViewAdapter(SQLiteTestActivity.this, bookList);
            bookListView.setAdapter(booksAdapter);
        }
        cursor.close();
    }

    private void deleteData() {
        SQLiteDatabase db = jDatabaseHelper.getWritableDatabase();
        db.delete(JDatabaseHelper.BOOK_TABLE_NAME, "pages > ?", new String[] {"454"});
    }

    private void updateData() {
        SQLiteDatabase db = jDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("price", 100);
        db.update(JDatabaseHelper.BOOK_TABLE_NAME, values, "name = ?", new String[] {"Thinking in Java1"});
    }

    private void addDataToDatabase() {
        SQLiteDatabase db = jDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        for (int i = 0; i < 3; i++) {
            values.put("name", "Thinking in Java"+i);
            values.put("author", "Jason"+i);
            values.put("pages", 454+i);
            values.put("price", 56+i);
            db.insert(JDatabaseHelper.BOOK_TABLE_NAME, null, values);
            values.clear();
        }
    }
}
