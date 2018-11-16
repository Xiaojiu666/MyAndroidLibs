package com.xiaojiu.studylibs.ViewGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/2/28 0028.
 */

public class MyRelatiLayout extends ViewGroup {
    public MyRelatiLayout(Context context) {
        super(context);
    }

    public MyRelatiLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRelatiLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        //   KLog.e(attrs.getAttributeCount());
        return new CustomLayoutParams(getContext(), attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new CustomLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new CustomLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof CustomLayoutParams;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int layoutWidth = 0;
        int layoutHeight = 0;
        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int cWidth = 0;
        int cHeight = 0;
        int count = getChildCount();

        if (widthMode == MeasureSpec.EXACTLY) {
            //如果布局容器的宽度模式是确定的（具体的size或者match_parent），直接使用父窗体建议的宽度
            layoutWidth = sizeWidth;
        } else {
            //如果是未指定或者wrap_content，我们都按照包裹内容做，宽度方向上只需要拿到所有子控件中宽度做大的作为布局宽度
            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                cWidth = child.getMeasuredWidth();
                //获取子控件最大宽度
                layoutWidth = cWidth > layoutWidth ? cWidth : layoutWidth;
            }
        }
        //高度很宽度处理思想一样
        if (heightMode == MeasureSpec.EXACTLY) {
            layoutHeight = sizeHeight;
        } else {
            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                cHeight = child.getMeasuredHeight();
                layoutHeight = cHeight > layoutHeight ? cHeight : layoutHeight;
            }
        }

        // 测量并保存layout的宽高
        setMeasuredDimension(layoutWidth, layoutHeight);
    }

/*    public void setTest(String... a) {

    }*/

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        final int count = getChildCount();
        int childMeasureWidth = 0;
        int childMeasureHeight = 0;
        CustomLayoutParams params = null;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            // 注意此处不能使用getWidth和getHeight，这两个方法必须在onLayout执行完，才能正确获取宽高
            childMeasureWidth = child.getMeasuredWidth();
            childMeasureHeight = child.getMeasuredHeight();
            params = (CustomLayoutParams) child.getLayoutParams();
            switch (params.position) {
                case CustomLayoutParams.POSITION_MIDDLE:    // 中间
                    left = (getWidth() - childMeasureWidth) / 2;
                    top = (getHeight() - childMeasureHeight) / 2;
                    break;
                case CustomLayoutParams.POSITION_LEFT:      // 左上方
                    left = 0;
                    top = 0;
                    break;
                case CustomLayoutParams.POSITION_RIGHT:     // 右上方
                    left = getWidth() - childMeasureWidth;
                    top = 0;
                    break;
                case CustomLayoutParams.POSITION_BOTTOM:    // 左下角
                    left = 0;
                    top = getHeight() - childMeasureHeight;
                    break;
                case CustomLayoutParams.POSITION_RIGHTANDBOTTOM:// 右下角
                    left = getWidth() - childMeasureWidth;
                    top = getHeight() - childMeasureHeight;
                    break;
                default:
                    break;
            }

            // 确定子控件的位置，四个参数分别代表（左上右下）点的坐标值
            child.layout(left, top, left + childMeasureWidth, top + childMeasureHeight);
        }
    }

}
