package com.orange.time_machine;

import android.graphics.Color;
import android.graphics.Paint;

public class ShapeConfig
{
    public static ShapeConfig sInstance;

    public static ShapeConfig getInstance()
    {
        if (null == sInstance)
        {
            sInstance = new ShapeConfig();
        }
        return sInstance;
    }

    private int mPadding;
    private Paint mPaint;

    private ShapeConfig()
    {
        mPadding = 10;
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
    }

    public void setColor(int color)
    {
        mPaint.setColor(color);
    }

    public void setPadding(int padding)
    {
        mPadding = padding;
    }

    public int getPadding()
    {
        return mPadding;
    }

    public Paint getPaint()
    {
        return mPaint;
    }

    public void setLineWidth(float width)
    {
        mPaint.setStrokeWidth(width);
    }

}
