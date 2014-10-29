package com.orange.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Weather extends LinearLayout
{
    public enum LayoutMode
    {
        Large,
        Small
    }
    private LinearLayout mLarge;
    private LinearLayout mSmall;
    private BitmapDrawable mBitmapDrawable;
    
    private LinearLayout mTimeAndRefresh;
    private LinearLayout mTemperatureCity;
    private LinearLayout mWeather;
    private LinearLayout mWeekWeather;
    
    private LinearLayout mAQI;
    
    private TextView mTime;
    private TextView mRefresh;
    private TextView mTemperature;
    private TextView mCity;
    private ImageView mLocationIcon;
    private ImageView mWeatherIcon;
    private TextView mWeatherText;
    private ImageView mWindIcon;
    private TextView mWindText;
    private TextView mAQIText;
    private ImageView mAQIArrowIcon;
    private TextView mTemperatureRange;

    private void updateLanguage()
    {
        mTime.setText("18:51");
        mRefresh.setText("更新");
        mTemperature.setText("32");
        mCity.setText("广州");
        mWeatherText.setText("晴");
        mWindText.setText("2级微风");
        mAQIText.setText("84良");
        mTemperatureRange.setText("24~34");
    }

    @SuppressWarnings("deprecation")
    private void updateTheme()
    {
        mTemperature.setTextSize(40);
        mLocationIcon.setBackgroundDrawable(mBitmapDrawable);
        mWeatherIcon.setBackgroundDrawable(mBitmapDrawable);
        mWindIcon.setBackgroundDrawable(mBitmapDrawable);
        mAQIArrowIcon.setBackgroundDrawable(mBitmapDrawable);
    }
    
    class WeekWeatherItem extends LinearLayout
    {
        private ImageView mCloud;
        private TextView mTemperatureRange;
        private TextView mWeekday;
        public WeekWeatherItem(Context context)
        {
            super(context);
            initComponents();
            updateLanguage();
            updateTheme();
        }
        private void initComponents()
        {
            setOrientation(LinearLayout.VERTICAL);
            setGravity(Gravity.CENTER);
            
            mCloud = new ImageView(getContext());
            mTemperatureRange = new TextView(getContext());
            mWeekday = new TextView(getContext());
            
            addView(mCloud);
            addView(mTemperatureRange);
            addView(mWeekday);
        }
        
        private void updateLanguage()
        {
            mTemperatureRange.setText("23-32");
            mWeekday.setText("星期二");
        }
        @SuppressWarnings("deprecation")
        private void updateTheme()
        {
            mCloud.setBackgroundDrawable(mBitmapDrawable);
        }
    }
    
    public Weather(Context context)
    {
        super(context);
        initBitmap();
        initComponents();
        updateLanguage();
        updateTheme();
    }
    
    private void initComponents()
    {
        mTime = new TextView(getContext());
        mRefresh = new TextView(getContext());
        mTemperature = new TextView(getContext());
        mCity = new TextView(getContext());
        mLocationIcon = new ImageView(getContext());
        mWeatherIcon = new ImageView(getContext());
        mWeatherText = new TextView(getContext());
        mWindIcon = new ImageView(getContext());
        mWindText = new TextView(getContext());
        mAQIText = new TextView(getContext());
        mAQIArrowIcon = new ImageView(getContext());
        mTemperatureRange = new TextView(getContext());
        
        mAQI = new LinearLayout(getContext());
        mAQI.setOrientation(LinearLayout.HORIZONTAL);
        mAQI.addView(mAQIText);
        mAQI.addView(mAQIArrowIcon);
        
        mTimeAndRefresh = new LinearLayout(getContext());
        mTimeAndRefresh.setOrientation(LinearLayout.HORIZONTAL); 
        mTimeAndRefresh.setGravity(Gravity.RIGHT);
        mTimeAndRefresh.addView(mTime);
        mTimeAndRefresh.addView(mRefresh);

        mTemperatureCity = new LinearLayout(getContext());
        mTemperatureCity.setOrientation(LinearLayout.HORIZONTAL); 
        mTemperatureCity.addView(mTemperature);
        mTemperatureCity.addView(mCity);
        mTemperatureCity.addView(mLocationIcon);

        mWeather = new LinearLayout(getContext());
        mWeather.setOrientation(LinearLayout.HORIZONTAL); 
        mWeather.addView(mWeatherIcon);
        mWeather.addView(mWeatherText);
        mWeather.addView(mWindIcon);
        mWeather.addView(mWindText);
        mWeather.addView(mAQI);
        
        mWeekWeather = new LinearLayout(getContext());
        mWeekWeather.setOrientation(LinearLayout.HORIZONTAL); 
        mWeekWeather.addView(new WeekWeatherItem(getContext()));
        mWeekWeather.addView(new WeekWeatherItem(getContext()));
        mWeekWeather.addView(new WeekWeatherItem(getContext()));
        mWeekWeather.addView(new WeekWeatherItem(getContext()));
        
        setOrientation(LinearLayout.VERTICAL);
        addView(mTimeAndRefresh);
        addView(mTemperatureCity);
        addView(mWeather);
        addView(mWeekWeather);
    }
    
    @SuppressWarnings("deprecation")
    private void initBitmap()
    {
        Bitmap bmp = Bitmap.createBitmap(100, 100, Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        canvas.drawColor(Color.RED);
        mBitmapDrawable = new BitmapDrawable(bmp);
    }

}
