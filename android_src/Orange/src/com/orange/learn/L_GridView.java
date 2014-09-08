package com.orange.learn;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.TextView;

import com.orange.ui.view.AnimationItemView;

public class L_GridView extends FrameLayout
{
    GridView mGridView;

    public L_GridView(Context context)
    {
        super(context);
        mGridView = new GridView(context);
        mGridView.setAdapter(new MyAdapter());
        mGridView.setNumColumns(5);
        this.addView(mGridView);
    }

    private class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return 100;
        }

        @Override
        public Object getItem(int position)
        {
            return null;
        }

        @Override
        public long getItemId(int position)
        {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            Log.v("xxx", "getView:" + position);
            AnimationItemView view = null;
            if(null == convertView)
            {
                view = new AnimationItemView(getContext());
                TextView tView = new TextView(getContext());
                view.setContentView(tView);
                view.setTag(tView);
                tView.setBackgroundColor(Color.RED);
            }
            else {
                view = (AnimationItemView)convertView;
            }
            TextView textView = (TextView)view.getTag();
            textView.setText("item:" + position);
            
            return view;
        }
    }

}
