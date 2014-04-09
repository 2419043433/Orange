package com.orange.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.orange.ui.animation.DraggableListView;
import com.orange.ui.animation.ListLayoutTransition;
import com.orange.ui.view_model.CompositeListAdapter;
import com.orange.ui.view_model.VerticalBookListAdapter;

/**
 * indicates a set of books of different kinds and from multiple data source
 * 
 */
public class BookSet extends FrameLayout {

	private ListView mBookList = null;
	private FrameLayout mDraggableListView = null;

	public BookSet(Context context) {
		super(context);
		init(context);
	}

	@SuppressLint("NewApi")
	private void init(Context context) {
		mBookList = new ListView(context);
		//mBookList.setLayoutTransition(new ListLayoutTransition());
//		Animation animation = AnimationUtils.makeOutAnimation(context, false);
//	    LayoutAnimationController lac=new LayoutAnimationController(animation);
//	    lac.setOrder(LayoutAnimationController.ORDER_REVERSE);
//	    lac.setDelay(1);
//	    mBookList.setLayoutAnimation(lac);
		mBookList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int i = 0;
				for(;i < parent.getChildCount(); ++ i)
				{
					if(view.equals(parent.getChildAt(i)))
					{
						break;
					}
				}
				parent.removeViews(i, 1);
				((BaseAdapter)mBookList.getAdapter()).notifyDataSetChanged();
				
			}
		});
		CompositeListAdapter la = new CompositeListAdapter();
		// la.add(new HorizontalBookListAdapter(context)); 
		la.add(new VerticalBookListAdapter(context, 0));
		la.add(new VerticalBookListAdapter(context, 30));
		la.add(new VerticalBookListAdapter(context, 60));
		mBookList.setAdapter(la);
		addView(mBookList);
		mDraggableListView = new DraggableListView(context, mBookList);
		mDraggableListView.setAlpha(0.5f);
		mDraggableListView.setBackgroundColor(Color.GREEN);
		mDraggableListView.setLayoutTransition(new ListLayoutTransition());
		addView(mDraggableListView, new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

}