package com.orange.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.widget.FrameLayout;

/**
 * 
 * The Class is pure color or gradual change color for Round rect View 
 *
 */
public class RoundRectView extends FrameLayout
{
    private final Paint mPaint;
    private float mRx = 0f;
    private float mRy = 0f;
    private int[] mColors = new int[3];
    
    public RoundRectView(Context context)
    {
        super(context);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void dispatchDraw(Canvas canvas)
    {
        float positions[] = new float[3];  
        
        positions[0] = 0;  
        positions[1] = 0.5f;  
        positions[2] = 1;  
          
        LinearGradient shader = new LinearGradient(  
                0, 0,  
                getWidth(), getHeight(),  
                mColors,  
                positions,  
                TileMode.MIRROR);  
        mPaint.setShader(shader);
        canvas.drawRoundRect(new RectF(0, 0, getWidth(), getHeight()), mRx, mRy, mPaint);
    }
    
    
    public void setColor(int color)
    {
        mColors[0] = mColors[1] = mColors[2] = color;
        invalidate();
    }
    
    
    public void setColors(int[] colors)
    {
        mColors = colors;
        invalidate();
    }

    
    public void setRadius(float rx,float ry)
    {
        mRx = rx;
        mRy = ry;
        invalidate();
    }
    
    public void setAntiAlias(boolean antiAlias)
    {
        mPaint.setAntiAlias(antiAlias);
        invalidate();
    }
    
}
