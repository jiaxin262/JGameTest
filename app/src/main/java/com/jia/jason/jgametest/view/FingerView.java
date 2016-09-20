package com.jia.jason.jgametest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by xin.jia
 * since 2016/9/20
 */
public class FingerView extends SurfaceView implements SurfaceHolder.Callback, Runnable{

    public int fps = 100;
    Canvas jCanvas = null;
    Paint jPaint = null;
    Paint jTextPaint = null;
    SurfaceHolder jSurfaceHolder = null;
    boolean isRunning = false;
    private float xPos, yPos;
    private Path jpath;
    Thread jPathThread;
    private long time;

    public FingerView(Context context) {
        super(context);
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);

        jSurfaceHolder = this.getHolder();
        jSurfaceHolder.addCallback(this);

        jCanvas = new Canvas();
        jPaint = new Paint();
        jPaint.setColor(Color.GREEN);
        jPaint.setAntiAlias(true);
        jPaint.setStyle(Paint.Style.STROKE);
        jPaint.setStrokeCap(Paint.Cap.ROUND);
        jPaint.setStrokeWidth(15);
        jTextPaint = new Paint();
        jTextPaint.setColor(Color.GREEN);
        jTextPaint.setTextSize(50);

        jpath = new Path();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float x = event.getX();
        float y = event.getY();
        Log.e("actionType---", action + "");
        switch (action) {
            case MotionEvent.ACTION_DOWN :
                jpath.moveTo(x, y);
                drawPoint(x, y);
                break;
            case MotionEvent.ACTION_MOVE :
                jpath.quadTo(xPos, yPos, x, y);
                break;
            case MotionEvent.ACTION_UP :
                break;
        }
        xPos = x;
        yPos = y;
        return true;
    }

    private void drawPoint(float x, float y) {
        try {
            jCanvas = jSurfaceHolder.lockCanvas();
            jCanvas.drawPoint(x, y, jPaint);
            jCanvas.drawText("当前触笔X：" + xPos, 0, 80, jTextPaint);
            jCanvas.drawText("当前触笔Y：" + yPos, 0, 150, jTextPaint);
            jSurfaceHolder.unlockCanvasAndPost(jCanvas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawPath() {
        if (jCanvas != null) {
            jCanvas.drawColor(Color.WHITE);
            jCanvas.drawPath(jpath, jPaint);
            jCanvas.drawText("当前触笔X：" + xPos, 0, 80, jTextPaint);
            jCanvas.drawText("当前触笔Y：" + yPos, 0, 150, jTextPaint);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isRunning = true;
        jPathThread = new Thread(this);
        jPathThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        jpath.reset();
        isRunning = false;
        boolean retry = true;
        while (retry) {
            try {
                jPathThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            long cTime = System.currentTimeMillis();
            if ((cTime - time) >= 1000 / fps) {
                try {
                    jCanvas = jSurfaceHolder.lockCanvas();
                    drawPath();
                    jSurfaceHolder.unlockCanvasAndPost(jCanvas);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                time = cTime;
            }
        }
    }
}