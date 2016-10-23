package com.jia.jason.jgametest.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jia.jason.jgametest.R;

/**
 * Created by jiaxin on 16/10/23.
 */

public class NotificationTestActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_test_layout);

        Button btnSendNotificaiton = (Button) findViewById(R.id.j_send_notification_btn);
        btnSendNotificaiton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.j_send_notification_btn:
                sendNotification();
                break;
        }
    }

    private void sendNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new Notification.Builder(this)
                .setContentInfo("content info")
                .setContentText("content text")
                .setContentTitle("content title")
                .setTicker("ticker")
                .setSubText("subText")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.air_ship_fire))
                .build();
        manager.notify(1, notification);
    }
}
