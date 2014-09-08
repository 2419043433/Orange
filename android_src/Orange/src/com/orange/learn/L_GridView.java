package com.orange.learn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;

import com.orange.ui.view.AnimationItemView;

public class L_GridView extends FrameLayout {
	MyGridView mGridView;

	public L_GridView(Context context) {
		super(context);
		mGridView = new MyGridView(context);
		mGridView.setAdapter(new MyAdapter());
		mGridView.setNumColumns(5);
		this.addView(mGridView);
	}

	private class MyGridView extends GridView {

		public MyGridView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void dispatchDraw(Canvas canvas) {
			super.dispatchDraw(canvas);
			Log.v("xxxx", "dispatchdraw");
			for (int i = 0; i < getChildCount(); ++i) {
				AnimationItemView childView = (AnimationItemView) getChildAt(i);
				if (childView.isClickedFlag()) {
					Log.v("xxxx", "childdraw");
					break;
				}
			}
		}
	}

	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 100;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		private int randomInt() {
			return (int) (Math.random() * 255) % 255;
		}

		private int makeColor() {
			return Color.argb(255, randomInt(), randomInt(), randomInt());
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Log.v("xxx", "getView:" + position);
			AnimationItemView view = null;
			if (null == convertView) {
				view = new AnimationItemView(getContext());
				TextView tView = new TextView(getContext());
				view.setContentView(tView);
				view.setTag(tView);
				tView.setBackgroundColor(makeColor());
			} else {
				view = (AnimationItemView) convertView;
			}
			TextView textView = (TextView) view.getTag();
			textView.setText("item:" + position);

			return view;
		}
	}

}
