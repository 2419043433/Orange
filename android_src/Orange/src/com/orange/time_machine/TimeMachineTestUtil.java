package com.orange.time_machine;

import android.content.Context;
import android.view.View;

public class TimeMachineTestUtil
{
    public static View makeTestView(Context context)
    {
        ScrollToolBox tbBox = new ScrollToolBox(context);
        return tbBox;
    }
}
