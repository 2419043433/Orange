package com.orange.ui.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.view.ViewGroup;

public class ListLayoutTransition extends LayoutTransition {
	private final Animator mAppearAnimator;
	private final Animator mDisappearAnimator;
	private final Animator mAppearChangeAnimator;
	private final Animator mDisappearChangeAnimator;

	public ListLayoutTransition() {
		// Add
		mAppearAnimator = ObjectAnimator.ofFloat(null, "rotationY", 90f, 0f)
				.setDuration(getDuration(LayoutTransition.APPEARING));
		mAppearAnimator.addListener(new AnimatorListenerAdapter() {
			public void onAnimationEnd(Animator anim) {
				View view = (View) ((ObjectAnimator) anim).getTarget();
				view.setRotationY(0f);
			}
		});

		// Removing
		mDisappearAnimator = ObjectAnimator.ofFloat(null, "rotationX", 0f, 90f)
				.setDuration(getDuration(LayoutTransition.DISAPPEARING));
		mDisappearAnimator.addListener(new AnimatorListenerAdapter() {
			public void onAnimationEnd(Animator anim) {
				View view = (View) ((ObjectAnimator) anim).getTarget();
				view.setRotationX(20f);
			}
		});

		// Changing while Adding
		PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left", 0, 1);
		PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("top", 0, 1);
		PropertyValuesHolder pvhRight = PropertyValuesHolder.ofInt("right", 0,
				1);
		PropertyValuesHolder pvhBottom = PropertyValuesHolder.ofInt("bottom",
				0, 1);
		PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("scaleX",
				1f, 0f, 1f);
		PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat("scaleY",
				1f, 0f, 1f);
		mAppearChangeAnimator = ObjectAnimator.ofPropertyValuesHolder(this,
				pvhLeft, pvhTop, pvhRight, pvhBottom, pvhScaleX, pvhScaleY)
				.setDuration(getDuration(LayoutTransition.CHANGE_APPEARING));
		mAppearChangeAnimator.addListener(new AnimatorListenerAdapter() {
			public void onAnimationEnd(Animator anim) {
				View view = (View) ((ObjectAnimator) anim).getTarget();
				view.setScaleX(1f);
				view.setScaleY(1f);
			}
		});

		// Changing while Removing
		Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
		Keyframe kf1 = Keyframe.ofFloat(.9999f, 360f);
		Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
		PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe(
				"rotation", kf0, kf1, kf2);
		mDisappearChangeAnimator = ObjectAnimator.ofPropertyValuesHolder(this,
				pvhLeft, pvhTop, pvhRight, pvhBottom, pvhRotation).setDuration(
				getDuration(LayoutTransition.CHANGE_DISAPPEARING));
		mDisappearChangeAnimator.addListener(new AnimatorListenerAdapter() {
			public void onAnimationEnd(Animator anim) {
				View view = (View) ((ObjectAnimator) anim).getTarget();
				view.setRotation(0f);
			}
		});
		this.setAnimator(LayoutTransition.APPEARING, mAppearAnimator);
		this.setAnimator(LayoutTransition.DISAPPEARING, mDisappearAnimator);
		this.setAnimator(LayoutTransition.CHANGE_APPEARING,
				mAppearChangeAnimator);
		this.setAnimator(LayoutTransition.CHANGE_DISAPPEARING,
				mDisappearChangeAnimator);

	}
}