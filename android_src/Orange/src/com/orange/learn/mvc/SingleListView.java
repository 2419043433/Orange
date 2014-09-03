package com.orange.learn.mvc;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ListView;

public class SingleListView extends FrameLayout
{
	private final ListView mListView;
	public SingleListView(Context context) {
		super(context);
		mListView = new ListView(context);
	}
}