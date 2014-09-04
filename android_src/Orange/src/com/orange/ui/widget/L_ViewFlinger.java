package com.orange.ui.widget;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class L_ViewFlinger extends ViewFlipper
{

    public L_ViewFlinger(Context context)
    {
        super(context);
        initComponents(context);
    }

    private class MyOnClickListener implements View.OnClickListener
    {
        public int mIndex;

        public MyOnClickListener(int index)
        {
            mIndex = index;
        }

        @Override
        public void onClick(View v)
        {
            if (mIndex < L_ViewFlinger.this.getChildCount() - 1)
            {
//                RotateAnimation in = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//                in.setDuration(1000);
//                RotateAnimation out = new RotateAnimation(360f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//                out.setDuration(1000);
                AlphaAnimation in = new AlphaAnimation(0, 1);
                TranslateAnimation out = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0, 
                        Animation.RELATIVE_TO_SELF,0,  Animation.RELATIVE_TO_SELF, 0);
                out.setDuration(1000);
                in.setDuration(1000);
                L_ViewFlinger.this.setInAnimation(in);
                L_ViewFlinger.this.setOutAnimation(out);
                L_ViewFlinger.this.showNext();
            }

        }
    }

    private void initComponents(Context context)
    {
        for (int i = 0; i < 10; ++i)
        {
            TextView tView = new TextView(context);
            tView.setText("text__" + i);
            tView.setOnClickListener(new MyOnClickListener(i));
            this.addView(tView);
        }
    }

}
