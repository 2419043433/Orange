package com.orange.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.orange.ui.widget.ScrollTabWidget.TabItem;

/*************************************** just for test ***************************************/

public class ScrollTabWidgetClient extends FrameLayout implements ScrollTabWidget.OnTabSelectionChangeListener
{
    private ScrollTabWidget mScrollTabWidget = null;

    public ScrollTabWidgetClient(Context context)
    {
        super(context);

        mScrollTabWidget = new ScrollTabWidget(context);

        for (int i = 0; i < 5; ++i)
        {
            TestTabPage page = new TestTabPage(context, i);
            TextView title = new TextView(context);
            title.setText("title___" + i);
            mScrollTabWidget.addTabItem(title, page);
        }
        mScrollTabWidget.switchTo(0);
        mScrollTabWidget.setOnTabSelectionChangeListener(this);
        addView(mScrollTabWidget);
    }

    private class TestTabPage extends TabPage
    {
        private TextView mTextView;

        public TestTabPage(Context context, int i)
        {
            super(context);

            mTextView = new TextView(context);
            mTextView.setText("page____" + i);
            addView(mTextView);
            setBackgroundColor(Color.BLUE);
        }
    }

    @Override
    public void OnTabSelectionChanged(TabItem item, boolean selected)
    {
        int color = Color.BLACK;
        if (selected)
        {
            color = Color.RED;
        }

        item.mTitle.setBackgroundColor(color);
    }

}

/*************************************** just for test ***************************************/
