package com.jia.jason.jgametest.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.jia.jason.jgametest.activity.BroadcastTestActivity;

/**
 * Created by jiaxin on 16/10/15.
 */

public class BootCompleteReciever extends BroadcastReceiver {

    public BootCompleteReciever() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("com.jia.jason.jgametest.MY_BROADCAST".equals(action)) {
            Toast.makeText(context, "receive MY_BROADCAST", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "JGameTest已自动启动", Toast.LENGTH_SHORT).show();
            Log.e(BroadcastTestActivity.TAG, "JGameTest已自动启动");
        }
    }
}
