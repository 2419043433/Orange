package com.orange.time_machine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

public class Rectangle extends FrameLayout {

	private Rect mRect = new Rect();
	public Rectangle(Context context) {
		super(context);
	}
	
	@Override
	public void draw(Canvas canvas)
	{
		getDrawingRect(mRect);
		mRect.left += ShapeConfig.getInstance().getPadding();
		mRect.top += ShapeConfig.getInstance().getPadding();
		mRect.right -= ShapeConfig.getInstance().getPadding();
		mRect.bottom -= ShapeConfig.getInstance().getPadding();
		canvas.drawRect(mRect, ShapeConfig.getInstance().getPaint());
	}

}
