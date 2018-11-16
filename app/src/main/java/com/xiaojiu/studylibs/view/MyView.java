package com.xiaojiu.studylibs.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.socks.library.KLog;
import com.test.myselfview.R;

/**
 * Created by Administrator on 2018/2/7 0007.
 */

public class MyView extends View {

    private int mColor;
    private String mText;
    private float mTextSize;
    private Rect mBound;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AAA, defStyleAttr, 0);
        mColor = a.getColor(R.styleable.AAA_mColor, Color.BLACK);
        mText = a.getString(R.styleable.AAA_mText);
        mTextSize = a.getDimension(R.styleable.AAA_mTextSize, 20);
/*        mPaint1 = new Paint();
        mPaint1.setColor(Color.BLUE);

        mPaint1.setStyle(Paint.Style.FILL);
        mPaint2 = new Paint();
        mPaint2.setColor(Color.YELLOW);
        mPaint2.setStyle(Paint.Style.FILL);*/
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);//得到模式
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);//得到大小
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);//得到模式
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);//得到大小
        KLog.e("widthSpecMode" + widthSpecMode);
        KLog.e("widthSpecSize" + widthSpecSize);
        int width;
        int height ;
        if (widthSpecMode == MeasureSpec.EXACTLY) {
            //如果match_parent或者具体的值，直接赋值
            width = widthSpecSize;
        } else {
            //如果是wrap_content，我们要得到控件需要多大的尺寸
            float textWidth = mBound.width();   //文本的宽度
            //控件的宽度就是文本的宽度加上两边的内边距。内边距就是padding值，在构造方法执行完就被赋值
            width = (int) (getPaddingLeft() + textWidth + getPaddingRight());
            Log.v("openxu", "文本的宽度:"+textWidth + "控件的宽度："+width);
        }
        //高度跟宽度处理方式一样
        if (heightSpecMode == MeasureSpec.EXACTLY) {
            height = heightSpecSize;
        } else {
            float textHeight = mBound.height();
            height = (int) (getPaddingTop() + textHeight + getPaddingBottom());
            Log.v("openxu", "文本的高度:"+textHeight + "控件的高度："+height);
        }
        //保存测量宽度和测量高度
        setMeasuredDimension(width, height);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        KLog.e("changed" + changed);
        KLog.e("left" + left);
        KLog.e("top" + top);
        KLog.e("right" + right);
        KLog.e("bottom" + bottom);
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        KLog.e("onDraw");
        Paint paint1 = new Paint();
        paint1.setColor(mColor);
        paint1.setStyle(Paint.Style.FILL);
        paint1.setTextSize(mTextSize);
        //获得绘制文本的宽和高
        mBound = new Rect();
        paint1.getTextBounds(mText, 0, mText.length(), mBound);
       // canvas.drawRect(0, 0, 400, 400, paint1);
        canvas.drawText(mText,getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2,paint1);
        // canvas.save();
        super.onDraw(canvas);
        //canvas.restore();
    }
}
