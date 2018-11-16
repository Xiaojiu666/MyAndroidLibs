package com.xiaojiu.studylibs.TouchEvent;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.socks.library.KLog;

/**
 * Created by Administrator on 2018/3/1 0001.
 */

public class MyLinerLayout extends LinearLayout {
    public MyLinerLayout(Context context) {
        super(context);
    }

    public MyLinerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                KLog.e("ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                KLog.e("ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:

                KLog.e("ACTION_MOVE");
                break;
        }
        KLog.e("onTouchEvent");
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        KLog.e("onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        KLog.e("dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }
}
