package com.jia.jason.jgametest.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jia.jason.jgametest.R;

/**
 * Created by jiaxin on 16/10/22.
 */

public class SPTest extends BaseActivity {

    EditText et_editText;
    TextView tv_sp_content;
    private final String EDIT_TEXT_CONTENT = "edit_text_content";
    private final String SP_NAME = "sp_et_content";

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
