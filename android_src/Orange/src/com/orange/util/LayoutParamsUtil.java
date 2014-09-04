package com.orange.util;

import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class LayoutParamsUtil
{
    public static final int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;
    public static final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;

    public static final FrameLayout.LayoutParams FRAMELAYOUT_MATCH_PARENT = new FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT, Gravity.FILL);
    public static final LinearLayout.LayoutParams LINEARLAYOUT_MATCH_PARENT_WRAP_CONTENT = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
    public static final LinearLayout.LayoutParams LINEARLAYOUT_WRAP_CONTENT = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
    public static final ViewGroup.LayoutParams VIEWGROUP_MATCH_PARENT = new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT);
}
