package com.orange.time_machine;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.LinearLayout.LayoutParams;

@SuppressLint("NewApi")
public class ToolBox extends FrameLayout
{

    private ListView mListView;

    public ToolBox(Context context)
    {
        super(context);
        initCompoment();
    }

    private void initCompoment()
    {
        mListView = new ListView(getContext());
        mListView.setAdapter(new ToolBoxAdapter());
        mListView.setLayoutParams(new FrameLayout.LayoutParams(400,FrameLayout.LayoutParams.MATCH_PARENT));
        mListView.setDivider(null);
        addView(mListView);

        setAlpha((float) 0.5);
    }

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
        public View getView(int arg0, View arg1, ViewGroup arg2)
        {
            View view = null;
            if (null == arg1)
            {
                view = new Rectangle(getContext());
                view.setLayoutParams(new AbsListView.LayoutParams(100, 100));
            }
            else
            {
                view = arg1;
            }
            return view;
        }

    }
}
