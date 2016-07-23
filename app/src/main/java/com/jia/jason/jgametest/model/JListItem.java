package com.jia.jason.jgametest.model;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by xin.jia
 * since 2016/3/2
 */
public class JListItem {

    public int color;
    public String text;
    public boolean isChecked = false;
    private static final int ITEM_SIZE = 25;

    public JListItem() {
        color = Color.BLUE;
        text = "第一个view";
    }

    public JListItem(int color, String text) {
        this.color = color;
        this.text = text;
    }

    //生成假数据
    public static List<JListItem> mockItems(int count){
        List<JListItem> items = new ArrayList<JListItem>();
        int[] colors = new int[]{
                Color.RED, Color.BLACK, Color.BLUE, Color.CYAN, Color.DKGRAY,
                Color.GRAY, Color.GREEN, Color.LTGRAY, Color.YELLOW, Color.TRANSPARENT,
        };
        for (int i=0; i<count; i++){
            int random = new Random().nextInt(10);
            JListItem item = new JListItem(colors[random], "第"+i+"个view");
            items.add(item);
        }
        return items;
    }

    //生成假数据
    public static List<JListItem> mockItems2(int count){
        List<JListItem> items = new ArrayList<JListItem>();
        for (int i=0; i<count; i++){
            JListItem item = new JListItem(Color.GREEN, "第"+i+"个viewLLLLLLLLL");
            items.add(item);
        }
        return items;
    }

}
