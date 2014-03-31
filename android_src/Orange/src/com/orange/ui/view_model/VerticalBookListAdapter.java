package com.orange.ui.view_model;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.orange.ui.view.BookItemView;

public class VerticalBookListAdapter extends BaseAdapter {

	private ArrayList<String> mData = new ArrayList<String>();
	private Context mContext = null;

	public VerticalBookListAdapter(Context context) {
		mContext = context;
		for(int i = 0; i < 20; ++ i)
		{
			mData.add("test" + i);
		}
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BookItemView itemView = (BookItemView) convertView;
		if (null == itemView) {
			itemView = new BookItemView(mContext);
			itemView.setMode(BookItemView.kVertical);
		}
		itemView.setText((String) getItem(position));
		return itemView;
	}
}