package com.orange.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DistrictSelectView extends LinearLayout
{
    private static final int SELECT_VIEW_HORIZENTAL_MARGIN = 20;
    private SingleLineTextIconItemView mAutoLocationView;
    private SingleLineTextIconItemView mManualLocationItemView;
    
    private LinearLayout mSelectContainer;
    private SingleLineTextIconItemView mProvinceSelectView;
    private SingleLineTextIconItemView mCitySelectView;
    private SingleLineTextIconItemView mCountySelectView;

    public DistrictSelectView(Context context)
    {
        super(context);
        initComponents();
        setupListeners();
        updateTheme();
        updateLanguage();
    }

    private void initComponents()
    {
        mAutoLocationView = new SingleLineTextIconItemView(getContext());
        mManualLocationItemView = new SingleLineTextIconItemView(getContext());
        
        mProvinceSelectView = new SingleLineTextIconItemView(getContext());
        mCitySelectView = new SingleLineTextIconItemView(getContext());
        mCountySelectView = new SingleLineTextIconItemView(getContext());
        
        mSelectContainer = new LinearLayout(getContext());
        mSelectContainer.setOrientation(LinearLayout.HORIZONTAL);
        
        LinearLayout.LayoutParams selectLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        selectLayoutParams.leftMargin = SELECT_VIEW_HORIZENTAL_MARGIN;
        mSelectContainer.addView(mProvinceSelectView, selectLayoutParams);
        mSelectContainer.addView(mCitySelectView, selectLayoutParams);
        mSelectContainer.addView(mCountySelectView, selectLayoutParams);
        
        setOrientation(LinearLayout.VERTICAL);
        addView(mAutoLocationView);
        addView(mManualLocationItemView);
        addView(mSelectContainer);
    }

    private void setupListeners()
    {
    }

    private void updateTheme()
    {
    }

    private void updateLanguage()
    {
        //test
        mProvinceSelectView.setText("省");
        mCitySelectView.setText("市");
        mCountySelectView.setText("县");
        
        mAutoLocationView.setText("自动定位");
        mManualLocationItemView.setText("手动定位");
    }
    

}
