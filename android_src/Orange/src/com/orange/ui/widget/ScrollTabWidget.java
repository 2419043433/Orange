package com.orange.ui.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.orange.util.LayoutParamsUtil;

public class ScrollTabWidget extends LinearLayout
{
    private LinearLayout mTitleContainer = null;
    private FrameLayout mPageContainer = null;
    private List<TabItem> mTabItems = new ArrayList<ScrollTabWidget.TabItem>();
    private OnTabSelectionChangeListener mOnTabSelectionChangeListener = null;

    public ScrollTabWidget(Context context)
    {
        super(context);
        initComponents(context);
    }

    private void initComponents(Context context)
    {
        this.setOrientation(LinearLayout.VERTICAL);

        mTitleContainer = new LinearLayout(context);
        mTitleContainer.setLayoutParams(LayoutParamsUtil.LINEARLAYOUT_MATCH_PARENT_WRAP_CONTENT);

        mPageContainer = new FrameLayout(context);
        mPageContainer.setLayoutParams(LayoutParamsUtil.VIEWGROUP_MATCH_PARENT);

        this.addView(mTitleContainer);
        this.addView(mPageContainer);
    }

    public class TabItem
    {
        View mTitle;
        TabPage mPage;

        public TabItem(View title, TabPage page)
        {
            mTitle = title;
            mPage = page;
        }
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

    private View.OnClickListener onTitleClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switchTo(getTitleIndex(v));
        }
    };

    private void setupTitleListener(View title)
    {
        // setup onClick listener
        title.setOnClickListener(onTitleClickListener);
        // setup onFocusChange listener
    }

    public void addTabItem(View title, TabPage page)
    {
        mTabItems.add(new TabItem(title, page));
        mTitleContainer.addView(title);
        mPageContainer.addView(page);

        title.setFocusableInTouchMode(true);
        setupTitleListener(title);
    }

    private boolean isIndexValid(int index)
    {
        return index >= 0 && index < mTabItems.size();
    }

    public void switchTo(int index)
    {
        if (!isIndexValid(index))
        {
            return;
        }

        TabItem destItem = mTabItems.get(index);
        destItem.mPage.bringToFront();
        for (int i = 0; i < mTabItems.size(); ++i)
        {
            if (mOnTabSelectionChangeListener != null)
            {
                mOnTabSelectionChangeListener.OnTabSelectionChanged(mTabItems.get(i), i == index);
            }
        }
    }

    public static interface OnTabSelectionChangeListener
    {
        void OnTabSelectionChanged(TabItem item, boolean selected);
    }

    public void setOnTabSelectionChangeListener(OnTabSelectionChangeListener listener)
    {
        mOnTabSelectionChangeListener = listener;
    }
}
