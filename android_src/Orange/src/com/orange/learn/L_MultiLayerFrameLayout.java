package com.orange.learn;

import com.orange.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

public class L_MultiLayerFrameLayout extends FrameLayout
{
    @SuppressWarnings("deprecation")
    public L_MultiLayerFrameLayout(Context context)
    {
        super(context);
        // TODO Auto-generated constructor stub
        FrameLayout rectFrameLayout  = new FrameLayout(context);
        rectFrameLayout.setBackgroundColor(Color.RED);
        TextView tView = new TextView(context);
        //tView.setBackgroundColor(Color.RED);
        tView.setBackgroundDrawable(getResources().getDrawable(R.drawable.selected_bg));
        String textString = "";
        for(int i = 0; i <100; i ++)
        {
            textString  = textString + "alsdkfjasldkfjasldkfjlskdjfsldkjfsldkjf\n";
        }
        tView.setText(textString);
        rectFrameLayout.addView(tView);
        this.addView(rectFrameLayout);
    }
    
    @Override
    protected void dispatchDraw(Canvas canvas)
    {
        long time = System.currentTimeMillis();
        super.dispatchDraw(canvas);
        Log.v("xxx","multi:" + (System.currentTimeMillis() - time));
    }

}
