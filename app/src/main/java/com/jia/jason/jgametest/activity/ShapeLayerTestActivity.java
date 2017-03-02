package com.jia.jason.jgametest.activity;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jia.jason.jgametest.R;

/**
 * Created by jiaxin on 16/11/1.
 */

public class ShapeLayerTestActivity extends BaseActivity {
    public static final String TAG = "ShapeLayerTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shape_layer_test_layout);

        TextView tv = (TextView) findViewById(R.id.j_shape_layer_tv);
        tv.setBackgroundDrawable(getResources().getDrawable(R.drawable.layer_list_border_shape));
        tv.setText("爱就是打开了房间；老师叫对方拉萨的法律思考的积分爱上了反对");

        GradientDrawable gradientDrawable = (GradientDrawable) tv.getBackground();
        Log.d(TAG, "getConstantState():" + gradientDrawable.getConstantState());

        Log.d("number", 5/2*2+"");
        Log.d("number", 5%2+"");

        for (int i = 5; i < 3; i++) {
            Log.d("for_test", i+"");
        }

    }
}
