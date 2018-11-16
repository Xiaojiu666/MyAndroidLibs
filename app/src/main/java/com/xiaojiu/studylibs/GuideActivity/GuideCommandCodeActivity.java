package com.xiaojiu.studylibs.GuideActivity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.test.myselfview.R;
import com.xiaojiu.studylibs.CommanCode.ImageCompressActivity;
import com.xiaojiu.studylibs.GuideItemBean;
import com.xiaojiu.studylibs.adapter.BaseRecyclerAdapter;
import com.xiaojiu.studylibs.base.BaseAppCompatActivity;

import java.util.ArrayList;

import butterknife.BindView;

public class GuideCommandCodeActivity extends BaseAppCompatActivity implements BaseRecyclerAdapter.OnItemCliclkListener {
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.command_code_recyceler_view)
    RecyclerView mCommandCodeRecycelerView;
    private String[] titleNames = new String[]{"图片压缩"};
    private String[] titleDescs = new String[]{"通过BitmapFactory对图片进行采样压缩"};
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
        mCommandCodeRecycelerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mCommandCodeRecycelerView.setAdapter(mGuideRecyclerAdapter);
    }

    @Override
    public int getResLayout() {
        return R.layout.activity_command_code_activity;
    }

    @Override
    public void onClick(int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(GuideCommandCodeActivity.this, ImageCompressActivity.class));
                break;
        }
    }
}
