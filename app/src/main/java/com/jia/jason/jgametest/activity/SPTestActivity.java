package com.jia.jason.jgametest.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jia.jason.jgametest.R;

/**
 * Created by jiaxin on 16/10/22.
 */

public class SPTestActivity extends BaseActivity {

    EditText et_editText;
    EditText et_2;
    TextView tv_sp_content;
    Button btnAdd, btnClear;
    LinearLayout llContentContainer;
    private final String EDIT_TEXT_CONTENT = "edit_text_content";
    private final String SP_NAME = "sp_et_content";

    private final String CONTENT_1 = "content1";
    private final String CONTENT_2 = "content2";
    private final String CONTENT_3 = "content3";
    private final String CONTENT_4 = "content4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_test_layout);

        et_editText = (EditText) findViewById(R.id.j_file_name_et);
        tv_sp_content = (TextView) findViewById(R.id.j_file_content_tv);

        et_editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    readDataFromSP();
                } else {
                    saveDataToSP(et_editText.getText().toString());
                }
            }
        });

        et_2 = (EditText) findViewById(R.id.j_sp_string_et);
        btnAdd = (Button) findViewById(R.id.j_btn_add);
        btnClear = (Button) findViewById(R.id.j_btn_clear);
        llContentContainer = (LinearLayout) findViewById(R.id.j_ll_container);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveContents();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearContents();
            }
        });
    }

    private void readAndShowcontents() {
        SharedPreferences sp = getSharedPreferences(SP_NAME, MODE_PRIVATE);
        String[] strings = new String[4];
        strings[0] = sp.getString(CONTENT_1, "...");
        strings[1] = sp.getString(CONTENT_2, "...");
        strings[2] = sp.getString(CONTENT_3, "...");
        strings[3] = sp.getString(CONTENT_4, "...");
        addTextView(strings);
    }

    private void addTextView(String[] strings) {
        llContentContainer.removeAllViews();
        for (String s : strings) {
            TextView tv = new TextView(SPTestActivity.this);
            tv.setText(s);
            llContentContainer.addView(tv);
        }
    }

    private void clearContents() {
        SharedPreferences.Editor editor = getSharedPreferences(SP_NAME, MODE_PRIVATE).edit();
        editor.remove(CONTENT_1);
        editor.remove(CONTENT_2);
        editor.remove(CONTENT_3);
        editor.remove(CONTENT_4);
        editor.apply();
        Toast.makeText(SPTestActivity.this, "content has been removed.", Toast.LENGTH_SHORT).show();
        readAndShowcontents();
    }

    private void saveContents() {
        SharedPreferences.Editor editor = getSharedPreferences(SP_NAME, MODE_PRIVATE).edit();
        editor.putString(CONTENT_1, "11111111");
        editor.putString(CONTENT_2, "22222222");
        editor.putString(CONTENT_3, "33333333");
        editor.apply();
        Toast.makeText(SPTestActivity.this, "content has been added.", Toast.LENGTH_SHORT).show();
        readAndShowcontents();
    }

    private void saveDataToSP(String contetn) {
        SharedPreferences.Editor editor = getSharedPreferences(SP_NAME, MODE_PRIVATE).edit();
        editor.putString(EDIT_TEXT_CONTENT, contetn);
        //editor.commit();
        editor.apply();
    }

    private void readDataFromSP() {
        SharedPreferences sp = getSharedPreferences(SP_NAME, MODE_PRIVATE);
        String content = sp.getString(EDIT_TEXT_CONTENT, "default_value");
        et_editText.setText(getString(R.string.from_sp, new Object[]{content}));
    }


}
