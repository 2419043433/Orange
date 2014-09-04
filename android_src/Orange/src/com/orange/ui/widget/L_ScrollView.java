package com.orange.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.orange.util.LayoutParamsUtil;

public class L_ScrollView extends ScrollView
{
    private LinearLayout mContent;
    private GestureDetector mGestureDetector;
    private GestureDetector.SimpleOnGestureListener mGestureListener;

    public L_ScrollView(Context context)
    {
        super(context);
        initComponents(context);
    }

    private void initComponents(Context context)
    {
        mContent = new LinearLayout(context);
        mContent.setLayoutParams(LayoutParamsUtil.FRAMELAYOUT_MATCH_PARENT);
        mContent.setOrientation(LinearLayout.HORIZONTAL);

        for (int i = 0; i < 10; ++i)
        {
            TextView textView = new TextView(context);
            textView.setLayoutParams(LayoutParamsUtil.LINEARLAYOUT_WRAP_CONTENT);
            textView.setText("text_____________" + i);
            textView.setBackgroundColor(Color.GREEN);
            mContent.addView(textView);
        }
        this.addView(mContent);

        mGestureListener = new GestureDetector.SimpleOnGestureListener()
        {
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
            {
                Log.v("xxx", "onScroll:[distanceX:" + distanceX + "][distanceY:" + distanceY + "]");
                smoothScrollBy((int) distanceX, (int) distanceY);
                return true;
            }

            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
            {
                Log.v("xxx", "onFling:[velocityX:" + velocityX + "][velocityY:" + velocityY + "]");
                return false;
            }
        };

        mGestureDetector = new GestureDetector(context, mGestureListener);
    }

    public boolean onTouchEvent(MotionEvent ev)
    {
        mGestureDetector.onTouchEvent(ev);
        return true;
    }

}
