package com.orange.ui.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScrollTabWidget extends LinearLayout
{
    List<TabItem> mItems = new ArrayList<ScrollTabWidget.TabItem>();
    FrameLayout mPageContainer = null;
    LinearLayout mTitleContainer = null;
    

    // justa for test
    public static ScrollTabWidget makeScrollTabWidget(Context context)
    {
        ScrollTabWidget widget = new ScrollTabWidget(context);
        for (int i = 0; i < 10; ++i)
        {
            TextView title = new TextView(context);
            title.setText("title" + i);
            TextView page = new TextView(context);
            page.setText("page" + i);
            page.setBackgroundColor(Color.BLACK);
            widget.addTab(page, title);
        }

        return widget;
    }

    public void switchTo(int index)
    {
        if (index < 0 || index >= mItems.size())
        {
            return;
        }

        TabItem destItem = mItems.get(index);
        destItem.mPage.bringToFront();
        for (int i = 0; i < mItems.size(); ++i)
        {
            int color = Color.BLACK;
            if (i == index)
            {
                color = Color.RED;
            }
            View title = mItems.get(i).mTitle;
            title.setBackgroundColor(color);
            title.setSelected(true);
            title.requestFocus();
        }
    }

    public ScrollTabWidget(Context context)
    {
        super(context);
        initComponents(context);
    }

    private void initComponents(Context context)
    {
        this.setOrientation(LinearLayout.VERTICAL);

        mTitleContainer = new LinearLayout(context);
        mTitleContainer.setOrientation(LinearLayout.HORIZONTAL);

        mPageContainer = new FrameLayout(context);

        this.addView(mTitleContainer);
        this.addView(mPageContainer);
    }

    public void addTab(View page, View title)
    {
        addTab(new TabItem(page, title));
    }

    private int getTitleIndex(View title)
    {
        for (int i = 0; i < mTitleContainer.getChildCount(); ++i)
        {
            if (title == mTitleContainer.getChildAt(i))
            {
                return i;
            }
        }
        return -1;
    }

    private void addTab(TabItem item)
    {
        if (null != item)
        {
            mItems.add(item);
            mTitleContainer.addView(item.mTitle);
            mPageContainer.addView(item.mPage);
            item.mTitle.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View v)
                {
                    switchTo(getTitleIndex(v));

                }
            });
        }
    }

    public void removeItem(TabItem item)
    {
        if (null != item)
        {
            mItems.remove(item);
            mTitleContainer.removeView(item.mTitle);
            mPageContainer.removeView(item.mPage);
        }
    }

    public TabItem getItem(int index)
    {
        return mItems.get(index);
    }

    class TabItem
    {
        View mPage;
        View mTitle;

        TabItem(View page, View title)
        {
            mPage = page;
            mTitle = title;
        }
    }

}
