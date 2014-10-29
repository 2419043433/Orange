package com.orange.learn;

import android.R.drawable;
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
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class L_Animation extends FrameLayout
{
    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
    private View view1;
    private View view2;
    private int mCurPosition;

    Bitmap mBmp1 = Bitmap.createBitmap(WIDTH, HEIGHT, Config.ARGB_8888);
    Bitmap mBmp2 = Bitmap.createBitmap(WIDTH, HEIGHT, Config.ARGB_8888);
    Canvas mCanvas1 = new Canvas(mBmp1);
    Canvas mCanvas2 = new Canvas(mBmp2);

    public L_Animation(Context context)
    {
        super(context);

        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(WIDTH, HEIGHT));
        view1 = new View(getContext());
        view1.setLayoutParams(new FrameLayout.LayoutParams(WIDTH, HEIGHT));
        //view1.setBackgroundColor(Color.RED);
        view1.setBackgroundResource(drawable.ic_delete);

        view2 = new View(getContext());
        view2.setLayoutParams(new FrameLayout.LayoutParams(WIDTH, HEIGHT));
        view2.setBackgroundResource(drawable.btn_star);
        //view2.setBackgroundColor(Color.YELLOW);
        
        frameLayout.addView(view1);
        frameLayout.addView(view2);
        view1.setVisibility(view1.INVISIBLE);
        view2.setVisibility(view1.INVISIBLE);

        addView(frameLayout);
        frameLayout.setBackgroundColor(Color.BLUE);
        setBackgroundColor(Color.BLACK);
    }

    private Rect fromRect = new Rect();
    private Rect toRect = new Rect();

    @Override
    public void dispatchDraw(Canvas canvas)
    {
        super.dispatchDraw(canvas);
        fromRect.set(0, mCurPosition, WIDTH, HEIGHT);
        toRect.set(0, 0, WIDTH, HEIGHT -mCurPosition);
        view1.draw(mCanvas1);
        canvas.drawBitmap(mBmp1, fromRect, toRect, null);
        view2.draw(mCanvas2);
        fromRect.set(0, 0, WIDTH, mCurPosition);
        toRect.set(0, HEIGHT - mCurPosition, WIDTH, HEIGHT);
        canvas.drawBitmap(mBmp2, fromRect, toRect, null);
    }

    @SuppressLint("NewApi")
    public void make()
    {

        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, HEIGHT);

        valueAnimator.addUpdateListener(new AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animator)
            {
                mCurPosition = (Integer) animator.getAnimatedValue();
                Log.v("xxx", "" + mCurPosition);
                invalidate();
            }
        });

        valueAnimator.setDuration(10000).start();

    }
}