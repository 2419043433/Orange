package com.orange.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

public class HorizentalListView extends FrameLayout {

	private final ListView mListView;
	private ListView.LayoutParams mListViewLayoutParams = new ListView.LayoutParams(0,0);

	public HorizentalListView(Context context) {
		super(context);
		mListView = new ListView(context);
		addView(mListView);
	}

	public void setAdapter(ListAdapter adapter) {
		mListView.setAdapter(adapter);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		Log.v("H.onMesure", "w:" + widthMeasureSpec + " h:" + heightMeasureSpec);
		//if(FrameLayout.LayoutParams.WRAP_CONTENT == getLayoutParams().height)
		mListViewLayoutParams.width = getLayoutParams().height;
		mListViewLayoutParams.height = getLayoutParams().width;
		mListView.setLayoutParams(mListViewLayoutParams);
		mListView.measure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		mListView.layout(left, top, right, bottom);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// canvas.rotate(90);
		super.onDraw(canvas);
	}
}