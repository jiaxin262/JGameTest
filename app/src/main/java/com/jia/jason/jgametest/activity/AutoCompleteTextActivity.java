package com.jia.jason.jgametest.activity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.jia.jason.jgametest.R;
import com.jia.jason.jgametest.model.StaticTest;

/**
 * Created by xin.jia
 * since 2016/7/24
 */
public class AutoCompleteTextActivity extends BaseActivity {

    private AutoCompleteTextView autoCompleteTextView;
    private MultiAutoCompleteTextView multiAutoCompleteTextView;
    private TextView textView1;
    private TextView textView2;

    private static final String[] COUNTRIES = {
            "Belgium", "France", "Italy", "Germany", "Spain", "China", "England", "Edgy", "Jason", "Japan", "JiaXin"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto_complete_layout);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,  android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.tv_autoComplete);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(arrayAdapter);
        multiAutoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.tv_multi_autoComplete);
        multiAutoCompleteTextView.setThreshold(1);
        multiAutoCompleteTextView.setAdapter(arrayAdapter);
        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        textView1 = (TextView) findViewById(R.id.jason_1);
        textView2 = (TextView) findViewById(R.id.jason_2);
        textView1.setBackgroundResource(R.drawable.jason_shape);
        GradientDrawable gd1 = (GradientDrawable) textView1.getBackground();
        Log.e("gd1.getState.hashCode()", gd1.getConstantState().hashCode()+"");
        gd1.setColor(Color.RED);
        textView2.setBackgroundResource(R.drawable.jason_shape);
        GradientDrawable gd2 = (GradientDrawable) textView2.getBackground();
        Log.e("gd2.getState.hashCode()", gd1.getConstantState().hashCode()+"");
//        GradientDrawable gd2 = (GradientDrawable) textView2.getBackground();
//        gd2.setColor(Color.GREEN);

        textView1.setOnClickListener(this);


        StaticTest s1 = new StaticTest();
        StaticTest s2 = new StaticTest();
        Log.e("s1.hashCode()", s1.getStaticInner()+"");
        Log.e("s2.hashCode()", s2.getStaticInner()+"");
    }

    @Override
    public void onClick(View v) {
        GradientDrawable gd1 = (GradientDrawable) textView1.getBackground();
        gd1.setColor(Color.BLUE);
        //textView2.setBackgroundResource(R.drawable.jason_shape);
    }

}
