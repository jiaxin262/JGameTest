package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.jia.jason.jgametest.R;

public class MainActivity extends BaseActivity {

    private TextView FlyBall;
    private TextView FingerPath;
    private TextView lunarLander;
    private TextView gBall;
    private TextView ListViewContainer;
    private TextView ListViewTest;
    private TextView jLunarLander;
    private TextView ListViewAndOther;
    private TextView gravitySensor;
    private TextView listAddSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FlyBall = (TextView) findViewById(R.id.fly_ball);
        FingerPath = (TextView) findViewById(R.id.finger_path);
        lunarLander = (TextView) findViewById(R.id.lunar_lander);
        gBall = (TextView) findViewById(R.id.g_ball);
        ListViewContainer = (TextView) findViewById(R.id.list_view_container);
        ListViewTest = (TextView) findViewById(R.id.list_view_test);
        jLunarLander = (TextView) findViewById(R.id.j_lunar_lander);
        ListViewAndOther = (TextView) findViewById(R.id.list_view_and_other);
        gravitySensor = (TextView) findViewById(R.id.gravity_sensor);
        listAddSet = (TextView) findViewById(R.id.list_add_set);

        FlyBall.setOnClickListener(this);
        FingerPath.setOnClickListener(this);
        lunarLander.setOnClickListener(this);
        gBall.setOnClickListener(this);
        ListViewContainer.setOnClickListener(this);
        ListViewTest.setOnClickListener(this);
        jLunarLander.setOnClickListener(this);
        ListViewAndOther.setOnClickListener(this);
        gravitySensor.setOnClickListener(this);
        listAddSet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fly_ball:
                jStartActivity(FlyBallActivity.class);
                break;
            case R.id.finger_path:
                jStartActivity(FingerPathActivity.class);
                break;
            case R.id.lunar_lander:
                jStartActivity(LunarLanderActivity.class);
                break;
            case R.id.g_ball:
                jStartActivity(GBallActivity.class);
                break;
            case R.id.list_view_container:
                jStartActivity(ListViewsActivity.class);
                break;
            case R.id.list_view_test:
                jStartActivity(ListViewTestActivity.class);
                break;
            case R.id.j_lunar_lander:
                jStartActivity(JLunarLanderActivity.class);
                break;
            case R.id.list_view_and_other:
                jStartActivity(ListViewAndOtherActivity.class);
                break;
            case R.id.gravity_sensor:
                jStartActivity(GravitySensorActivity.class);
                break;
            case R.id.list_add_set:
                jStartActivity(ListAddSetActivity.class);
                break;
            default:
                jShowAlertMessage("SORRY", "NOT ACCOMPLISHED!");
        }
    }

}