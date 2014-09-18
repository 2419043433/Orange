package com.orange.time_machine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

@SuppressLint("NewApi")
public class ToolBox extends FrameLayout {

	private ListView list_;
	public ToolBox(Context context) {
		super(context);
		initCompoment();
	}
	
	private void initCompoment()
	{
		list_ = new ListView(getContext());
		list_.setAdapter(new Adapter());
		addView(list_);
		
		setAlpha((float) 0.5);
	}
	
	class Adapter extends BaseAdapter
	{

		@Override
		public int getCount() {
			return 100;
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			Rectangle view = null;
			if(null == arg1)
			{
				view = new Rectangle(getContext());
				view.setLayoutParams(new android.view.ViewGroup.LayoutParams(100,100));
			}
			return view;
		}
		
	}
}
