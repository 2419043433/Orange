package com.orange;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.orange.learn.L_SelfDrawFrameLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Portal main = new Portal(this);
       //Book main = new Book(this);
       // L_Drawable main = new L_Drawable(this);
        //ScrollTabWidgetClient main = new ScrollTabWidgetClient(this);
        //L_Scroller main = new L_Scroller(this);
        //L_ScrollView main = new L_ScrollView(this);
        //L_ViewFlinger main = new L_ViewFlinger(this);
        L_SelfDrawFrameLayout main = new L_SelfDrawFrameLayout(this);
        TextView tView = new TextView(this);
        tView.setBackgroundColor(Color.RED);
        String textString = "";
        for(int i = 0; i <100; i ++)
        {
            textString  = textString + "alsdkfjasldkfjasldkfjlskdjfsldkjfsldkjf\n";
        }
        tView.setText(textString);
        main.addView(tView);
       // L_MultiLayerFrameLayout main = new L_MultiLayerFrameLayout(this);
        setContentView(main);
        
        //setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
