package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.jia.jason.jgametest.R;

/**
 * Created by xin.jia
 * since 2016/7/24
 */
public class AutoCompleteTextActivity extends BaseActivity {

    private AutoCompleteTextView autoCompleteTextView;
    private static final String[] COUNTRIES = {
            "Belgium", "France", "Italy", "Germany", "Spain", "China", "England", "Edgy", "Jason", "Japan", "JiaXin"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto_complete_layout);

        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.tv_autoComplete);
        autoCompleteTextView.setThreshold(1);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,  android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        autoCompleteTextView.setAdapter(arrayAdapter);
    }
}
