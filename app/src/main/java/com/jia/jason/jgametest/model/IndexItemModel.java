package com.jia.jason.jgametest.model;


import com.jia.jason.jgametest.activity.BaseActivity;

/**
 * Created by xin.jia
 * since 2016/9/19
 */
public class IndexItemModel {

    private String itemName;
    private Class<? extends BaseActivity> activityClass;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Class<? extends BaseActivity> getActivityClass() {
        return activityClass;
    }

    public void setActivityClass(Class<? extends BaseActivity> activityClass) {
        this.activityClass = activityClass;
    }
}
