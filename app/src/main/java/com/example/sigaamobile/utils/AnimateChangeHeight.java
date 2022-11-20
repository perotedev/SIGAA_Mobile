package com.example.sigaamobile.utils;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

public class AnimateChangeHeight {
    private final View view;
    private final ValueAnimator slideAnimator;
    private AnimatorSet animationSet;

    public AnimateChangeHeight(View view, int newHeight){
        this.view = view;
        this.slideAnimator = ValueAnimator.ofInt(view.getMeasuredHeight(), newHeight).setDuration(300);
        this.animationSet = new AnimatorSet();
    }

    public void updateAnimate(){
        slideAnimator.addUpdateListener(this::onAnimationUpdate);
        this.startAnimate(this.slideAnimator);
    }

    private void startAnimate(ValueAnimator animator){
        this.animationSet = new AnimatorSet();
        this.animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
        this.animationSet.play(animator);
        this.animationSet.start();
    }

    private void onAnimationUpdate(ValueAnimator animation) {
        this.view.getLayoutParams().height = (Integer) animation.getAnimatedValue();
        this.view.requestLayout();
    }
}
