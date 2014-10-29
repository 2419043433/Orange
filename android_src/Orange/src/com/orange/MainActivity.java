package com.orange;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.orange.learn.L_RoundRectShapeDrawable;
import com.orange.ui.view.AnimationItemView;
import com.orange.ui.widget.Book;
import com.orange.util.LayoutParamsUtil;

public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Portal main = new Portal(this);
         Book main = new Book(this);
        // L_Drawable main = new L_Drawable(this);
        // ScrollTabWidgetClient main = new ScrollTabWidgetClient(this);
        // L_Scroller main = new L_Scroller(this);
        // L_ScrollView main = new L_ScrollView(this);
        // L_ViewFlinger main = new L_ViewFlinger(this);

        TextView tView = new TextView(this);
        tView.setText("\n\n\n\n\\n\n\ndddddddddddddddabc");
        tView.setBackgroundColor(Color.GREEN);
        tView.setLayoutParams(LayoutParamsUtil.FRAMELAYOUT_MATCH_PARENT);
        String textString = "";
        for (int i = 0; i < 100; i++)
        {
            textString = textString + "alsdkfjasldkfjasldkfjlskdjfsldkjfsldkjf\n";
        }
        tView.setText(textString);
        // L_SelfDrawFrameLayout main = new L_SelfDrawFrameLayout(this);
        // main.addView(tView);
        // L_GridView main = new L_GridView(this);
        // View main = TimeMachineTestUtil.makeTestView(this);
        // //makeSingleAniationView();
        // View main = makeRoundRectView();
        // View main = CubeWithTextureRenderer.makeCubeView(this);
        // View main = new LayoutTransition_L(this);
        // View main = new Weather(this);
        // View main = L_Permission.makeView(this);
        //View main = L_Banner.makeView(this);
        //View main = new DistrictSelectView(this);
        setContentView(main);

        // setContentView(R.layout.activity_main);
    }

    private FrameLayout make500X500FrameLayout()
    {
        AnimationItemView fm = new AnimationItemView(this);
        FrameLayout.LayoutParams lParams = new FrameLayout.LayoutParams(500, 500, Gravity.CENTER);
        fm.setLayoutParams(lParams);
        return fm;
    }

    private FrameLayout make500X500FrameLayout2()
    {
        FrameLayout fm = new FrameLayout(this);
        FrameLayout.LayoutParams lParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        fm.setLayoutParams(lParams);
        TextView textView = new TextView(this);
        textView.setText("adjlksdfjk");
        textView.setBackgroundColor(Color.BLUE);
        fm.addView(textView);
        fm.setBackgroundColor(Color.YELLOW);
        return fm;
    }

    private View makeRoundRectView()
    {
        int radius = 5;
        int width = 2;
        float[] outerR = new float[] { radius, radius, radius, radius, radius, radius, radius, radius };
        RectF inset = new RectF(width, width, width, width);
        float[] innerR = new float[] { radius, radius, radius, radius, radius, radius, radius, radius };
        RoundRectShape rrsRectShape = new RoundRectShape(outerR, inset, innerR);
        L_RoundRectShapeDrawable sp = new L_RoundRectShapeDrawable(rrsRectShape);
        FrameLayout mainFrameLayout = new FrameLayout(this);
        FrameLayout fm = make500X500FrameLayout2();
        mainFrameLayout.addView(fm);
        return mainFrameLayout;
    }

    private View makeSingleAniationView()
    {
        FrameLayout mainFrameLayout = new FrameLayout(this);
        // FrameLayout.LayoutParams fLayoutParams = new FrameLayout(context)
        mainFrameLayout.setBackgroundColor(Color.GRAY);
        FrameLayout fm = make500X500FrameLayout2();
        // fm.setBackgroundColor(Color.RED);
        mainFrameLayout.addView(fm);
        return mainFrameLayout;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
