package com.xiaojiu.studylibs.GuideActivity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.test.myselfview.R;
import com.xiaojiu.studylibs.GuideItemBean;
import com.xiaojiu.studylibs.adapter.GuideRecyclerAdapter;
import com.xiaojiu.studylibs.algorithm.AlgorithmAaddBActivity;
import com.xiaojiu.studylibs.algorithm.AlgorithmTailZeroActivity;
import com.xiaojiu.studylibs.base.BaseAppCompatActivity;

import java.util.ArrayList;

import butterknife.BindView;

public class GuideAlgorithmActivity extends BaseAppCompatActivity implements GuideRecyclerAdapter.OnItemCliclkListener {
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.guide_alagorithm_recyceler_view)
    RecyclerView mGuideAlagorithmRecycelerView;
    private String[] titleNames = new String[]{"A+B问题", "尾部的0"};
    private String[] titleDescs = new String[]{"给出两个整数 a 和 b , 求他们的和。\n使用位运算", "设计一个算法，计算出n阶乘中尾部零的个数"};
    private ArrayList<GuideItemBean> guideItemBeans;
    private GuideRecyclerAdapter mGuideRecyclerAdapter;

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
        mGuideRecyclerAdapter = new GuideRecyclerAdapter(this, guideItemBeans);
        mGuideAlagorithmRecycelerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mGuideAlagorithmRecycelerView.setAdapter(mGuideRecyclerAdapter);
    }

    @Override
    public int getResLayout() {
        return R.layout.activity_guide_algorithm;
    }

    @Override
    public void onClick(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(GuideAlgorithmActivity.this, AlgorithmAaddBActivity.class));
                break;
            case 1:
                startActivity(new Intent(GuideAlgorithmActivity.this, AlgorithmTailZeroActivity.class));
                break;
            case 2:
                //startActivity(new Intent(GuideActivity.this, GuideCommandCodeActivity.class));
                break;
        }
    }
}
