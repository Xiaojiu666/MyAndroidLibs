package com.xiaojiu.studylibs.GuideActivity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.test.myselfview.R;
import com.xiaojiu.studylibs.GuideItemBean;
import com.xiaojiu.studylibs.adapter.BaseRecyclerAdapter;
import com.xiaojiu.studylibs.base.BaseAppCompatActivity;
import com.xiaojiu.studylibs.view.CustomViewActivity;
import com.xiaojiu.studylibs.view.FitAcitivity;

import java.util.ArrayList;

import butterknife.BindView;

public class GuideViewActivity extends BaseAppCompatActivity implements BaseRecyclerAdapter.OnItemCliclkListener {

    @BindView(R.id.guide_view_recyceler_view)
    RecyclerView mViewGuideRecycelerView;
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    private String[] titleNames = new String[]{"自定义View介绍", "自定ViewLibs库", "适配问题"};
    private String[] titleDescs = new String[]{"自定义VIew的介绍，源码分析", "一些网上搜集、开发中使用到的库", "使用插件+多分辨率下不同的demens进行适配"};
    private ArrayList<GuideItemBean> guideItemBeans;
    private BaseRecyclerAdapter mGuideRecyclerAdapter;

    @Override
    protected void initOnClick() {
        mGuideRecyclerAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        guideItemBeans = new ArrayList<GuideItemBean>();
        for (int i = 0; i < titleNames.length; i++) {
            GuideItemBean guideItemBean = new GuideItemBean();
            guideItemBean.setTitleName(titleNames[i]);
            guideItemBean.setTitleDesc(titleDescs[i]);
            guideItemBeans.add(guideItemBean);
        }
    }

    @Override
    protected void initView() {
        initToobar(myToolbar);
        mGuideRecyclerAdapter = new BaseRecyclerAdapter(this, guideItemBeans);
        mViewGuideRecycelerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mViewGuideRecycelerView.setAdapter(mGuideRecyclerAdapter);
    }

    @Override
    public int getResLayout() {
        return R.layout.activity_guide_view;
    }

    @Override
    public void onClick(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(GuideViewActivity.this, CustomViewActivity.class));
                break;
            case 2:
                startActivity(new Intent(GuideViewActivity.this, FitAcitivity.class));
                break;
        }
    }
}
