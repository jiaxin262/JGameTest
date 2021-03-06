package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import com.jia.jason.jgametest.R;

/**
 * Created by xin.jia
 * since 2016/7/24
 */
public class AutoCompleteTextActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto_complete_layout);

        String[] countries = getResources().getStringArray(R.array.countries);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,  android.R.layout.simple_dropdown_item_1line, countries);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.tv_autoComplete);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(arrayAdapter);

        MultiAutoCompleteTextView multiAutoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.tv_multi_autoComplete);
        multiAutoCompleteTextView.setThreshold(1);
        multiAutoCompleteTextView.setAdapter(arrayAdapter);
        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

    }

}
