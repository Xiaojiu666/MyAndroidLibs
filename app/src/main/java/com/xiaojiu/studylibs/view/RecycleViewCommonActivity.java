package com.xiaojiu.studylibs.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.test.myselfview.R;
import com.xiaojiu.studylibs.adapter.BaseRecyclerViewAdapter;
import com.xiaojiu.studylibs.base.BaseAppCompatActivity;
import com.xiaojiu.studylibs.customview.CustomMyView;
import com.xiaojiu.studylibs.customview.CustomRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author Administrator
 */
public class RecycleViewCommonActivity extends BaseAppCompatActivity {
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.my_recycler_view)
    CustomRecyclerView myRecyclerView;
    @BindView(R.id.refreshable_view)
    CustomMyView refreshableView;
    private ArrayList<String> mDatas;

    @Override
    protected void initOnClick() {
        refreshableView.setOnRefreshListener(new CustomMyView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                refreshableView.sleep(10000);
            }
        });
      /*  refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                refreshableView.finishRefreshing();
            }
        }, 0);*/
    }

    @Override
    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }


    @Override
    protected void initView() {
        initToobar(myToolbar);
        BaseRecyclerViewAdapter baseRecyclerViewAdapter = new BaseRecyclerViewAdapter();
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //myRecyclerView.scrollToV;
        baseRecyclerViewAdapter.setList(mDatas);
        myRecyclerView.setAdapter(baseRecyclerViewAdapter);
    }

    @Override
    public int getResLayout() {
        return R.layout.activity_recyceler_common;
    }


}
