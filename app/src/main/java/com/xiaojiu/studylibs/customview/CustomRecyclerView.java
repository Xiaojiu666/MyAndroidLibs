package com.xiaojiu.studylibs.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CustomRecyclerView extends RecyclerView {
    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
               /* KLog.e("ACTION_DOWN e.getX()" + e.getX());
                KLog.e("ACTION_DOWN e.getY()" + e.getY());*/
                break;
            case MotionEvent.ACTION_MOVE:
               /* KLog.e("ACTION_MOVE e.getX()" + e.getX());
                KLog.e("ACTION_MOVE e.getY()" + e.getY());*/
                break;
            case MotionEvent.ACTION_UP:
             /*   KLog.e("ACTION_UP e.getX()" + e.getX());
                KLog.e("ACTION_UP e.getY()" + e.getY());*/
                break;
        }

        return super.onTouchEvent(e);
    }
}
