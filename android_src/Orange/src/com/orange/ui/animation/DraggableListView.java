package com.orange.ui.animation;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;

public class DraggableListView extends FrameLayout {

	private static String TAG = "DraggableListView";
	private ListView mListView = null;
	private View mDraggingView = null;
	private boolean mDragInProgress;
	private boolean mHovering;
	private boolean mAcceptsDrag;
	private int mDragItemIndex = -1;
	

	public DraggableListView(Context context, ListView listView) {
		super(context);
		mListView = listView;
		init(context);
	}

	@SuppressLint("NewApi")
	private void init(Context context) {
		mListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@SuppressLint("NewApi")
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {

				View.DragShadowBuilder sb = new DragShadowBuilderImpl(view);
				view.startDrag(null, // the data to be dragged
						sb, // the drag shadow builder
						null, // no need to use local data
						0 // flags (not currently used, set to 0)
				);
				return true;
			}
		});
		
		setOnTouchListener(new OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				invalidate();
				return false;
			}});
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Log.v(TAG, "Scroll y:" + mListView.getScrollY());
		int childCount = mListView.getChildCount();
		for (int i = 0; i < childCount; ++i) {
			View child = null;
			if (mDragItemIndex < i) {
				child = mListView.getChildAt(i);
			}
			// the last one, do special handle
			else if (i == childCount - 1) {
				int pos = (Integer) mListView.getChildAt(i).getTag() + 1;
				if (pos >= mListView.getAdapter().getCount()) {
					break;
				}
				child = mListView.getAdapter().getView(pos, null, mListView);
			} else {
				child = mListView.getChildAt(i + 1);
			}
			child.draw(canvas);
			canvas.translate(0,
					child.getHeight() + mListView.getDividerHeight());
		}
	}

	@SuppressLint("NewApi")
	private class DragShadowBuilderImpl extends View.DragShadowBuilder {

		public DragShadowBuilderImpl(View v) {
			super(v);
		}

		public boolean onDragEvent(DragEvent event) {
			boolean result = false;
			switch (event.getAction()) {
			case DragEvent.ACTION_DRAG_STARTED: {
				// claim to accept any dragged content
				Log.i(TAG, "Drag started, event=" + event);
				// cache whether we accept the drag to return for LOCATION
				// events
				mDragInProgress = true;
				mAcceptsDrag = result = true;
				// Redraw in the new visual state if we are a potential drop
				// target
				if (mAcceptsDrag) {
					invalidate();
				}
			}
				break;

			case DragEvent.ACTION_DRAG_ENDED: {
				Log.i(TAG, "Drag ended.");
				if (mAcceptsDrag) {
					invalidate();
				}
				mDragInProgress = false;
				mHovering = false;
			}
				break;

			case DragEvent.ACTION_DRAG_LOCATION: {
				// we returned true to DRAG_STARTED, so return true here
				Log.i(TAG, "... seeing drag locations ...");
				result = mAcceptsDrag;
			}
				break;

			case DragEvent.ACTION_DROP: {
				Log.i(TAG, "Got a drop! dot=" + this + " event=" + event);
				// if (mAnrType == ANR_DROP) {
				// sleepSixSeconds();
				// }
				// processDrop(event);
				result = true;
			}
				break;

			case DragEvent.ACTION_DRAG_ENTERED: {
				Log.i(TAG, "Entered dot @ " + this);
				mHovering = true;
				invalidate();
			}
				break;

			case DragEvent.ACTION_DRAG_EXITED: {
				Log.i(TAG, "Exited dot @ " + this);
				mHovering = false;
				invalidate();
			}
				break;

			default:
				Log.i(TAG, "other drag event: " + event);
				result = mAcceptsDrag;
				break;
			}

			return result;
		}
	}

}
