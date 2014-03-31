package com.orange.ui.widget;

import android.content.Context;
import android.widget.FrameLayout;

import com.orange.ui.view.HorizentalListView;
import com.orange.ui.view_model.CompositeListAdapter;
import com.orange.ui.view_model.VerticalBookListAdapter;

/**
 * indicates a set of books of different kinds and from multiple data source
 * 
 */
public class BookSet extends FrameLayout {

	private HorizentalListView mBookList = null;
	
	public BookSet(Context context) {
		super(context);
		init(context);
	}

	
	private void init(Context context)
	{
		mBookList = new HorizentalListView(context);
		CompositeListAdapter la = new CompositeListAdapter();
		//la.add(new HorizontalBookListAdapter(context));
		la.add(new VerticalBookListAdapter(context));
		mBookList.setAdapter(la);
		addView(mBookList);
	}

}