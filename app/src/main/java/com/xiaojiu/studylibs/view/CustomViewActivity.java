package com.xiaojiu.studylibs.view;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.test.myselfview.R;
import com.xiaojiu.studylibs.base.BaseAppCompatActivity;

import butterknife.BindView;

public class CustomViewActivity extends BaseAppCompatActivity {
    @BindView(R.id.custom_tv_desc)
    TextView mCustomTvDesc;
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;

    @Override
    protected void initOnClick() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        initToobar(myToolbar);
        initDesc();
    }

    private void initDesc() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("   父控件会调用子控件的onMeasure方法询问子控件：" +
                "“你有多大的尺寸，我要给你多大的地方才能容纳你？”，" +
                "   然后传入两个参数（widthMeasureSpec和heightMeasureSpec）" +
                "   两个规格参数是由父控件计算好的，根据父控件和子控件的宽高布局来进行及计算 " + "\n" +
                "   父为EXACTLY(匹配&&精确值)，子为匹配&&精确值属性时，子约束为EXACTLY" +
                "反之子为包裹时，子约束为At_Most" + "\n" +
                "   父为At_Most(包裹)，子为精确值属性时，子约束为EXACTLY" +
                "反之子为包裹时&&匹配时，子约束为At_Most" + "\n" +
                "   父为UNSPECIFIED时，子为精确值属性时，子约束为EXACTLY" +
                "反之子为包裹时&&匹配时，子约束为UNSPECIFIED" + "\n" +
                "\n\n");


  /*      stringBuffer.append(" 当布局容器宽高为match_parent时:\n");
        stringBuffer.append("   子View 为什么wrap_content时 得到的测量模式是AT_MOST、测量值为屏幕的宽高(高需要去除导航栏高度)\n");
        stringBuffer.append("   子View 为match_parent/精确的DP时 得到的测量模式是EXACTLY、测量值为屏幕的宽高(高需要去除导航栏高度)/精确的值\n");*/
        stringBuffer.append("问题1:为什么wrap_content时，测量值为屏幕的宽高 \n");
        stringBuffer.append("   子布局使用时，没办法包裹， 需要在onMeasure方法里根据MeasureSpec 进行需求自定义 详见:customView \n");
        stringBuffer.append("问题2:子布局使用时，如何包裹自己\n");
        stringBuffer.append("   子布局使用时，没办法包裹， 需要在onMeasure方法里根据MeasureSpec 进行需求自定义 详见:customView \n");
        mCustomTvDesc.setText(stringBuffer);
    }

    @Override
    public int getResLayout() {
        return R.layout.activity_custom_view;
    }

}
