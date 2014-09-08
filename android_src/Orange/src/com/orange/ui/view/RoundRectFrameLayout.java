package com.orange.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.widget.FrameLayout;

public class RoundRectFrameLayout extends FrameLayout
{
    private final static int ROUND_RECT_RADIUS = 3;
    private Canvas mMemCanvas = new Canvas();
    private Bitmap mMemBitmap = null;
    private int mRadius = ROUND_RECT_RADIUS;
    private Rect mRect = new Rect();
    private RectF mContentRect = new RectF();
    private Path mPath = new Path();
    private Paint mPaint = new Paint();
    private Paint mClearPaint = new Paint();
    private Xfermode mClearMode = new PorterDuffXfermode(Mode.CLEAR);

    public RoundRectFrameLayout(Context context)
    {
        super(context);
        mPaint.setAntiAlias(true);
        mClearPaint.setXfermode(mClearMode);
    }
    
    public void setRadius(int radius)
    {
        mRadius = radius;
    }
    
    @Override
    public void dispatchDraw(Canvas canvas)
    {
        mMemBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
        mMemCanvas.setBitmap(mMemBitmap);
        //clear canvas
        mMemCanvas.drawPaint(mClearPaint);
        //draw content to memory canvas
        super.dispatchDraw(mMemCanvas);
        //make round rect path
        getDrawingRect(mRect);
        mContentRect.set(mRect);
        mPath.reset();
        mPath.addRoundRect(mContentRect, mRadius, mRadius, Direction.CW);
        //clip path and draw
        canvas.save();
        canvas.clipPath(mPath);
        canvas.drawBitmap(mMemBitmap, 0, 0, mPaint);
        canvas.restore();
        mMemBitmap.recycle();
        mMemCanvas.setBitmap(null);
    }

}
