package com.jia.jason.jgametest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.jia.jason.jgametest.R;
import com.jia.jason.jgametest.model.GCircle;

import java.util.Random;
import java.util.Vector;

/**
 * Created by xin.jia
 * since 2016/2/25
 */
public class GBallView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private Thread th;
    private SurfaceHolder sfh;
    private Canvas canvas;
    private Paint paint;
    private boolean flag;
    public static int screenW, screenH;
    private Vector<GCircle> vc;//这里定义装我们自定义圆形的容器
    private Random ran;//随即库
    public GBallView(Context context) {
        super(context);
        this.setKeepScreenOn(true);
        vc = new Vector<GCircle>();
        ran = new Random();
        sfh = this.getHolder();
        sfh.addCallback(this);
        paint = new Paint();
        paint.setAntiAlias(true);
        setFocusable(true);
    }
    public void surfaceCreated(SurfaceHolder holder) {
        flag = true;
        th = new Thread(this);
        screenW = this.getWidth();
        screenH = this.getHeight();
        th.start();
    }
    public void draw() {
        try {
            canvas = sfh.lockCanvas();
            canvas.drawColor(getContext().getResources().getColor(R.color.grey));
            if (vc != null) {//当容器不为空，遍历容器中所有圆形画方法
                for (int i = 0; i < vc.size(); i++) {
                    vc.elementAt(i).drawMyArc(canvas, paint);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (canvas != null)
                    sfh.unlockCanvasAndPost(canvas);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    private void logic() {//主逻辑
        if (vc != null) {//当容器不为空，遍历容器中所有圆形逻辑
            for (int i = 0; i < vc.size(); i++) {
                vc.elementAt(i).logic();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                vc.addElement(new GCircle(
                        ran.nextInt(this.getWidth()-170), ran.nextInt(500), 20+ran.nextInt(150)));
                break;
            case MotionEvent.ACTION_MOVE :
                break;
            case MotionEvent.ACTION_UP :
                break;
        }
        return true;
    }

    public void run() {
        while (flag) {
            logic();
            draw();
            try {
                Thread.sleep(100);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;
    }
}
