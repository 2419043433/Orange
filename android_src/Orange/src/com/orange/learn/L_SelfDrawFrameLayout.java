package com.orange.learn;

import android.R.integer;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PixelFormat;
import android.graphics.Region.Op;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.FrameLayout;

public class L_SelfDrawFrameLayout extends FrameLayout
{
    int mRadius = 50;
    Bitmap mCurPage = null;
    Canvas mCanvas = null;
    private Path mPath = new Path();
    private Paint mPaint = new Paint();
    
    private DisplayMetrics mDisplayMetrics;
    
    private int dipToPixel(int dip)
    {
        float scale = ((float)mDisplayMetrics.densityDpi) / 160;
        return (int)scale * dip;
    }

    @SuppressLint("NewApi")
    public L_SelfDrawFrameLayout(Context context)
    {
        super(context);
        mDisplayMetrics = getResources().getDisplayMetrics();
        mCurPage = Bitmap.createBitmap(2000, 2000, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mCurPage);
        setBackgroundColor(Color.BLACK);
        try
        {
            if (android.os.Build.VERSION.SDK_INT >= 11)
            {
                setLayerType(LAYER_TYPE_SOFTWARE, null);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // TODO: make new objects as member
    private static Path clipRoundRectPath(RectF src, int radius)
    {
        Path path = new Path();
        path.moveTo(src.left, src.top + radius);
        path.arcTo(new RectF(src.left, src.top, src.left + 2 * radius, src.top + 2 * radius), 180, 90);
        path.lineTo(src.right - radius, src.top);
        path.arcTo(new RectF(src.right - 2 * radius, src.top, src.right, src.top + 2 * radius), 270, 90);
        path.lineTo(src.right, src.bottom - radius);
        path.arcTo(new RectF(src.right - 2 * radius, src.bottom - 2 * radius, src.right, src.bottom), 0, 90);
        path.lineTo(src.left + radius, src.bottom);
        path.arcTo(new RectF(src.left, src.bottom - 2 * radius, src.left + 2 * radius, src.bottom), 90, 90);
        path.close();
        return path;
    }
    
    private void drawPoint(Canvas canvas,int x, int y)
    {
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        canvas.drawCircle((float)x, (float)y, 3, paint);
    }
    
    private void drawPoint(Canvas canvas,float x, float y)
    {
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(x, y, 3, paint);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        Log.v("xxx", "onDraw");
    }

    private PorterDuffXfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    @Override
    protected void dispatchDraw(Canvas canvas)
    {

        // super.dispatchDraw(canvas);

        int kOutterRadius = 50;
        int kLineWidth = 30;
        long time = System.currentTimeMillis();
        Rect rect = new Rect();
        RectF rectf = new RectF();
        this.getDrawingRect(rect);
        rectf.set(rect.left + 100, rect.top + 100, rect.right - 100, rect.bottom - 100);
        Path path = mPath;
        path.addRoundRect(rectf, kOutterRadius, kOutterRadius, Direction.CW);
        Paint p = mPaint;
        p.setAntiAlias(true);
        p.setStyle(Style.STROKE);
        //p.setStrokeWidth(kLineWidth);
        p.setColor(Color.GREEN);
        canvas.drawPath(path, p);
        
        path.reset();
        RectF rectF2 = new RectF();
        rectF2.set(rectf.left + kLineWidth, rectf.top + kLineWidth, rectf.right - kLineWidth, rectf.bottom - kLineWidth);
        path.addRoundRect(rectF2, kOutterRadius - kLineWidth, kOutterRadius - kLineWidth, Direction.CW);
        canvas.drawRect(rectF2, p);
        canvas.save();
        canvas.clipPath(path);
        super.dispatchDraw(mCanvas);
        canvas.drawBitmap(mCurPage, 0, 0, p);
        canvas.restore();
        drawPoint(canvas, rect.left + 100, rect.top + 100);
        drawPoint(canvas, rectF2.left, rectF2.top);

        Log.v("xxx", "multiselfdraw:" + (System.currentTimeMillis() - time));
    }

}
