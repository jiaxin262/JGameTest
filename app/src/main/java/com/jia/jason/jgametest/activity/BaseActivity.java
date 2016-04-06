package com.jia.jason.jgametest.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.jia.jason.jgametest.R;

/**
 * Created by xin.jia
 * since 2016/1/12
 */
public class BaseActivity extends Activity implements View.OnClickListener, View.OnLongClickListener,
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void jStartActivity(Intent intent) {
        this.startActivity(intent);
    }

    public void jStartActivity(Class<? extends Activity> cls) {
        this.jStartActivity((Class) cls, (Bundle) null);
    }

    public void jStartActivity(Class<? extends Activity> cls, Bundle bundle) {
        Intent intent = new Intent();
        if(bundle != null) {
            intent.putExtras(bundle);
        }

        intent.setClass(this, cls);
        this.startActivity(intent);
    }

    public void jStartActivityForResult(Class<? extends Activity> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        if(bundle != null) {
            intent.putExtras(bundle);
        }

        intent.setClass(this, cls);
        this.startActivityForResult(intent, requestCode);
    }

    public void jShowAlertMessage(String title, String message) {
        try {
            (new AlertDialog.Builder(this)).setTitle(title).setMessage(message)
                    .setNegativeButton(R.string.button_ok, (android.content.DialogInterface.OnClickListener)null).show();
        } catch (Exception var4) {
            ;
        }

    }

    public void jShowAlertMessage(int titleResId, String message) {
        this.jShowAlertMessage(this.getString(titleResId), message);
    }

    public void jShowAlertMessage(int titleResId, int msgResId) {
        this.jShowAlertMessage(this.getString(titleResId), this.getString(msgResId));
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return false;
    }
}
