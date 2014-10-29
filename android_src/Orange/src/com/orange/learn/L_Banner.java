package com.orange.learn;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class L_Banner extends FrameLayout
{
    private ImageView mClose;

    public static View makeView(Context context)
    {
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.VERTICAL);
        TextView tView = new TextView(context);
        tView.setText("upper text view");
        LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200);
        L_Banner banner = new L_Banner(context);
        banner.setLayoutParams(lParams);
        ll.addView(tView);
        ll.addView(banner);
        for (int i = 0; i < 4; ++i)
        {
            TextView tv = new TextView(context);
            tv.setText("bottom text view  " + i);
            ll.addView(tv);
        }
        ll.setLayoutAnimation(ll.getLayoutAnimation());
        return ll;
    }

    public L_Banner(Context context)
    {
        super(context);
        initComponents();
        updateTheme();
        updateLanguage();
    }

    private void initComponents()
    {
        mClose = new ImageView(getContext());
        FrameLayout.LayoutParams lParams = new FrameLayout.LayoutParams(100, 100, Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        mClose.setLayoutParams(lParams);
        addView(mClose);
    }

    private void updateLanguage()
    {
        mClose.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                ViewGroup parent = (ViewGroup) L_Banner.this.getParent();
                parent.removeView(L_Banner.this);
            }
        });
    }

    @SuppressWarnings("deprecation")
    private void updateTheme()
    {
        mClose.setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        setBackgroundDrawable(new ColorDrawable(Color.RED));
    }
}
