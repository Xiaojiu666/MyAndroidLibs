package com.xiaojiu.studylibs.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.test.myselfview.R;

/**
 * Created by Administrator on 2018/2/26 0026.
 */

public class MatrixView extends View {
    private Paint mPaint1;

    public MatrixView(Context context) {
        this(context, null);
    }

    public MatrixView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MatrixView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint1 = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Matrix matrix = new Matrix();
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.donkey);
        matrix.setScale(0.5f, 0.5f);
        // 输出pts计算之前数据
        canvas.drawBitmap(bmp, matrix, mPaint1);
        super.onDraw(canvas);
    }
}
