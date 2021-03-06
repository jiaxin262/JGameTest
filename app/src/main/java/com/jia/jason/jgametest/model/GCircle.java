package com.jia.jason.jgametest.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import com.jia.jason.jgametest.view.GBallView;

import java.util.Random;

/**
 * Created by xin.jia
 * since 2016/2/25
 */
public class GCircle {

    private int arc_x, arc_y, arc_r;//圆形的X,Y坐标和半径
    private float speed_x = 2.2f, speed_y = 2.2f;//小球的x、y的速度
    private float vertical_speed;//加速度
    private float horizontal_speed;
    private final float ACC = 0.335f;//为了模拟加速度的偏移值
    private final float RECESSION = 0.3f;//每次弹起的衰退系数
    private boolean isDown = true;//是否处于下落  状态
    private Random ran;//随即数库
    private int circleColor; //每个小球的颜色

    /**
     * @param x 圆形X坐标
     * @param y 圆形Y坐标
     * @param r 圆形半径
     */
    public GCircle(int x, int y, int r) {
        ran = new Random();
        this.arc_x = x;
        this.arc_y = y;
        this.arc_r = r;
        setRandomColor();
    }
    public void drawMyArc(Canvas canvas, Paint paint) {//每个圆形都应该拥有一套绘画方法
        paint.setColor(circleColor);//不断的获取随即颜色，对圆形进行填充(实现圆形闪烁效果)
        canvas.drawArc(new RectF(arc_x + speed_x, arc_y + speed_y, arc_x + 2 *
                arc_r + speed_x, arc_y + 2 * arc_r + speed_y), 0, 360, true, paint);
    }
    /**
     * 随机为小球生成一种颜色
     */
    public void setRandomColor() {
        int ran_color = ran.nextInt(8);
        switch (ran_color) {
            case 0:
                break;
            case 1:
                circleColor = Color.BLUE;
                break;
            case 2:
                circleColor = Color.CYAN;
                break;
            case 3:
                circleColor = Color.DKGRAY;
                break;
            case 4:
                circleColor = Color.RED;
                break;
            case 6:
                circleColor = Color.GREEN;
            case 7:
            case 8:
                circleColor = Color.YELLOW;
                break;
        }
    }
    /**
     * 圆形的逻辑
     */
    public void logic() {//每个圆形都应该拥有一套逻辑
        if (isDown) {//圆形下落逻辑
            speed_y += vertical_speed;//圆形的Y轴速度加上加速度
            int count = (int) vertical_speed++;//这里拿另外一个变量记下当前速度偏移量
            //如果下面的for (int i = 0; i < vertical_speed++; i++) {}这样就就死循环了 - -
            for (int i = 0; i < count; i++) {
                vertical_speed += ACC;
            }
        } else {//圆形反弹逻辑
            if (vertical_speed <= 0) {
                isDown = true;
                return;
            }
            speed_y -= vertical_speed;
            int count = (int) vertical_speed--;
            for (int i = 0; i < count; i++) {
                vertical_speed -= ACC;
            }
        }
        if (isCollision()) {
            isDown = false;//当发生碰撞说明圆形的方向要改变一下了！
            vertical_speed -= vertical_speed * RECESSION;//每次碰撞都会衰减反弹的加速度
        }
        Log.e("isDown:" + isDown, "vertial_speed:" + vertical_speed);
    }
    /**
     * 圆形与屏幕底部的碰撞
     */
    public boolean isCollision() {
        return arc_y + 2 * arc_r + speed_y >= GBallView.screenH;
    }
}
