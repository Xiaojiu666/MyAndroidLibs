package com.xiaojiu.studylibs.GuideActivity;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.test.myselfview.R;
import com.xiaojiu.studylibs.GuideItemBean;
import com.xiaojiu.studylibs.adapter.BaseRecyclerAdapter;
import com.xiaojiu.studylibs.base.BaseAppCompatActivity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by xiaojiu on 2018/11/7 0006.
 */

public class GuideActivity extends BaseAppCompatActivity implements BaseRecyclerAdapter.OnItemCliclkListener {
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.guide_recyceler_view)
    RecyclerView mGuideRecycelerView;
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    private String[] titleNames = new String[]{"View", "设计模式", "开发常用模块", "数据结构"};
    private String[] titleDescs = new String[]{"包含View的介绍,使用,自定义View分析,以及收集的自定义ViewLibs", "包含了26类设计模式的介绍已经使用", "一些开发中常用的模块", "一些面试中常见的算法和数据结构"};
    private ArrayList<GuideItemBean> guideItemBeans;
    private BaseRecyclerAdapter mGuideRecyclerAdapter;


    @Override
    protected void initOnClick() {
        mGuideRecyclerAdapter.setOnItemClickListener(this);
    }

    @Override
    public void initView() {
        setToolbar(myToolbar);
        mGuideRecyclerAdapter = new BaseRecyclerAdapter(this, guideItemBeans);
        mGuideRecycelerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mGuideRecycelerView.setAdapter(mGuideRecyclerAdapter);

    }

    @Override
    public int getResLayout() {
        return R.layout.activity_guide;
    }

    public void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void initData() {
        guideItemBeans = new ArrayList<GuideItemBean>();
        for (int i = 0; i < titleNames.length; i++) {
            GuideItemBean guideItemBean = new GuideItemBean();
            guideItemBean.setTitleName(titleNames[i]);
            guideItemBean.setTitleDesc(titleDescs[i]);
            guideItemBeans.add(guideItemBean);
        }
    }


    @Override
    public void onClick(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(GuideActivity.this, GuideViewActivity.class));
                break;
            case 2:
                startActivity(new Intent(GuideActivity.this, GuideCommandCodeActivity.class));
                break;
            case 3:
                startActivity(new Intent(GuideActivity.this, GuideAlgorithmActivity.class));
                break;
        }
    }
}
