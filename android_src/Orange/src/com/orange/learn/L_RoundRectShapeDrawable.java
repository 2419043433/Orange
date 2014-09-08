package com.orange.learn;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;

public class L_RoundRectShapeDrawable extends ShapeDrawable {
	private Paint mPaint = new Paint();
	int radius = 5;
	int width = 2;
  float[] outerR = new float[] { radius, radius, radius, radius, radius, radius, radius, radius }; 
  RectF   inset = new RectF(width, width, width, width); 
  float[] innerR = new float[] { radius, radius, radius, radius, radius, radius, radius, radius };
  RoundRectShape rrsRectShape = new RoundRectShape(outerR, inset, innerR);
	public L_RoundRectShapeDrawable(Shape shape) {
		super(shape);
		mPaint.setColor(Color.BLUE);
	}

	@Override
	protected void onDraw(Shape shape, Canvas canvas, Paint paint) {

		super.onDraw(shape, canvas, mPaint);

	}

}
