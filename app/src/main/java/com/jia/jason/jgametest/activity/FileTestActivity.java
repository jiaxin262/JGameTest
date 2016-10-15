package com.jia.jason.jgametest.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.jia.jason.jgametest.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by jiaxin
 * on 16/10/15.
 */

public class FileTestActivity extends BaseActivity {

    EditText editText;
    private final String J_FILE_NAME = "j_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_test_layout);

        editText = (EditText) findViewById(R.id.j_file_name_et);
        restoreDataFromFile();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveDataToFile(editText.getText().toString());
    }

    private void restoreDataFromFile() {
        String content = getDataFromFile();
        if (!TextUtils.isEmpty(content)) {
            editText.setText(content);
            editText.setSelection(content.length());
            //editText.setSelection(2,4);
            //editText.extendSelection(2);
            //editText.selectAll();
        }
    }

    public void saveDataToFile(String content) {
        FileOutputStream fos;
        BufferedWriter bufferedWriter = null;
        try {
            fos = openFileOutput(J_FILE_NAME, Context.MODE_PRIVATE);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos));
            bufferedWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getDataFromFile() {
        FileInputStream fis;
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        try {
            fis = openFileInput(J_FILE_NAME);
            bufferedReader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
