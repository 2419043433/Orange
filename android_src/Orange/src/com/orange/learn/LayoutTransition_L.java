package com.orange.learn;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LayoutTransition_L extends LinearLayout {

	private Button mBtn;

	private LinearLayout mContent;

	@SuppressLint("NewApi")
	public LayoutTransition_L(Context context) {
		super(context);

		mBtn = new Button(context);
		mContent = new LinearLayout(context);
		mContent.setOrientation(LinearLayout.HORIZONTAL);
		mBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				TextView tv = new TextView(getContext());
				tv.setText("aaa\nbbb\nccc");
				mContent.addView(tv, 0);
			}
		});
		mBtn.setText("add");
		mContent.setLayoutTransition(createLayoutTransition());
		//mContent.setLayoutAnimation(createLayoutAnimationController());
		setOrientation(LinearLayout.VERTICAL);
		addView(mBtn);
		addView(mContent);
	}
	
	private LayoutAnimationController createLayoutAnimationController()
	{
        AnimationSet set = new AnimationSet(true);

        Animation animation = new RotateAnimation(0f, 30f,0.5f,0.5f);
        animation.setDuration(100);
        set.addAnimation(animation);
        
        animation = new RotateAnimation(30f, -30f,0.5f,0.5f);
        animation.setDuration(100);
        set.addAnimation(animation);
        
        animation = new RotateAnimation(-30f, 0f,0.5f,0.5f);
        animation.setDuration(100);
        set.addAnimation(animation);
        
        set.setRepeatCount(3);
        set.setRepeatMode(Animation.REVERSE);
        LayoutAnimationController controller = new LayoutAnimationController(set, 0.5f);
        return controller;
	}

	@SuppressLint("NewApi")
	private LayoutTransition createLayoutTransition() {
		LayoutTransition lTransition = new LayoutTransition();

		PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(View.SCALE_X, 2);
		PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 2);
		ObjectAnimator animAppear =
		    ObjectAnimator.ofPropertyValuesHolder(mContent, pvhX, pvhY);
		lTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, animAppear);

		ObjectAnimator animIn = ObjectAnimator.ofFloat(null, "rotationX", 0f,
				360f).setDuration(
				lTransition.getDuration(LayoutTransition.APPEARING));
		lTransition.setAnimator(LayoutTransition.APPEARING, animIn);
		lTransition.enableTransitionType(LayoutTransition.CHANGE_APPEARING);
		Log.v("xxx", "##################:" + lTransition.isTransitionTypeEnabled(LayoutTransition.CHANGE_APPEARING));
		// ObjectAnimator animExists = ObjectAnimator.ofFloat(null, "rotationY",
		// 0f, 360f).setDuration(1000);
		// lTransition.setAnimator(LayoutTransition.CHANGE_APPEARING,
		// animExists);

		// lTransition.setDuration(2000);
		return lTransition;
	}

}
