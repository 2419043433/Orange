package com.orange.time_machine;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;

class TouchEventListener implements View.OnTouchListener
{
    private boolean mHasActionDown = false;
    private final Delegate mDelegate;

    public TouchEventListener(Delegate delegate)
    {
        assert (null != delegate);

        mDelegate = delegate;
    }

    public static interface Delegate
    {
        public void onActionDown(View v, MotionEvent event);

        public void onActionUp(View v, MotionEvent event);

        public void onActionMove(View v, MotionEvent event);
        
        public void onActionOutside(View v, MotionEvent event);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        if (null == mDelegate)
        {
            return false;
        }
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                {
                    mHasActionDown = true;
                    mDelegate.onActionDown(v, event);
                }
                break;
            case MotionEvent.ACTION_UP:
                {
                    mHasActionDown = false;
                    mDelegate.onActionUp(v, event);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                {
                    if (mHasActionDown)
                    {
                        mDelegate.onActionMove(v, event);
                    }
                }
                break;
            case MotionEvent.ACTION_OUTSIDE:
            {
                if (mHasActionDown)
                {
                    mDelegate.onActionOutside(v, event);
                }
            }
            break;

        }
        return true;
    }
}