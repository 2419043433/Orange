package com.orange.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.TextView;

public class BookItemView extends TextView {

	public static int kVertical = 1;
	public static int kHorizontal = 2;

	private int mMode = 2;

	public BookItemView(Context context) {
		super(context);
	}

	public void setMode(int mode) {
		mMode = mode;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int[] pos = new int[2];
		this.getLocationInWindow(pos);
		// if (kHerizontal == mMode) {
		// canvas.rotate(90);
		// }
		super.onDraw(canvas);
	}
}