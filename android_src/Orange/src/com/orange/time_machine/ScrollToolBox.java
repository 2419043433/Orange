package com.orange.time_machine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

@SuppressLint("NewApi")
public class ScrollToolBox extends FrameLayout
{

    private ScrollView mScrollView;
    private LinearLayout mContent;
    private View mCurSelectedView;
    private FrameLayout mDragLayer;

    public ScrollToolBox(Context context)
    {
        super(context);
        initCompoment();
    }

    private Animation createScaleAnimation(boolean zoom)
    {
        float from = zoom ? 1.3f : 1.0f;
        float to = zoom ? 1.0f : 1.3f;
        Animation animation = new ScaleAnimation(from, to, from, to, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(200);
        animation.setFillAfter(true);
        return animation;
    }

    private Animation createAlphaAnimation(boolean disappare)
    {
        float from = disappare ? 1.0f : 0.0f;
        float to = disappare ? 0.0f : 1.0f;
        Animation animation = new AlphaAnimation(from, to);
        animation.setDuration(500);
        animation.setFillAfter(true);
        return animation;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initCompoment()
    {
        mDragLayer = new FrameLayout(getContext());
        mDragLayer.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        
        // Views

        mScrollView = new ScrollView(getContext());
        mScrollView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

        mContent = new LinearLayout(getContext());
        mContent.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        mContent.setOrientation(LinearLayout.VERTICAL);

        mScrollView.addView(mContent);
        addView(mScrollView);
        addView(mDragLayer);
        setOnTouchListener(new TouchEventListener(mTouchEventDelegate));

        mAdapter = new Adapter();
        updateData();
    }



    private View.OnClickListener mOnClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            // zoom the previous selected and magnify current selected
            if (mCurSelectedView != null)
            {
                if (mCurSelectedView == view)
                {
                    return;
                }
                mCurSelectedView.startAnimation(createScaleAnimation(true));
            }

            mCurSelectedView = view;
            mCurSelectedView.startAnimation(createScaleAnimation(false));
        }
    };

    public void updateData()
    {
        if (null == mAdapter)
        {
            return;
        }

        for (int i = 0; i < mAdapter.getCount(); ++i)
        {
            View convertView = (i < mContent.getChildCount()) ? mContent.getChildAt(i) : null;
            View view = mAdapter.getView(i, convertView, mContent);

            mContent.addView(view);
        }
    }
    
    private TouchEventListener.Delegate mTouchEventDelegate = new TouchEventListener.Delegate()
    {
        @Override
        public void onActionDown(View v, MotionEvent event)
        {

        }

        @Override
        public void onActionUp(View v, MotionEvent event)
        {

        }

        @Override
        public void onActionMove(View v, MotionEvent event)
        {
            if(null == mCurrentMotionDownView)
            {
                return;
            }
            
            int x = (int) event.getX();
            int y = (int) event.getY();
            Rectangle dragView = new Rectangle(getContext());
            dragView.setLayoutParams(v.getLayoutParams());
            dragView.setX(x);
            dragView.setY(y);
            mDragLayer.addView(dragView);
            ScrollToolBox.this.postInvalidate();
        }

        @Override
        public void onActionOutside(View v, MotionEvent event)
        {
            
        }      
    };
    
    private View mCurrentMotionDownView;
    
    private TouchEventListener.Delegate mItemTouchEventDelegate = new TouchEventListener.Delegate()
    {
        @Override
        public void onActionDown(View v, MotionEvent event)
        {
            mCurrentMotionDownView = v;
        }

        @Override
        public void onActionUp(View v, MotionEvent event)
        {
            mCurrentMotionDownView = null;
        }

        @Override
        public void onActionMove(View v, MotionEvent event)
        {

        }

        @Override
        public void onActionOutside(View v, MotionEvent event)
        {
            mCurrentMotionDownView = null;
        }      
    };

    private Adapter mAdapter;

    public void setAdapter(Adapter adapter)
    {
        mAdapter = adapter;
    }

    class Adapter extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return 100;
        }

        @Override
        public Object getItem(int arg0)
        {
            return null;
        }

        @Override
        public long getItemId(int arg0)
        {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View view = convertView;
            if (null == view)
            {
                view = new Rectangle(getContext());
                LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(100, 100);
                lParams.leftMargin = 20;
                lParams.rightMargin = 20;
                view.setLayoutParams(lParams);
                view.setOnClickListener(mOnClickListener);
                view.setOnTouchListener(new TouchEventListener(mItemTouchEventDelegate));
            }

            return view;
        }

    }
}
