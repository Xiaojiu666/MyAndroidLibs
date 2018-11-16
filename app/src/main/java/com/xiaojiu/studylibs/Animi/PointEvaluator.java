package com.xiaojiu.studylibs.Animi;

import android.animation.TypeEvaluator;

/**
 * Created by Administrator on 2018/2/28 0028.
 */

public class PointEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        //KLog.e(fraction);
        float startPoint = (float) startValue;
        float endPoint = (float) endValue;
        float result = startPoint + fraction * (endPoint - startPoint);
        return result;
    }
}
