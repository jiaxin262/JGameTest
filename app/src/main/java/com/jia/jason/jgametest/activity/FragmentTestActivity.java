package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jia.jason.jgametest.R;
import com.jia.jason.jgametest.fragment.Fragment1;
import com.jia.jason.jgametest.fragment.Fragment2;
import com.jia.jason.jgametest.fragment.Fragment3;
import com.jia.jason.jgametest.fragment.Fragment4;

/**
 * Created by xin.jia
 * since 2016/8/7
 */
public class FragmentTestActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, Fragment1.ReplyListener{

    RadioGroup radioGroup;
    Fragment currentFragment;
    Fragment1 fragment1;
    Fragment fragment2;
    Fragment fragment3;
    Fragment fragment4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_test_layout2);
        Log.e("FragmentTestActivity:", " onCreate");

        radioGroup = (RadioGroup) findViewById(R.id.rg_fragments);
        radioGroup.setOnCheckedChangeListener(this);
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();

        Bundle bundle = new Bundle();
        bundle.putString("text", "来自Activity的问候");
        fragment1.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container_ll, fragment1);
        transaction.commit();
        currentFragment = fragment1;
        fragment1.setListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_f1:
                changeFragment(fragment1);
                break;
            case R.id.rb_f2:
                changeFragment(fragment2);
                break;
            case R.id.rb_f3:
                changeFragment(fragment3);
                break;
            case R.id.rb_f4:
                changeFragment(fragment4);
                break;
        }
    }

    private void changeFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!getSupportFragmentManager().getFragments().contains(fragment)) {
            transaction.add(R.id.fragment_container_ll, fragment);
        }
        transaction.hide(currentFragment);
        transaction.show(fragment);
        transaction.commit();
        currentFragment = fragment;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("FragmentTestActivity:", " onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("FragmentTestActivity:", " onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("FragmentTestActivity:", " onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("FragmentTestActivity:", " onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("FragmentTestActivity:", " onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("FragmentTestActivity:", " onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("FragmentTestActivity:", " onRestoreInstanceState");
    }

    @Override
    public void reply(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }
}
