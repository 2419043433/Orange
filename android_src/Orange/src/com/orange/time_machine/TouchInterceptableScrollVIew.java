package com.orange.time_machine;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class TouchInterceptableScrollVIew extends ScrollView
{
    private boolean mFlag = false;
    public TouchInterceptableScrollVIew(Context context)
    {
        super(context);
    }
    
    @Override
    public boolean dispatchTouchEvent(MotionEvent event)
    {
        boolean handled = super.dispatchTouchEvent(event);
        return mFlag ? false : handled;
    }
    
    public void setFlag(boolean flag)
    {
        mFlag = flag;
    }

}
