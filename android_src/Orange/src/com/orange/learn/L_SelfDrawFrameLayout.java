package com.orange.learn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;
import android.graphics.Path.FillType;
import android.graphics.Rect;
import android.graphics.RectF;
import android.widget.FrameLayout;

public class L_SelfDrawFrameLayout extends FrameLayout
{
    int mRadius = 20;

    public L_SelfDrawFrameLayout(Context context)
    {
        super(context);
    }

    //TODO: make new objects as member
    private static Path clipRoundRectPath(Rect src, int radius)
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

    @Override
    protected void dispatchDraw(Canvas canvas)
    {
        Rect rect = new Rect();
        this.getDrawingRect(rect);
        rect.set(rect.left + 100, rect.top + 100, rect.right - 100, rect.bottom - 100);
        Path path = clipRoundRectPath(rect, mRadius);
        
        
        path.reset();
        path.moveTo(0, 0);
        path.lineTo(500, 0);
        path.lineTo(500, 500);
        path.arcTo(new RectF(300, 400, 500, 600), 0, 180);
        path.lineTo(0,500);

        
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setStyle(Style.STROKE);
        p.setColor(Color.GREEN);
        canvas.drawPath(path, p);

        p.setColor(Color.RED);
        p.setStyle(Style.FILL);
        canvas.save();
        canvas.clipPath(path);
        canvas.drawRect(rect, p);
        canvas.restore();



        //canvas.drawPath(path, p);
        // canvas.drawArc(new RectF(300,300,500,500), 180, 90, true, p);
        // canvas.drawColor(Color.RED);
    }

}
