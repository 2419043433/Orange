package com.orange.ui.view_model;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CompositeListAdapter extends BaseAdapter implements
		DelegatedDataObserver.Delegate {

	private final ArrayList<BaseAdapter> mSubAdapters;
	private final DelegatedDataObserver mDataObserver;

	public CompositeListAdapter() {
		mSubAdapters = new ArrayList<BaseAdapter>();
		mDataObserver = new DelegatedDataObserver(this);
	}

	public void add(BaseAdapter adapter) {
		adapter.registerDataSetObserver(mDataObserver);
		mSubAdapters.add(adapter);
	}

	public void remove(BaseAdapter adapter) {
		mSubAdapters.remove(adapter);
		adapter.unregisterDataSetObserver(mDataObserver);
	}

	public int getSubAdapterCount() {
		return mSubAdapters.size();
	}

	@Override
	public int getCount() {
		int sum = 0;
		for (BaseAdapter la : mSubAdapters) {
			sum += la.getCount();
		}
		return sum;
	}

	/*
	 * 3, [3, 5]--->return true 5, [3, 5]--->return false
	 */
	private boolean isInRangeFloor(int number, int low, int up) {
		return number >= low && number < up;
	}

	private Object[] getSubAdapterInfoByItemPosition(int position) {
		if (position < 0 || position >= getCount()) {
			return null;
		}

		int start = 0;
		for (int i = 0; i < mSubAdapters.size(); ++i) {
			BaseAdapter cur = mSubAdapters.get(i);
			if (isInRangeFloor(position, start, start + cur.getCount())) {
				Object[] ret = new Object[2];
				ret[0] = cur;
				ret[1] = position - start;
				return ret;
			}

			start += cur.getCount();
		}

		return null;
	}

	@Override
	public Object getItem(int position) {
		Object[] info = getSubAdapterInfoByItemPosition(position);
		if (null == info) {
			return null;
		}
		return ((BaseAdapter) info[0]).getItem((Integer) info[1]);
	}

	@Override
	public long getItemId(int position) {
		Object[] info = getSubAdapterInfoByItemPosition(position);
		if (null == info) {
			return -1;
		}
		return ((BaseAdapter) info[0]).getItemId((Integer) info[1]);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Object[] info = getSubAdapterInfoByItemPosition(position);
		if (null == info) {
			return null;
		}
		
		return ((BaseAdapter) info[0]).getView((Integer) info[1], convertView, parent);
	}

	@Override
	public void onDataChanged() {
		notifyDataSetChanged();
	}
}