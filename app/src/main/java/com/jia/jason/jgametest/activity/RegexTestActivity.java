
package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.util.Log;

import com.jia.jason.jgametest.R;

import java.util.regex.Pattern;

public class RegexTestActivity extends BaseActivity {
    public static final String TAG = "RegexTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regex_layout);

        testRegex();
    }

    private void testRegex() {
        String pattern = "\\d{7,7}[a-z]{1}";
        String content1 = "1234567a";
        String content2 = "12345678";
        String content3 = "123456qa";
        String content4 = "a234567a";
        String content5 = "a2345670";
        String content6 = "1234567a.";
        String content7 = "1234567a_";
        String content8 = "1234567a1";
        String content9 = "1234567aZ";
        String content10 = "1234567Z";

        Log.d(TAG, "1 isMatch:" + Pattern.matches(pattern, content1));
        Log.d(TAG, "2 isMatch:" + Pattern.matches(pattern, content2));
        Log.d(TAG, "3 isMatch:" + Pattern.matches(pattern, content3));
        Log.d(TAG, "4 isMatch:" + Pattern.matches(pattern, content4));
        Log.d(TAG, "5 isMatch:" + Pattern.matches(pattern, content5));
        Log.d(TAG, "6 isMatch:" + Pattern.matches(pattern, content6));
        Log.d(TAG, "7 isMatch:" + Pattern.matches(pattern, content7));
        Log.d(TAG, "8 isMatch:" + Pattern.matches(pattern, content8));
        Log.d(TAG, "9 isMatch:" + Pattern.matches(pattern, content9));
        Log.d(TAG, "10 isMatch:" + Pattern.matches(pattern, content10));

    }
}
