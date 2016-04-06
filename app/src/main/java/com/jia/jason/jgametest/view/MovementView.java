package com.jia.jason.jgametest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by xin.jia
 * since 2016/2/17
 */
public class MovementView extends SurfaceView implements SurfaceHolder.Callback {

    private int xPos, yPos, xVel, yVel, width, height, circleRadius;
    private Paint circlePaint;
    UpdateThread updateThread;

    public MovementView(Context context) {
        super(context);
        getHolder().addCallback(this);

        circleRadius = 50;
        circlePaint = new Paint();
        circlePaint.setColor(Color.BLUE);

        xVel = 10;
        yVel = 10;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Rect surfaceFrame = holder.getSurfaceFrame();
        width = surfaceFrame.width();
        height = surfaceFrame.height();

        xPos = width/2;
        yPos = circleRadius;

        updateThread = new UpdateThread(this);
        updateThread.setRunning(true);
        updateThread.start();
        Log.e("surfaceCreated:", xPos+", "+yPos);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.e("surfaceChanged:", "888");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.e("surfaceDestroyed:", "***");
        boolean retry = true;
        updateThread.setRunning(false);
        while (retry) {
            try {
                updateThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.drawCircle(xPos, yPos, circleRadius, circlePaint);
    }

    public void updatePhysics() {
        xPos += xVel;
        yPos += yVel;

        if (yPos - circleRadius < 0 || yPos + circleRadius > height) {  //上下边界
            if (yPos - circleRadius < 0) {
               yPos = circleRadius;
            } else {
                yPos = height - circleRadius;
            }
            yVel *= -1;
        }

        if (xPos - circleRadius < 0 || xPos + circleRadius > width) {  //左右边界
            if (xPos - circleRadius < 0) {
                xPos = circleRadius;
            } else {
                xPos = width - circleRadius;
            }
            xVel *= -1;
        }
    }

}
