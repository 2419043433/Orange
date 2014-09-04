package com.orange.learn;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.orange.util.LayoutParamsUtil;

public class L_Scroller extends LinearLayout
{
    private Scroller mScroller;
    private GestureDetector mGestureDetector;
    private GestureDetector.SimpleOnGestureListener mGuestureListener;

    public L_Scroller(Context context)
    {
        super(context);
        initComponents(context);
        setupListeners(context);
    }

    private void initComponents(Context context)
    {
        mScroller = new Scroller(context);

        this.setOrientation(LinearLayout.HORIZONTAL);

        for (int i = 0; i < 10; ++i)
        {
            TextView textView = new TextView(context);
            textView.setLayoutParams(LayoutParamsUtil.LINEARLAYOUT_WRAP_CONTENT);
            textView.setText("text_____________" + i);
            textView.setBackgroundColor(Color.GREEN);
            this.addView(textView);
        }
    }

    private void setupListeners(Context context)
    {
        mGuestureListener = new GestureDetector.SimpleOnGestureListener()
        {
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
            {
                Log.v("xxx", "onScroll:[distanceX:" + distanceX + "][distanceY:" + distanceY + "]");
                smoothScrollBy((int)distanceX, (int)distanceY);
                return true;
            }

            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
            {
                Log.v("xxx", "onFling:[velocityX:" + velocityX + "][velocityY:" + velocityY + "]");
                return false;
            }
        };

        mGestureDetector = new GestureDetector(context, mGuestureListener);
    }

    public void smoothScrollTo(int fx, int fy)
    {
        int dx = fx - mScroller.getFinalX();
        int dy = fy - mScroller.getFinalY();
        smoothScrollBy(dx, dy);
    }

    public void smoothScrollBy(int dx, int dy)
    {
        mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx, dy);
        invalidate();
    }

    @Override
    public void computeScroll()
    {
        if (mScroller.computeScrollOffset())
        {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
        super.computeScroll();
    }

    boolean mDownTouch = false;

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        super.onTouchEvent(event);

        // Listening for the down and up touch events
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                mDownTouch = true;
                return true;

            case MotionEvent.ACTION_UP:
                if (mDownTouch)
                {
                    mDownTouch = false;
                    performClick();
                    return true;
                }
        }
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean performClick()
    {
        super.performClick();
        return true;
    }
}