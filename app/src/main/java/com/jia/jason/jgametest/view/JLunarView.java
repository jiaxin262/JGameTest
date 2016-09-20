package com.jia.jason.jgametest.view;

import android.app.Service;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import com.jia.jason.jgametest.R;

/**
 * Created by xin.jia
 * since 2016/2/29
 */
public class JLunarView extends SurfaceView implements SurfaceHolder.Callback {

    private Context mContext;
    private TextView mStatusText;
    private TextView tvGameStart;
    private JLunarThread thread;
    private SensorManager sm;
    private Sensor sensor;
    private SensorEventListener mySensorListener;
    private float x = 0, y = 0, z = 0;  //重力感应数据

    public JLunarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        sm = (SensorManager) context.getSystemService(Service.SENSOR_SERVICE);
        sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mySensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                x = event.values[0]; //手机横向翻滚,x>0 说明当前手机左翻 x<0右翻
                y = event.values[1]; //手机纵向翻滚,y>0 说明当前手机下翻 y<0上翻
                z = event.values[2]; //屏幕的朝向,z>0 手机屏幕朝上 z<0 手机屏幕朝下
                if (thread.mMode == thread.STATE_RUNNING) {
                    thread.landerXPos -= x;
                }
            }

            //传感器的精度发生改变时响应此函数
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // TODO Auto-generated method stub
            }
        };
        sm.registerListener(mySensorListener, sensor, SensorManager.SENSOR_DELAY_GAME);

        thread = new JLunarThread(holder, context, new Handler() {
            @Override
            public void handleMessage(Message m) {
                String text = m.getData().getString("text");
                if (text != null && !"".equals(text)) {
                    tvGameStart.setVisibility(VISIBLE);
                    tvGameStart.setText("START GAME");
                }
                mStatusText.setVisibility(m.getData().getInt("viz"));
                mStatusText.setText(text);
            }
        });

        setFocusable(true); // make sure we get key events
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        thread.setSurfaceSize(width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        thread.setRunning(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                thread.doScreenDown();
                break;
            case MotionEvent.ACTION_UP:
                thread.doScreenUp();
                break;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent msg) {
        return thread.doKeyDown(keyCode, msg);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent msg) {
        return thread.doKeyUp(keyCode, msg);
    }

    public void setTextView(TextView textView) {
        mStatusText = textView;
    }

    public void setGameStartTextView(TextView tvGameStart) {
        this.tvGameStart = tvGameStart;
    }

    public JLunarThread getThread() {
        return thread;
    }

    public class JLunarThread extends Thread {

        /** 游戏状态 */
        public static final int STATE_LOSE = 1;
        public static final int STATE_PAUSE = 2;
        public static final int STATE_READY = 3;
        public static final int STATE_RUNNING = 4;
        public static final int STATE_WIN = 5;

        /** UI参数 */
        public static final int LANDER_INIT_Y = 10; //登录器初始时上边坐标
        public static final double ACC_POWER = 1.02; //重力加速倍数
        public static final double ACC_INIT = 0.4;  //初始加速度
        public static final double TARGET_SPEED = 13;  //最大着陆速度
        public static final float TARGET_SPEED_WIDTH = 130;  //目标速度显示的宽度
        public static final float FULL_FUEL = 300;  //燃料量

        private int mCanvasHeight = 1;
        private int mCanvasWidth = 1;
        private double landerXPos;  //登录器左边坐标
        private double landerYPos;  //登录器上边坐标
        private double landerXAcc;  //X方向上增加的距离
        private double landerYAcc;  //Y方向上增加的距离
        private int mLanderWidth, mLanderHeight;
        private int targetPlatWidth, targetPlatHeight;
        private int leftCloudWidth, leftCloudHeight, rightCloudWidth, rightCloudHeight;
        private int seaHeight;  //海平面高度
        private int shipWidth, shipHeight;
        private Drawable targetPlatImage;
        private Drawable mLanderImage;
        private Drawable mFiringImage;
        private Drawable mCrashedImage;
        private Drawable cloudLeftImage;
        private Drawable cloudRightImage;
        private Drawable shipImage;
        private Paint bottomSeaPaint;   //下方海的画笔
        private Paint targetLinePaint;  //目标降落区域画笔
        private Paint speedPaint;   //最大速度画笔
        private Paint currentSpeedPaint;    //实时速度画笔
        private Paint fuelPaint;     //燃料画笔
        private Paint textPaint;    //文字画笔
        private int targetXPos;  //目标降落地X坐标
        private int targetWidth; //目标降落地宽度
        private int targetY;
        private float currentSpeed; //当前速度
        private float currentFule = FULL_FUEL;  //当前燃料
        private boolean isFuelAllUsed;  //燃料是否用完

        /** 逻辑参数 */
        private int mMode;
        private boolean mEngineFiring;
        private boolean mRun = false;

        private Handler mHandler;
        private SurfaceHolder mSurfaceHolder;

        private static final String KEY_LANDER_HEIGHT = "mLanderHeight";
        private static final String KEY_LANDER_WIDTH = "mLanderWidth";

        public JLunarThread(SurfaceHolder surfaceHolder, Context context, Handler handler) {

            mSurfaceHolder = surfaceHolder;
            mHandler = handler;
            mContext = context;

            Resources res = context.getResources();
            targetPlatImage = res.getDrawable(R.drawable.target_plat);
            mLanderImage = res.getDrawable(R.drawable.air_ship_normal);
            mFiringImage = res.getDrawable(R.drawable.air_ship_fire);
            mCrashedImage = res.getDrawable(R.drawable.lander_crashed);
            cloudLeftImage = res.getDrawable(R.drawable.cloud_left);
            cloudRightImage = res.getDrawable(R.drawable.cloud_right);
            shipImage = res.getDrawable(R.drawable.ship);

            targetPlatWidth = targetPlatImage.getIntrinsicWidth();
            targetPlatHeight = targetPlatImage.getIntrinsicHeight();
            mLanderWidth = mLanderImage.getIntrinsicWidth();
            mLanderHeight = mLanderImage.getIntrinsicHeight();
            leftCloudWidth = cloudLeftImage.getIntrinsicWidth();
            leftCloudHeight = cloudLeftImage.getIntrinsicHeight();
            rightCloudWidth = cloudRightImage.getIntrinsicWidth();
            rightCloudHeight = cloudRightImage.getIntrinsicHeight();
            shipWidth = shipImage.getIntrinsicWidth();
            shipHeight = shipImage.getIntrinsicHeight();
            mEngineFiring = true;
            landerXPos = mLanderWidth;
            landerYPos = mLanderHeight * 5;

            bottomSeaPaint = new Paint();
            bottomSeaPaint.setAntiAlias(true);
            bottomSeaPaint.setColor(Color.parseColor("#75acd9"));

            targetLinePaint = new Paint();
            targetLinePaint.setAntiAlias(true);
            targetLinePaint.setColor(Color.GREEN);
            targetLinePaint.setStrokeWidth(3);

            speedPaint = new Paint();
            speedPaint.setAntiAlias(true);
            speedPaint.setColor(Color.parseColor("#559B30FF"));
            speedPaint.setStrokeWidth(20);

            currentSpeedPaint = new Paint();
            currentSpeedPaint.setAntiAlias(true);
            currentSpeedPaint.setColor(Color.GREEN);
            currentSpeedPaint.setStrokeWidth(20);

            fuelPaint = new Paint();
            fuelPaint.setAntiAlias(true);
            fuelPaint.setColor(Color.GREEN);
            fuelPaint.setStrokeWidth(30);

            textPaint = new Paint();
            textPaint.setAntiAlias(true);
            textPaint.setColor(Color.WHITE);
            textPaint.setTextSize(20);
        }

        public void doStart() {
            synchronized (mSurfaceHolder) {
                landerXPos = mCanvasWidth/2 - mLanderWidth/2;
                landerYPos = LANDER_INIT_Y;
                landerXAcc = 0;
                landerYAcc = ACC_INIT;
                mEngineFiring = false;
                targetXPos = (int) (Math.random() * (mCanvasWidth - mLanderWidth*3/2));
                targetWidth = mLanderWidth * 3/2;
                seaHeight = mCanvasHeight/11;
                targetY = seaHeight - 40;

                currentSpeed = 0;
                currentFule = FULL_FUEL;
                isFuelAllUsed = false;
                setState(STATE_RUNNING);
            }
        }

        @Override
        public void run() {
            while (mRun) {
                Canvas c = null;
                try {
                    c = mSurfaceHolder.lockCanvas(null);
                    synchronized (mSurfaceHolder) {
                        if (mMode == STATE_RUNNING) {
                            updatePhysics();
                        }
                        doDraw(c);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (c != null) {
                        mSurfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }

        private void updatePhysics() {
            landerXPos += landerXAcc;
            /**Y方向偏移：加速下落+喷射器*/
            if (currentFule < 0) {
                isFuelAllUsed = true;
                currentFule = 0;
                setFiring(false);
                landerYAcc = ACC_INIT;
            }
            if (landerYAcc > 0) {
                landerYAcc *= ACC_POWER;
            }
            if (mEngineFiring) {
                landerYAcc -= 0.2;
                currentFule -= 0.2*7;
            }
            landerYPos += landerYAcc;
            double yAccTemp = landerYAcc;
            if (yAccTemp < 0) {
                yAccTemp = 0;
            }
            currentSpeed = (float) (yAccTemp+1) * 10;
            /**边缘检测*/
            if (landerXPos <= 0) {
                landerXPos = 0;
            }
            if (landerXPos + mLanderWidth >= mCanvasWidth) {
                landerXPos = mCanvasWidth - mLanderWidth;
            }
            if (landerYPos <= 0) {
                landerYPos = 0;
            }
            /**检测是否降落到地面*/
            if (landerYPos + mLanderHeight >= mCanvasHeight) {
                int result = STATE_LOSE;
                CharSequence message = "";
                Resources res = mContext.getResources();
                //检测是否降落到目标区域内
                if (landerXPos >= targetXPos && landerXPos + mLanderWidth <= targetXPos + targetWidth) {
                    //检测速度是否在限定范围内
                    if (landerYAcc <= TARGET_SPEED) {
                        result = STATE_WIN;
                    } else {
                        message = res.getText(R.string.message_too_fast);
                    }
                } else {
                    message = res.getText(R.string.message_off_pad);
                }
                setState(result, message);
            }
        }

        private void doDraw(Canvas canvas) {
            canvas.drawColor(Color.parseColor("#bae0ff"));
            canvas.drawRect(new RectF(0, mCanvasHeight - seaHeight, mCanvasWidth, mCanvasHeight), bottomSeaPaint);
            targetPlatImage.setBounds(targetXPos, mCanvasHeight - targetY,
                    targetXPos + targetWidth, mCanvasHeight - targetY + targetPlatHeight);
            targetPlatImage.draw(canvas);
            cloudLeftImage.setBounds(50, 200, 50 + leftCloudWidth, 200 + leftCloudHeight);
            cloudLeftImage.draw(canvas);
            cloudRightImage.setBounds(450, 500, 450 + rightCloudWidth, 500 + rightCloudHeight);
            cloudRightImage.draw(canvas);
            shipImage.setBounds(100, mCanvasHeight - seaHeight - 50, 100 + shipWidth,
                    mCanvasHeight - seaHeight - 50 + shipHeight);
            shipImage.draw(canvas);
            if (mMode != STATE_READY) {
                /**画文字*/
                canvas.drawText("Fuel:", 50, 50, textPaint);
                canvas.drawText("Velocity:", mCanvasWidth / 2 - 50, 50, textPaint);
                /**画最大速度表*/
                canvas.drawLine(mCanvasWidth / 2 + 50, 50, TARGET_SPEED_WIDTH + mCanvasWidth / 2 + 50, 50, speedPaint);
                /**画实时速度表*/
                canvas.drawLine(mCanvasWidth / 2 + 50, 50, currentSpeed + mCanvasWidth / 2 + 50, 50, currentSpeedPaint);
                /**画燃料表*/
                canvas.drawLine(80, 50, currentFule + 80, 50, fuelPaint);
            }
            /**画登月舱*/
            int yTop = (int) landerYPos;
            int xLeft = (int) landerXPos;
            if (mMode == STATE_LOSE) {
                mCrashedImage.setBounds(xLeft, yTop, xLeft + mLanderWidth, yTop + mLanderHeight);
                mCrashedImage.draw(canvas);
            } else if (mEngineFiring && !isFuelAllUsed) {
                mFiringImage.setBounds(xLeft, yTop, xLeft + mLanderWidth, yTop + mLanderHeight);
                mFiringImage.draw(canvas);
            } else {
                mLanderImage.setBounds(xLeft, yTop, xLeft + mLanderWidth, yTop + mLanderHeight);
                mLanderImage.draw(canvas);
            }
        }

        public void doScreenDown() {
            synchronized (mSurfaceHolder) {
                if (mMode == STATE_RUNNING && !isFuelAllUsed) {
                    setFiring(true);
                }
            }
        }

        public void doScreenUp() {
            synchronized (mSurfaceHolder) {
                if (mMode == STATE_RUNNING) {
                    setFiring(false);
                    if (landerYAcc <= 0) {
                        landerYAcc = ACC_INIT;
                    }
                }
            }
        }

        boolean doKeyDown(int keyCode, KeyEvent msg) {
            synchronized (mSurfaceHolder) {
                boolean okStart = false;
                if (keyCode == KeyEvent.KEYCODE_DPAD_UP) okStart = true;
                if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) okStart = true;
                if (keyCode == KeyEvent.KEYCODE_S) okStart = true;

                if (okStart
                        && (mMode == STATE_READY || mMode == STATE_LOSE || mMode == STATE_WIN)) {
                    // ready-to-start -> start
                    doStart();
                    return true;
                } else if (mMode == STATE_PAUSE && okStart) {
                    return true;
                } else if (mMode == STATE_RUNNING) {
                    // center/space -> fire
                    if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER
                            || keyCode == KeyEvent.KEYCODE_SPACE) {
                        setFiring(true);
                        return true;
                        // left/q -> left
                    } else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT
                            || keyCode == KeyEvent.KEYCODE_Q) {
                        return true;
                        // right/w -> right
                    } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT
                            || keyCode == KeyEvent.KEYCODE_W) {
                        return true;
                        // up -> pause
                    } else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
                        pause();
                        return true;
                    }
                }

                return false;
            }
        }

        boolean doKeyUp(int keyCode, KeyEvent msg) {
            boolean handled = false;

            synchronized (mSurfaceHolder) {
                if (mMode == STATE_RUNNING) {
                    if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER
                            || keyCode == KeyEvent.KEYCODE_SPACE) {
                        setFiring(false);
                        handled = true;
                    } else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT
                            || keyCode == KeyEvent.KEYCODE_Q
                            || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT
                            || keyCode == KeyEvent.KEYCODE_W) {
                        handled = true;
                    }
                }
            }

            return handled;
        }

        public void setFiring(boolean firing) {
            synchronized (mSurfaceHolder) {
                mEngineFiring = firing;
            }
        }

        public void setSurfaceSize(int width, int height) {
            // synchronized to make sure these all change atomically
            synchronized (mSurfaceHolder) {
                mCanvasWidth = width;
                mCanvasHeight = height;
            }
        }

        public void setState(int mode) {
            synchronized (mSurfaceHolder) {
                setState(mode, null);
            }
        }

        public void setState(int mode, CharSequence message) {
            synchronized (mSurfaceHolder) {
                mMode = mode;

                if (mMode == STATE_RUNNING) {
                    Message msg = mHandler.obtainMessage();
                    Bundle b = new Bundle();
                    b.putString("text", "");
                    b.putInt("viz", View.INVISIBLE);
                    msg.setData(b);
                    mHandler.sendMessage(msg);
                } else {
                    mEngineFiring = false;
                    Resources res = mContext.getResources();
                    CharSequence str = "";
                    if (mMode == STATE_READY)
                        str = res.getText(R.string.mode_ready);
                    else if (mMode == STATE_PAUSE)
                        str = res.getText(R.string.mode_pause);
                    else if (mMode == STATE_LOSE)
                        str = res.getText(R.string.mode_lose);
                    else if (mMode == STATE_WIN)
                        str = res.getString(R.string.mode_win_suffix);

                    if (message != null) {
                        str = message + "\n" + str;
                    }

                    Message msg = mHandler.obtainMessage();
                    Bundle b = new Bundle();
                    b.putString("text", str.toString());
                    b.putInt("viz", View.VISIBLE);
                    msg.setData(b);
                    mHandler.sendMessage(msg);
                }
            }
        }

        public synchronized void restoreState(Bundle savedState) {
            synchronized (mSurfaceHolder) {
                setState(STATE_PAUSE);
                mEngineFiring = false;

                mLanderWidth = savedState.getInt(KEY_LANDER_WIDTH);
                mLanderHeight = savedState.getInt(KEY_LANDER_HEIGHT);
            }
        }

        public Bundle saveState(Bundle map) {
            synchronized (mSurfaceHolder) {
                if (map != null) {
                    map.putInt(KEY_LANDER_WIDTH, mLanderWidth);
                    map.putInt(KEY_LANDER_HEIGHT, mLanderHeight);
                }
            }
            return map;
        }

        public void pause() {
            synchronized (mSurfaceHolder) {
                if (mMode == STATE_RUNNING) {
                    setState(STATE_PAUSE);
                }
            }
        }

        public void setRunning(boolean b) {
            mRun = b;
        }

    }

}
