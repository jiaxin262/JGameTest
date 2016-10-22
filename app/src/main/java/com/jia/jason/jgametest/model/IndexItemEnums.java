package com.jia.jason.jgametest.model;

import com.jia.jason.jgametest.activity.AutoCompleteTextActivity;
import com.jia.jason.jgametest.activity.BaseActivity;
import com.jia.jason.jgametest.activity.BroadcastTestActivity;
import com.jia.jason.jgametest.activity.ConstraintActivity;
import com.jia.jason.jgametest.activity.ContentProviderTestActivity;
import com.jia.jason.jgametest.activity.DrawableAnimationActivity;
import com.jia.jason.jgametest.activity.DrawableStateActivity;
import com.jia.jason.jgametest.activity.DrawableTestActivity;
import com.jia.jason.jgametest.activity.FileTestActivity;
import com.jia.jason.jgametest.activity.FingerPathActivity;
import com.jia.jason.jgametest.activity.FlyBallActivity;
import com.jia.jason.jgametest.activity.FragmentTestActivity;
import com.jia.jason.jgametest.activity.GBallActivity;
import com.jia.jason.jgametest.activity.GravitySensorActivity;
import com.jia.jason.jgametest.activity.GridViewActivity;
import com.jia.jason.jgametest.activity.FrescoImageViewActivity;
import com.jia.jason.jgametest.activity.JLunarLanderActivity;
import com.jia.jason.jgametest.activity.LifeCycleActivity;
import com.jia.jason.jgametest.activity.ListAdapterActivity;
import com.jia.jason.jgametest.activity.ListViewAndOtherActivity;
import com.jia.jason.jgametest.activity.ListViewTestActivity;
import com.jia.jason.jgametest.activity.ListViewsActivity;
import com.jia.jason.jgametest.activity.OtaTestActivity;
import com.jia.jason.jgametest.activity.ProgressBarActivity;
import com.jia.jason.jgametest.activity.ReachFilesActivity;
import com.jia.jason.jgametest.activity.SIActivity1;
import com.jia.jason.jgametest.activity.SPTestActivity;
import com.jia.jason.jgametest.activity.SQLiteTestActivity;
import com.jia.jason.jgametest.activity.ToggleButtonActivity;
import com.jia.jason.jgametest.activity.ViewFlipperActivity;

/**
 * Created by xin.jia
 * since 2016/9/19
 */
public enum IndexItemEnums {
    CONTENT_PROVIDER("ContentProvider", ContentProviderTestActivity.class),
    SQLITE_TEST("SQLiteTestActivity", SQLiteTestActivity.class),
    SP_TEST("SharedPrefercesTest", SPTestActivity.class),
    FILE_TEST("FileTest", FileTestActivity.class),
    BROADCAST_TEST("BroadcastTest", BroadcastTestActivity.class),
    ACTIVITY_TASK_TEST("ATaskTest", SIActivity1.class),
    CONSTRAINT_LAYOUT("ConstraintLayout", ConstraintActivity.class),
    LIFE_CYCLE("LifeCycle", LifeCycleActivity.class),
    IMAGEVIEW_TEST("ImageView", FrescoImageViewActivity.class),
    DRAWABLE_ANIMATION("DrawableAnimation", DrawableAnimationActivity.class),
    PROGRESSBAR_TEST("ProgressBar", ProgressBarActivity.class),
    FRAGMENT_TEST("FragmentTest", FragmentTestActivity.class),
    VIEW_FLIPPER_TEST("ViewFlipper", ViewFlipperActivity.class),
    GRID_VIEW_TEST("GridView", GridViewActivity.class),
    LIST_VIEW_TEST("ListView", ListAdapterActivity.class),
    TOGGLE_BUTTON("ToggleButton", ToggleButtonActivity.class),
    AUTO_COMPLETE_TEXT("AutoCompleteText", AutoCompleteTextActivity.class),
    GRAVITY_SENSOR("GravitySensor", GravitySensorActivity.class),
    GRAVITY_BALL("GravityBall", GBallActivity.class),
    FINGER_PATH("FingerPath", FingerPathActivity.class),
    FLY_BALL("FlyBall", FlyBallActivity.class),
    LUNAR_LANDER("LunarLander", JLunarLanderActivity.class),
    DRAWABLE_STATE("DrawableState", DrawableStateActivity.class),
    DRAWABLE_TEST("DrawableTest", DrawableTestActivity.class),
    REACH_FILES("RichFiles", ReachFilesActivity.class),
    LISTVIEWS("ListViews", ListViewsActivity.class),
    LISTVIEW_TEST("ListViewTest", ListViewTestActivity.class),
    LISTVIEW_AND_OTHER("ListViewAndOther", ListViewAndOtherActivity.class),
    OTA_TEST("OtaTest", OtaTestActivity.class);

    private String itemName;
    private Class<? extends BaseActivity> className;

    IndexItemEnums(Class<? extends BaseActivity> className) {
        this("defaultName", className);
    }

    IndexItemEnums(String itemName, Class<? extends BaseActivity> className) {
        this.itemName = itemName;
        this.className = className;
    }

    public String getItemName() {
        return itemName;
    }

    public Class<? extends BaseActivity> getClassName() {
        return className;
    }
}
