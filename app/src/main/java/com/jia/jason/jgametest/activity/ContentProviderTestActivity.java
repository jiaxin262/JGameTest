package com.jia.jason.jgametest.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jia.jason.jgametest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiaxin on 16/10/23.
 */

public class ContentProviderTestActivity extends BaseActivity {

    private ListView contactListView;
    ArrayAdapter<String> arrayAdapter;
    List<String> contactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_list_layout);

        contactListView = (ListView) findViewById(R.id.j_contact_list_lv);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactList);
        contactListView.setAdapter(arrayAdapter);
        readContact();
    }

    private void readContact() {
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null, null, null
            );
            while (cursor.moveToNext()) {
                String contactName = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                ));
                String number = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                ));
                contactList.add(contactName+"   "+number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
    }
}
