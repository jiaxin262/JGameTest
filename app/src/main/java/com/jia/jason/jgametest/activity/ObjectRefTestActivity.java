package com.jia.jason.jgametest.activity;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;

import com.jia.jason.jgametest.model.ObjectRefModel;

import java.util.HashMap;

/**
 * Created by jiaxin on 2017/1/4.
 */

public class ObjectRefTestActivity extends BaseActivity {
    public static final String TAG = "ObjectRefTestActivity";

    SparseArray<ObjectRefModel> objectRefs;
    HashMap<Integer, ObjectRefModel> objectRefMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {
        objectRefs = new SparseArray<>();
        objectRefMaps = new HashMap<>();
        ObjectRefModel model = new ObjectRefModel(0, "model0", new int[]{0, 1, 2});
        addModel(model);
        ObjectRefModel model1 = new ObjectRefModel(1, "model1", new int[]{3, 4, 5});
        addModel(model1);
        ObjectRefModel model2 = new ObjectRefModel(2, "model2", new int[]{6, 7, 8});
        addModel(model2);

        model.name = "new name";

        for (int i = 0; i < objectRefs.size(); i++) {
            Log.d(TAG, objectRefs.get(i).hashCode()+"");
            Log.d(TAG, objectRefMaps.get(i).hashCode()+"");
            Log.d(TAG, objectRefs.get(i).name+"");
        }

        finalTest(model);

    }

    private void addModel(ObjectRefModel model) {
        for (int msg : model.msgs) {
            objectRefs.put(msg, model);
            objectRefMaps.put(msg, model);
        }
    }

    private void finalTest(final ObjectRefModel model) {
        Log.d(TAG, model.hashCode()+"");
        model.name = "change";
        Log.d(TAG, model.hashCode()+"");
        Log.d(TAG, model.name+"");

        final Integer integer = 2;
        Integer integer1 = integer;
        integer1 = 3;
        Log.d(TAG, "integer:"+integer + ",integer1:"+integer1);

        final HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "111");
        Log.d(TAG, hashMap.get(1));
        HashMap<Integer, String> hashMap1 = hashMap;
        hashMap1.put(1, "222");
        Log.d(TAG, hashMap.get(1));
    }
}
