package com.orange.time_machine;

import android.graphics.Color;
import android.graphics.Paint;

public class ShapeConfig {
	public static ShapeConfig instance_;
	public static ShapeConfig getInstance() {
		if (null == instance_) {
			instance_ = new ShapeConfig();
		}
		return instance_;
	}

	private int padding_;
	private Paint paint_;
	private ShapeConfig() {
		padding_ = 10;
		paint_ = new Paint();
		paint_.setColor(Color.BLACK);
	}
	
	public  void setColor(int color)
	{
		paint_.setColor(color);
	}
	
	public void setPadding(int padding)
	{
		padding_ = padding;
	}
	
	public int getPadding()
	{
		return padding_;
	}
	
	public Paint getPaint()
	{
		return paint_;
	}


}
