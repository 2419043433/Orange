package com.orange.time_machine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

@SuppressLint("NewApi")
public class ToolBox extends FrameLayout
{

    private ListView mListView;
    private View mCurSelectedView;

    public ToolBox(Context context)
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

    private void initCompoment()
    {
        mListView = new ListView(getContext());
        mListView.setAdapter(new ToolBoxAdapter());
        mListView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        mListView.setDivider(null);
        mListView.setOnItemClickListener(mOnItemClickListener);
        addView(mListView);
    }

    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener()
    {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            //zoom the previous selected and magnify current selected
            if (mCurSelectedView != null)
            {
                if (mCurSelectedView == view)
                {
                    return;
                }
                Animation animation = createScaleAnimation(true);
                mCurSelectedView.setAnimation(animation);
                animation.startNow();
            }

            mCurSelectedView = view;
            Animation animation = createScaleAnimation(false);
            mCurSelectedView.setAnimation(animation);
            animation.startNow();
        }
    };

    class ToolBoxAdapter extends BaseAdapter
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
                view.setLayoutParams(new AbsListView.LayoutParams(100, 100));

            }

            return view;
        }

    }
}
