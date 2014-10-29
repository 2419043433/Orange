package com.orange.time_machine;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

@SuppressLint("NewApi")
public class ScrollToolBox extends FrameLayout {
	private static final String TAG = "ScrollToolBox";
	private ScrollView mScrollView;
	private LinearLayout mDestLayer;
	private LinearLayout mContent;
	private View mCurSelectedView;
	private FrameLayout mDragLayer;

	public ScrollToolBox(Context context) {
		super(context);
		initCompoment();
	}

	private Animation createScaleAnimation(boolean zoom) {
		float from = zoom ? 1.3f : 1.0f;
		float to = zoom ? 1.0f : 1.3f;
		Animation animation = new ScaleAnimation(from, to, from, to,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		animation.setDuration(200);
		animation.setFillAfter(true);
		return animation;
	}
	
	private LayoutAnimationController createLayoutAnimationController()
	{
        AnimationSet set = new AnimationSet(true);

        Animation animation = new RotateAnimation(0f, 30f,0.5f,0.5f);
        animation.setDuration(100);
        set.addAnimation(animation);
        
        animation = new RotateAnimation(30f, -30f,0.5f,0.5f);
        animation.setDuration(100);
        set.addAnimation(animation);
        
        animation = new RotateAnimation(-30f, 0f,0.5f,0.5f);
        animation.setDuration(100);
        set.addAnimation(animation);
        
        set.setRepeatCount(3);
        set.setRepeatMode(Animation.REVERSE);
        LayoutAnimationController controller = new LayoutAnimationController(set, 0.5f);
        return controller;
	}

	private Animation createAlphaAnimation(boolean disappare) {
		float from = disappare ? 1.0f : 0.1f;
		float to = disappare ? 0.1f : 1.0f;
		Animation animation = new AlphaAnimation(from, to);
		animation.setDuration(500);
		animation.setFillAfter(true);
		return animation;
	}
	
	private LayoutTransition createLayoutTransition() {
		LayoutTransition lTransition = new LayoutTransition();
		ObjectAnimator animIn = ObjectAnimator.ofFloat(null, "rotationX", 0f,
				360f).setDuration(200);
		lTransition.setAnimator(LayoutTransition.APPEARING, animIn);
		
//		ObjectAnimator animExists = ObjectAnimator.ofFloat(null, "rotationY", 0f,
//				360f).setDuration(lTransition.getDuration(LayoutTransition.CHANGE_APPEARING));
//		lTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, animExists);

        lTransition.setStagger(LayoutTransition.CHANGE_APPEARING, 200);
        lTransition.setStagger(LayoutTransition.CHANGE_DISAPPEARING, 30);
        lTransition.setDuration(500);
		return lTransition;
	}

	private Animation createTranslateAnimation(boolean disappare) {
		float from = disappare ? 0.0f : -200f;
		float to = disappare ? -200f : 0.0f;
		Animation animation = new TranslateAnimation(from, to, 0, 0);
		animation.setDuration(500);
		animation.setFillAfter(true);
		return animation;
	}

	@SuppressLint("ClickableViewAccessibility")
	private void initCompoment() {
		mDestLayer = new LinearLayout(getContext());
		FrameLayout.LayoutParams destLayoutParams = new FrameLayout.LayoutParams(
				500, FrameLayout.LayoutParams.MATCH_PARENT);
		destLayoutParams.leftMargin = 400;
		mDestLayer.setOrientation(LinearLayout.HORIZONTAL);
		mDestLayer.setLayoutParams(destLayoutParams);
		mDestLayer.setLayoutTransition(createLayoutTransition());

		mDragLayer = new FrameLayout(getContext());
		mDragLayer.setLayoutParams(new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT));

		// Views

		mScrollView = new ScrollView(getContext());
		mScrollView.setLayoutParams(new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT));

