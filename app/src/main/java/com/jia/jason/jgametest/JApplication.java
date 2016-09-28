package com.jia.jason.jgametest;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by xin.jia
 * since 2016/3/29
 */
public class JApplication extends Application {

    private static Context mContext;

    public JApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mContext = this;
    }

    public static Context getContext() {
        if(mContext == null) {
            throw new RuntimeException("you must extends JApplication !!! ");
        } else {
            return mContext;
        }
    }

}
