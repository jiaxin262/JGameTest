package com.jia.jason.jgametest.view;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.jia.jason.jgametest.view.MovementView;

/**
 * Created by xin.jia
 * since 2016/2/17
 */
public class UpdateThread extends Thread {

    private long time;
    private int fps = 100;
    private boolean toRun = false;
    private MovementView movementView;
    private SurfaceHolder surfaceHolder;

    public UpdateThread(MovementView movementView) {
        this.movementView = movementView;
        this.surfaceHolder = movementView.getHolder();
    }

    public void setRunning(boolean run) {
        toRun = run;
    }

    @Override
    public void run() {
        Canvas c;
        while (toRun) {
            long cTime = System.currentTimeMillis();
            if ((cTime - time) >= 1000 / fps) {
                c = null;
                try {
                    c = surfaceHolder.lockCanvas(null);
                    movementView.updatePhysics();
                    movementView.onDraw(c);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (c != null) {
                        surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
                time = cTime;
            }
        }
    }
}
