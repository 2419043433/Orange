package com.orange.learn.mvc;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ArrayStringListAdapter extends BaseAdapter
{
	private final ArrayStringDataSet mDataSet;
	
	public ArrayStringListAdapter(ArrayStringDataSet dataSet)
	{
		mDataSet = dataSet;
	}
	@Override
	public int getCount() {
		return mDataSet.getCount();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ItemView v = (ItemView)convertView;
		if(null == v)
		{
			v = new ItemView(parent.getContext(), position);
		}
		v.setText(mDataSet.get(position));
		
		return v;
	}
	
}