package com.xiaojiu.studylibs.view;

import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.socks.library.KLog;
import com.test.myselfview.R;
import com.xiaojiu.studylibs.base.BaseAppCompatActivity;

import butterknife.BindView;

public class FitAcitivity extends BaseAppCompatActivity {
    @BindView(R.id.fit_tv_false)
    TextView mFitTvFalse;
    @BindView(R.id.fit_tv_true)
    TextView mFitTvTrue;

    @Override
    protected void initOnClick() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("            已适配          " + "\n");
        stringBuffer.append("1.在Android Studio中安装ScreenMatch插件，如图：" + "\n");
        stringBuffer.append("2.在项目的默认values文件夹中需要一份dimens.xml文件" + "\n");
        stringBuffer.append("3.插件安装好后，在项目的任意目录或文件上右键，选择ScreenMatch选项。如图" + "\n");
        mFitTvTrue.setText(stringBuffer);
        StringBuffer stringBuffer1 = new StringBuffer();
        stringBuffer1.append("            未适配          " + "\n");
        stringBuffer1.append("1.在Android Studio中安装ScreenMatch插件，如图：" + "\n");
        stringBuffer1.append("2.在项目的默认values文件夹中需要一份dimens.xml文件" + "\n");
        stringBuffer1.append("3.插件安装好后，在项目的任意目录或文件上右键，选择ScreenMatch选项。如图" + "\n");
        mFitTvFalse.setText(stringBuffer1);
        ViewTreeObserver observer = mFitTvTrue.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout() {
                mFitTvTrue.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = mFitTvTrue.getMeasuredWidth();
                int height = mFitTvTrue.getMeasuredHeight();
                KLog.e("width"+width+"height"+height);
            }
        });
        ViewTreeObserver observer1 = mFitTvFalse.getViewTreeObserver();
        observer1.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout() {
                mFitTvFalse.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int width = mFitTvFalse.getMeasuredWidth();
                int height = mFitTvFalse.getMeasuredHeight();
                KLog.e("observer1width"+width+"observer1height"+height);
            }
        });

    }


    @Override
    public int getResLayout() {
        return R.layout.activity_fit_view;
    }

}
