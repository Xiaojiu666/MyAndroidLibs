package com.xiaojiu.studylibs.algorithm;

import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import com.test.myselfview.R;
import com.xiaojiu.studylibs.base.BaseAppCompatActivity;

import butterknife.BindView;

public class AlgorithmTailZeroActivity extends BaseAppCompatActivity {


    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.algorithm_webview)
    WebView mAlgorithmWebview;

    @Override
    protected void initOnClick() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        initToobar(myToolbar);
        mAlgorithmWebview.loadUrl("https://blog.csdn.net/surp2011/article/details/51168272");
    }

    @Override
    public int getResLayout() {
        return R.layout.activity_algorithm_tail_zero;
    }

}
