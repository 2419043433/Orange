package com.orange.ui.animation;

import android.R.drawable;
import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SwitchableFrameLayout extends LinearLayout
{
    private static int WIDTH = -1;
    private static int HEIGHT = -1;
    private int mCurPosition;
    private FrameLayout mDrawingArea;

    private enum State
    {
        Normal, Animation, Animation_Pause
    }

    private State mState = State.Normal;

    public SwitchableFrameLayout(Context context)
    {
        super(context);

        mDrawingArea = new FrameLayout(getContext());
        mDrawingArea.setLayoutParams(new LinearLayout.LayoutParams(600, 600));
        View v = new View(getContext());
        v.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        v.setBackgroundResource(drawable.ic_delete);
        mDrawingArea.addView(v);

        v = new View(getContext());
        v.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        v.setBackgroundResource(drawable.btn_star);
        mDrawingArea.addView(v);

        v = new View(getContext());
        v.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        v.setBackgroundResource(drawable.btn_plus);
        mDrawingArea.addView(v);

        v = new View(getContext());
        v.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        v.setBackgroundResource(drawable.btn_radio);
        mDrawingArea.addView(v);

        mDrawingArea.setVisibility(View.INVISIBLE);

        Button start = new Button(context);
        start.setText("start");
        start.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                start();
            }
        });
        Button stop = new Button(context);
        stop.setText("stop");
        stop.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                stop();
            }
        });
        addView(start);
        addView(stop);
        addView(mDrawingArea);
        mDrawingArea.setBackgroundColor(Color.BLUE);
        setBackgroundColor(Color.BLACK);
    }

    private Rect fromRect = new Rect();
    private Rect toRect = new Rect();

    @Override
    public void dispatchDraw(Canvas canvas)
    {
        if (WIDTH < 0)
        {
            Rect r = new Rect();
            getDrawingRect(r);
            WIDTH = r.width();
            HEIGHT = r.height();
        }

        if (mState == State.Normal)
        {
            mDrawingArea.setVisibility(VISIBLE);
            super.dispatchDraw(canvas);
            return;
        }

        mDrawingArea.setVisibility(View.INVISIBLE);

        // should judge state
        if (mBmp1 == null)
        {
            createResource();
        }

        fromRect.set(0, mCurPosition, WIDTH, HEIGHT);
        toRect.set(0, 0, WIDTH, HEIGHT - mCurPosition);
        mCanvas1.drawColor(Color.BLACK);
        mViewProvider.getAt(mCurIndex).draw(mCanvas1);
        canvas.drawBitmap(mBmp1, fromRect, toRect, null);
        mCanvas2.drawColor(Color.BLACK);
        mViewProvider.getAt(mNextIndex).draw(mCanvas2);
        fromRect.set(0, 0, WIDTH, mCurPosition);
        toRect.set(0, HEIGHT - mCurPosition, WIDTH, HEIGHT);
        canvas.drawBitmap(mBmp2, fromRect, toRect, null);
    }

    private void createResource()
    {
        mBmp1 = Bitmap.createBitmap(WIDTH, HEIGHT, Config.ARGB_8888);
        mCanvas1 = new Canvas(mBmp1);
        mBmp2 = Bitmap.createBitmap(WIDTH, HEIGHT, Config.ARGB_8888);
        mCanvas2 = new Canvas(mBmp2);
    }

    private void destroyResource()
    {
        mBmp1.recycle();
        mBmp1 = null;
        mCanvas1 = null;

        mBmp2.recycle();
        mBmp2 = null;
        mCanvas2 = null;
    }

    private int mCurIndex = -1;
    private int mNextIndex = -1;

    public void start()
    {
        // if child count
        if (mViewProvider.getCount() < 2)
        {
            return;
        }
        mState = State.Animation;
        mCurIndex = 0;
        mNextIndex = 1;
        // doInit();
        doLoop();
    }

    private void doLoop()
    {
        switch (mState)
        {
            case Normal:
                // Animation->Normal
                // release resource here
                doStop();
                break;
            case Animation:
                doAnimation();
                break;
            case Animation_Pause:
                doPause();
                break;
            default:
                break;
        }
    }

    public void stop()
    {
        // doUnInit();
        mState = State.Normal;
        doLoop();
    }

    private void doStop()
    {
        mValueAnimator.cancel();
    }

    private ViewProvider mViewProvider = new ViewProvider()
    {

        @Override
        public int getCount()
        {
            return mDrawingArea.getChildCount();
        }

        @Override
        public View getAt(int index)
        {
            return mDrawingArea.getChildAt(index);
        }
    };

    private void next()
    {
        mCurIndex = mNextIndex;
        mNextIndex++;
        if (mNextIndex == mViewProvider.getCount())
        {
            mNextIndex = 0;
        }

        mState = State.Animation;
        doLoop();
    }

    Bitmap mBmp1;
    Canvas mCanvas1;

    Bitmap mBmp2;
    Canvas mCanvas2;

    private void doInit()
    {

    }

    private void doUnInit()
    {
    }

    private ValueAnimator mValueAnimator;

    @SuppressLint("NewApi")
    public void doAnimation()
    {
        if (null == mValueAnimator)
        {
            mValueAnimator = ValueAnimator.ofInt(0, HEIGHT);
            mValueAnimator.addUpdateListener(new AnimatorUpdateListener()
            {
                @Override
                public void onAnimationUpdate(ValueAnimator animator)
                {
                    mCurPosition = (Integer) animator.getAnimatedValue();
                    postInvalidate();
                }
            });
            mValueAnimator.addListener(new AnimatorListener()
            {

                @Override
                public void onAnimationStart(Animator animation)
                {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onAnimationRepeat(Animator animation)
                {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onAnimationEnd(Animator animation)
                {
                    mState = State.Animation_Pause;
                    doLoop();
                }

                @Override
                public void onAnimationCancel(Animator animation)
                {
                    destroyResource();
                }
            });
            mValueAnimator.setDuration(3000);
            mValueAnimator.setInterpolator(new TimeInterpolator()
            {

                @Override
                public float getInterpolation(float input)
                {
                    return input;
                }
            });
        }

        mValueAnimator.start();
    }

    public void doPause()
    {
        new Handler().postDelayed(new Runnable()
        {

            @Override
            public void run()
            {
                next();
            }
        }, 1000);
    }

    public static interface ViewProvider
    {
        int getCount();

        View getAt(int index);
    }
}