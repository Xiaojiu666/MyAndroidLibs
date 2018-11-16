package com.xiaojiu.studylibs.TouchEvent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.socks.library.KLog;

/**
 * Created by Administrator on 2018/3/1 0001.
 */

public class Mybutton extends Button {

    public Mybutton(Context context) {
        super(context);
    }

    public Mybutton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Mybutton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        KLog.e("onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        KLog.e("dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }
}
