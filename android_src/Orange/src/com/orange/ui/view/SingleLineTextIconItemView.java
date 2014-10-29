package com.orange.ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SingleLineTextIconItemView extends LinearLayout
{
    private TextView mText;
    private ImageView mIcon;

    public SingleLineTextIconItemView(Context context)
    {
        super(context);
        initComponents();
    }

    private void initComponents()
    {
        mText = new TextView(getContext());
        mIcon = new ImageView(getContext());

        setOrientation(LinearLayout.HORIZONTAL);
        addView(mText);
        addView(mIcon);
    }

    public void setIconBg(Drawable d)
    {
        mIcon.setImageDrawable(d);
    }

    public void setText(String text)
    {
        mText.setText(text);
    }

    public void setHorizentalPadding(int padding)
    {
        setPadding(padding, 0, padding, 0);
    }

}
