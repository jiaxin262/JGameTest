package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.jia.jason.jgametest.R;

public class MainActivity extends BaseActivity {

    private TextView FlyBall;
    private TextView FingerPath;
    private TextView reachFiles;
    private TextView gBall;
    private TextView ListViewContainer;
    private TextView ListViewTest;
    private TextView jLunarLander;
    private TextView ListViewAndOther;
    private TextView gravitySensor;
    private TextView otaTest;
    private TextView tvAutoComplete;
    private TextView tvToggleButton;
    private TextView tvListViewAdapter;
    private TextView tvGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FlyBall = (TextView) findViewById(R.id.fly_ball);
        FingerPath = (TextView) findViewById(R.id.finger_path);
        reachFiles = (TextView) findViewById(R.id.reach_files);
        gBall = (TextView) findViewById(R.id.g_ball);
        ListViewContainer = (TextView) findViewById(R.id.list_view_container);
        ListViewTest = (TextView) findViewById(R.id.list_view_test);
        jLunarLander = (TextView) findViewById(R.id.j_lunar_lander);
        ListViewAndOther = (TextView) findViewById(R.id.list_view_and_other);
        gravitySensor = (TextView) findViewById(R.id.gravity_sensor);
        otaTest = (TextView) findViewById(R.id.ota_test);
        tvAutoComplete = (TextView) findViewById(R.id.auto_complete_text);
        tvToggleButton = (TextView) findViewById(R.id.tv_toggleButton);
        tvListViewAdapter = (TextView) findViewById(R.id.listView_adapter);
        tvGridView = (TextView) findViewById(R.id.tv_gridView);

        FlyBall.setOnClickListener(this);
        FingerPath.setOnClickListener(this);
        reachFiles.setOnClickListener(this);
        gBall.setOnClickListener(this);
        ListViewContainer.setOnClickListener(this);
        ListViewTest.setOnClickListener(this);
        jLunarLander.setOnClickListener(this);
        ListViewAndOther.setOnClickListener(this);
        gravitySensor.setOnClickListener(this);
        otaTest.setOnClickListener(this);
        tvAutoComplete.setOnClickListener(this);
        tvToggleButton.setOnClickListener(this);
        tvListViewAdapter.setOnClickListener(this);
        tvGridView.setOnClickListener(this);
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
            case R.id.reach_files:
                jStartActivity(ReachFilesActivity.class);
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
            case R.id.ota_test:
                jStartActivity(OtaTestActivity.class);
                break;
            case R.id.auto_complete_text:
                jStartActivity(AutoCompleteTextActivity.class);
                break;
            case R.id.tv_toggleButton:
                jStartActivity(ToggleButtonActivity.class);
                break;
            case R.id.listView_adapter:
                jStartActivity(ListAdapterActivity.class);
                break;
            case R.id.tv_gridView:
                jStartActivity(GridViewActivity.class);
                break;
            default:
                jShowAlertMessage("SORRY", "NOT ACCOMPLISHED!");
        }
    }

}