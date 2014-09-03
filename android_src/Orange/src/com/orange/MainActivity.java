package com.orange;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.orange.ui.widget.Portal;
import com.orange.ui.widget.ScrollTabWidget;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Portal main = new Portal(this);
       // Book main = new Book(this);
       // L_Drawable main = new L_Drawable(this);
        ScrollTabWidget main = ScrollTabWidget.makeScrollTabWidget(this);
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
