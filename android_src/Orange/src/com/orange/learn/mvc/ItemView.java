package com.orange.learn.mvc;

import android.content.Context;
import android.widget.TextView;

public class ItemView extends TextView
{
	private int mPos = -1;
	public ItemView(Context context, int pos) {
		super(context);
		mPos = pos;
	}
	public void setPos(int pos)
	{
		mPos = pos;
	}
	public int getPos()
	{
		return mPos;
	}
}