		mContent = new LinearLayout(getContext());
		mContent.setLayoutParams(new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT));
		mContent.setOrientation(LinearLayout.VERTICAL);

		mScrollView.addView(mContent);
		addView(mDestLayer);
		addView(mScrollView);
		addView(mDragLayer);

		mAdapter = new Adapter();
		updateData();

	}

	private View.OnLongClickListener mOnLongClickListener = new View.OnLongClickListener() {
		@Override
		public boolean onLongClick(View v) {
			mScrollView.startAnimation(createTranslateAnimation(true));
			mCurrentMotionDownView = v;
			mFlag = true;
			return true;
		}
	};

	private View.OnClickListener mOnClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			// zoom the previous selected and magnify current selected
			if (mCurSelectedView != null) {
				mCurSelectedView.startAnimation(createScaleAnimation(true));
				if (mCurSelectedView == view) {
					mCurSelectedView = null;
					return;
				}
			}

			mCurSelectedView = view;
			mCurSelectedView.startAnimation(createScaleAnimation(false));
		}
	};

	public void updateData() {
		if (null == mAdapter) {
			return;
		}

		for (int i = 0; i < mAdapter.getCount(); ++i) {
			View convertView = (i < mContent.getChildCount()) ? mContent
					.getChildAt(i) : null;
			View view = mAdapter.getView(i, convertView, mContent);

			mContent.addView(view);
		}
	}

	boolean mFlag = false;
	private boolean mHasActionDown = false;

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		Log.v(TAG, "dispatchTouchEvent:");
		if (!mFlag) {
			return super.dispatchTouchEvent(event);
		}
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			mHasActionDown = true;
		}
			break;
		case MotionEvent.ACTION_OUTSIDE:
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP: {
			mHasActionDown = false;
			onActionUp(event);
		}
			break;
		case MotionEvent.ACTION_MOVE: {
			if (mHasActionDown || mFlag) {
				onActionMove(event);
			}
		}
			break;

		default:
			break;
		}
		return true;
	}

	private void createDragView() {
		mDragTargetView = new Rectangle(getContext());
		mDragTargetView.setVisibility(View.INVISIBLE);
		mDragTargetView.setBackgroundColor(Color.GREEN);
		mDragTargetView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v.setVisibility(View.GONE);
            }
        });
		LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(100,
				100);
		lParams.leftMargin = 20;
		lParams.rightMargin = 20;
		mDragTargetView.setLayoutParams(lParams);
		mDragLayer.removeAllViews();
		mDragLayer.addView(mDragTargetView);
	}

	public void onActionUp(MotionEvent event) {
		if (!mFlag || null == mDragTargetView) {
			return;
		}
		mDragLayer.removeAllViews();
		mDestLayer.addView(mDragTargetView, 0);
		mDragTargetView = null;
		mFlag = false;
		mScrollView.startAnimation(createTranslateAnimation(false));
	}

	public void onActionMove(MotionEvent event) {
		if (!mFlag || null == mCurrentMotionDownView) {
			return;
		}
		if (null == mDragTargetView) {
			createDragView();
		}

		int x = (int) event.getX();
		int y = (int) event.getY();

		if (mDragTargetView.getVisibility() != View.VISIBLE) {
			mDragTargetView.setVisibility(View.VISIBLE);
		}
		mDragTargetView.layout(x - mCurrentMotionDownView.getWidth() / 2, y
				- mCurrentMotionDownView.getHeight() / 2, x
				+ mCurrentMotionDownView.getWidth() / 2, y
				+ mCurrentMotionDownView.getHeight() / 2);
	}

	private View mCurrentMotionDownView;
	private View mDragTargetView;

	private Adapter mAdapter;

	public void setAdapter(Adapter adapter) {
		mAdapter = adapter;
	}

	class Adapter extends BaseAdapter {

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
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			if (null == view) {
				view = new Rectangle(getContext());
				LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
						100, 100);
				lParams.leftMargin = 20;
				lParams.rightMargin = 20;
				view.setLayoutParams(lParams);
				view.setOnClickListener(mOnClickListener);
				view.setOnLongClickListener(mOnLongClickListener);
			}

			return view;
		}

	}
}
