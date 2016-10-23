package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jia.jason.jgametest.R;

/**
 * Created by jiaxin on 16/10/23.
 */

public class HandlerTestActivity extends BaseActivity {

    public static final int UPDATE_TEXT = 1;
    private Button btnUpdateUI;
    private TextView tvHandler;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT:
                    tvHandler.setText("Nice to meet you");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler_test_layout);

        btnUpdateUI = (Button) findViewById(R.id.j_update_text_btn);
        tvHandler = (TextView) findViewById(R.id.j_handler_test_tv);
        btnUpdateUI.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.j_update_text_btn:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
        }
    }
}
