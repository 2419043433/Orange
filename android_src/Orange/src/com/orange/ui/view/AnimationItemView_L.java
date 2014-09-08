package com.orange.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Bitmap.Config;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;

import com.orange.R;
import com.orange.util.LayoutParamsUtil;

public class AnimationItemView_L extends FrameLayout
{
    private static final int ITEM_HORIZONTAL_SHADOW = 20;
    private static final int ITEM_VERTICAL_SHADOW = 20;
    private static final int ROUND_RECT_RADIUS = 10;
    private static final int SIDELINE_WIDTH = 3;

    private FrameLayout mShadowBg = null;

    private ScaleAnimation mZoomAnimation;
    private ScaleAnimation mMagnifyAnimation;

    // shadow config
    private int mBorderColor = Color.BLUE;

    public AnimationItemView_L(Context context)
    {
        super(context);
        initComponents(context);
        setupListeners();
        updateTheme();
    }

    private void initComponents(Context context)
    {
        mShadowBg = new FrameLayout(context);
        mShadowBg.setLayoutParams(LayoutParamsUtil.FRAMELAYOUT_MATCH_PARENT);
        this.addView(mShadowBg);

        mMagnifyAnimation = new ScaleAnimation(1f, 2f, 1f, 2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mZoomAnimation = new ScaleAnimation(2f, 1f, 2f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        mMemBitmap = Bitmap.createBitmap(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels, Config.ARGB_8888);
        mMemCanvas = new Canvas(mMemBitmap);
    }

    private void OnFocusedStateChanged(boolean focused)
    {
        Animation animation = focused ? mMagnifyAnimation : mZoomAnimation;
        if (null == animation)
        {
            return;
        }
        animation.setDuration(200);
        animation.setFillAfter(true);

        this.startAnimation(animation);
    }

    boolean mClicked = false;

    private void setupListeners()
    {
        // test code
        this.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mClicked = !mClicked;
                OnFocusedStateChanged(mClicked);
            }
        });

        this.setOnFocusChangeListener(new OnFocusChangeListener()
        {

            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                OnFocusedStateChanged(hasFocus);
            }
        });
    }

    @SuppressWarnings("deprecation")
    private void updateTheme()
    {
        mShadowBg.setBackgroundDrawable(getResources().getDrawable(R.drawable.shadow_bg));
    }

    private Rect mRect = new Rect();
    //make some of these static
    private RectF mFocusedBorderRect = new RectF();
    private RectF mContentViewRect = new RectF();
    private Path mPath = new Path();
    private View mContentView;
    private Paint mFocusedBorderPaint = new Paint();
    private Paint mContentViewPaint = new Paint();
    private Canvas mMemCanvas;
    private Bitmap mMemBitmap = null;

    public void setContentView(View contentView)
    {
        mContentView = contentView;
    }

    @Override
    protected void dispatchDraw(Canvas canvas)
    {
        // draw shadow background
        super.dispatchDraw(canvas);

        RectF contentViewRect = null;
        if (mRect.isEmpty())
        {
            getDrawingRect(mRect);
            mFocusedBorderRect
                    .set(mRect.left + ITEM_HORIZONTAL_SHADOW, mRect.top + ITEM_VERTICAL_SHADOW, mRect.right - ITEM_HORIZONTAL_SHADOW, mRect.bottom - ITEM_VERTICAL_SHADOW);
            mContentViewRect.set(
                    mFocusedBorderRect.left + SIDELINE_WIDTH,
                    mFocusedBorderRect.top + SIDELINE_WIDTH,
                    mFocusedBorderRect.right - SIDELINE_WIDTH,
                    mFocusedBorderRect.bottom - SIDELINE_WIDTH);
        }

        if (this.isFocused())
        {
            mPath.reset();
            mPath.addRoundRect(mFocusedBorderRect, ROUND_RECT_RADIUS, ROUND_RECT_RADIUS, Direction.CW);
            mFocusedBorderPaint.setAntiAlias(true);
            mFocusedBorderPaint.setColor(mBorderColor);
            canvas.drawPath(mPath, mFocusedBorderPaint);

            contentViewRect = mContentViewRect;
        }
        else
        {
            contentViewRect = mFocusedBorderRect;
        }
        mContentView.draw(mMemCanvas);
        canvas.save();
        canvas.clipRect(contentViewRect);
        canvas.drawBitmap(mMemBitmap, 0, 0, mContentViewPaint);
        canvas.restore();
    }
}
