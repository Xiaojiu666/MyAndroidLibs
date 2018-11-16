package com.xiaojiu.studylibs.Animi;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2018/2/28 0028.
 */

public class PressView extends View {

    private Paint mPaint;

    private float mCircleX = 120;

    private float mCircleY = 120;

    private float mCircleRadius = 120;

    private float currentRadius;

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        mPaint.setColor(Color.parseColor(color));
        invalidate();
    }

    private int mCirleColor = Color.BLACK;


    public PressView(Context context) {
        this(context, null);
    }

    public PressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public PressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        currentRadius = mCircleRadius;
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了
        mPaint.setColor(mCirleColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCircleX = getMeasuredWidth() / 2;
        mCircleY = getMeasuredHeight() / 2;
        canvas.drawCircle(mCircleX, mCircleY, currentRadius, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                openAnimation();
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return true;
    }

    private void openAnimation() {
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new PointEvaluator(), mCircleRadius, (float) getMeasuredWidth());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentRadius = (float) animation.getAnimatedValue();
                invalidate();
            }
        });

        valueAnimator.setInterpolator(new DecelerateAccelerateInterpolator());
        ObjectAnimator valueAnimator2 = ObjectAnimator.ofObject(this, "color", new ColorEvaluator(),
                "#0000FF", "#FF0000");
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(valueAnimator).with(valueAnimator2);
        animSet.setDuration(5000);
        animSet.start();
    }
}
