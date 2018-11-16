package com.xiaojiu.studylibs.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {
    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);//得到模式
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);//得到大小
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);//得到模式
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);//得到大小
  /*      KLog.e(widthSpecMode);
        // 1.自定义包裹类型*/
        int width;
        if (widthSpecMode == MeasureSpec.EXACTLY) {
            //如果match_parent或者具体的值，直接赋值
            width = widthSpecSize;
        } else {
            //根据需求内容自定义包裹的宽高
            width = 100;
        }
        setMeasuredDimension(width, heightSpecSize);
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        //KLog.e("changed" + changed + "left" + left + "top" + top + "right" + right + "bottom" + bottom);
        //super.onLayout(changed, left, top, 800, bottom);
    }
}
