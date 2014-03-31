package com.orange.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;


public class Portal extends RelativeLayout {
	private BookSet mBookSet = null;

	public Portal(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		mBookSet = new BookSet(context);
		mBookSet.setBackgroundColor(Color.RED);
		mBookSet.setLayoutParams(new FrameLayout.LayoutParams(400,400));
//				FrameLayout.LayoutParams.MATCH_PARENT,
//				FrameLayout.LayoutParams.MATCH_PARENT));
		addView(mBookSet, new FrameLayout.LayoutParams(400,400));
	}

}