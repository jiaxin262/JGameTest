package com.jia.jason.jgametest.util;

import android.util.Log;

import com.jia.jason.jgametest.model.BookItem;

/**
 * Created by jiaxin on 2017/1/19.
 */

public class MsgUtil {

    public static final String TAG = "MsgUtil";

    private BookItem bookItem;

    private MsgUtil instance;

    private MsgUtil() {}

    public MsgUtil obtain() {
        if (instance == null) {
            instance = new MsgUtil();
        }
        return instance;
    }

    public void init(BookItem bookItem) {
        this.bookItem = bookItem;
    }

    public void getBookItem() {
    }
}
