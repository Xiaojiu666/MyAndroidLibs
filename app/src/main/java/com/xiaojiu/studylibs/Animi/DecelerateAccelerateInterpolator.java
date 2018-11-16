package com.xiaojiu.studylibs.Animi;

import android.animation.TimeInterpolator;

import com.socks.library.KLog;

/**
 * Created by Administrator on 2018/3/1 0001.
 */

public class DecelerateAccelerateInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        float result;
        KLog.e(input);
        if (input <= 0.5) {
            result = (float) (Math.sin(Math.PI * input)) / 2;
        } else {
            result = (float) (2 - Math.sin(Math.PI * input)) / 2;
        }
        return result;
    }
}
