package com.jia.jason.jgametest.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

/**
 * Created by jiaxin on 16/10/15.
 */

public class BroadcastTestActivity extends BaseActivity {

    public static final String TAG = "BroadcastTestActivity";
    private IntentFilter intentFilter;
    private NetworkChangeReciever networkChangeReciever;
    LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReciever = new NetworkChangeReciever(this);
        registerReceiver(networkChangeReciever, intentFilter);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        Log.d(TAG+"-getActiveNetWorkInfo", connectivityManager.getActiveNetworkInfo().toString());

        //发送广播
        Intent intent = new Intent("com.jia.jason.jgametest.MY_BROADCAST");
        sendBroadcast(intent);  //发送标准广播
        sendOrderedBroadcast(intent, null); //发送有序广播

        //本地广播
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.sendBroadcast(intent);
        localBroadcastManager.registerReceiver(networkChangeReciever, intentFilter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReciever);
        localBroadcastManager.unregisterReceiver(networkChangeReciever);
    }

    class NetworkChangeReciever extends BroadcastReceiver {

        Context context;

        public NetworkChangeReciever() {
        }

        public NetworkChangeReciever(Context context) {
            this.context = context;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
