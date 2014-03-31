package com.orange.ui.view_model;

import android.database.DataSetObserver;

public class DelegatedDataObserver extends DataSetObserver  {
	
	private Delegate mDelegate = null;

	public DelegatedDataObserver(Delegate delegate) {
		mDelegate = delegate;
	}
	
	@Override
    public void onChanged() {
		if(null == mDelegate)
		{
			return;
		}
		mDelegate.onDataChanged();
    }
	
	public interface Delegate {
		void onDataChanged();
	}

}