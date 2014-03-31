package com.orange.learn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.widget.LinearLayout;

public class L_Drawable extends LinearLayout {

	public L_Drawable(Context context) {
		super(context);
		init(context);
	}
	private GradientDrawable arrGd[] = null;
	GradientDrawable mDrawable = null;
	Rect mRect = new Rect(0,0, 100, 100);
	private void init(Context context) {
		arrGd = new GradientDrawable[3];
		for(int i = 0; i < 3; ++ i)
		{
			arrGd[i] = new GradientDrawable();
		}
	      mDrawable = new GradientDrawable(
	              GradientDrawable.Orientation.TL_BR, new int[] { 0xFF0000ff,
	                  0xFFffFF00, 0xFF00ffFF });
	          mDrawable.setShape(GradientDrawable.RECTANGLE);
	          mDrawable.setGradientRadius((float) (Math.sqrt(2) * 60));
		this.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		setWillNotDraw(false);
	}
    static void setCornerRadii(GradientDrawable drawable, float r0,
            float r1, float r2, float r3) {
          drawable.setCornerRadii(new float[] { r0, r0, r1, r1, r2, r2, r3,
              r3 });
        }
	private int[] colors = {0x00000000, 0x00ff0000};

	@SuppressLint({ "NewApi", "DrawAllocation" })
	@Override
	protected void onDraw(Canvas canvas) {

		canvas.drawColor(Color.RED);
		mDrawable.setBounds(mRect);
	      float r = 16;

	      canvas.translate(10, 10);
	      mDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
	      setCornerRadii(mDrawable, r, r, 0, 0);
	      mDrawable.draw(canvas);

	      canvas.translate(mRect.width() + 10, 0);
	      mDrawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
	      setCornerRadii(mDrawable, 0, 0, r, r);
	      mDrawable.draw(canvas);
	      
	      canvas.translate(-(mRect.width() + 10), mRect.height() + 10);
	      mDrawable.setGradientType(GradientDrawable.SWEEP_GRADIENT);
	      setCornerRadii(mDrawable, 0, r, r, 0);
	      mDrawable.draw(canvas);

	      canvas.translate(10 + mRect.width(), 0);
	      mDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
	      setCornerRadii(mDrawable, r, 0, 0, r);
	      mDrawable.draw(canvas);

	      canvas.translate(-(mRect.width() + 10), mRect.height() + 10);
	      mDrawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
	      setCornerRadii(mDrawable, r, 0, r, 0);
	      mDrawable.draw(canvas);

	      canvas.translate(10 + mRect.width(), 0);
	      mDrawable.setGradientType(GradientDrawable.SWEEP_GRADIENT);
	      setCornerRadii(mDrawable, 0, r, 0, r);
	      mDrawable.draw(canvas);

	      canvas.translate(-(mRect.width() + 10), mRect.height() + 10);
	      GradientDrawable gd = (GradientDrawable)mDrawable.mutate();
	      gd.setBounds(mRect);
	      gd.setShape(GradientDrawable.RECTANGLE);
	      gd.setOrientation(GradientDrawable.Orientation.RIGHT_LEFT);
	      gd.setGradientType(GradientDrawable.LINEAR_GRADIENT);
	      //gd.setColors(colors);
	      gd.draw(canvas);
	}
}