package com.xiaojiu.studylibs.GuideActivity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.test.myselfview.R;
import com.xiaojiu.studylibs.CommanCode.ImageCompressActivity;
import com.xiaojiu.studylibs.CommanCode.QRsacnActivity;
import com.xiaojiu.studylibs.CommanCode.RxJavaCommonActivity;
import com.xiaojiu.studylibs.GuideItemBean;
import com.xiaojiu.studylibs.adapter.GuideRecyclerAdapter;
import com.xiaojiu.studylibs.base.BaseAppCompatActivity;
import com.xiaojiu.studylibs.manager.DownLoadManager;

import java.util.ArrayList;

import butterknife.BindView;

public class GuideCommandCodeActivity extends BaseAppCompatActivity implements GuideRecyclerAdapter.OnItemCliclkListener {
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.command_code_recyceler_view)
    RecyclerView mCommandCodeRecycelerView;
    private String[] titleNames = new String[]{"图片压缩", "检查更新", "Zxing二维码扫描","Rxjava"};
    private String[] titleDescs = new String[]{"通过BitmapFactory对图片进行采样压缩", "使用Google提供的DownLoadManager用来下载Apk文件", "使用Zxing框架进行二维码解码,并且自定义了扫描页面的布局","Rxjava一些常用的类和方法"};
    private ArrayList<GuideItemBean> guideItemBeans;
    private GuideRecyclerAdapter mGuideRecyclerAdapter;
    public final static int CUSTOMIZED_REQUEST_CODE = 0x0000ffff;

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
            case 1:
                //此处下载的是支付宝的APP地址
                new DownLoadManager(this, "https://t.alipayobjects.com/L1/71/100/and/alipay_wap_main.apk");
                break;
            case 2:
                IntentIntegrator intentIntegrator = new IntentIntegrator(this).setCaptureActivity(QRsacnActivity.class).setBeepEnabled(false);
                intentIntegrator.setPrompt("请对准二维码/条形码");
                intentIntegrator.initiateScan();
                intentIntegrator.setBeepEnabled(false);
                break;
            case 3:
                startActivity(new Intent(GuideCommandCodeActivity.this, RxJavaCommonActivity.class));
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != CUSTOMIZED_REQUEST_CODE && requestCode != IntentIntegrator.REQUEST_CODE) {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        switch (requestCode) {
            case CUSTOMIZED_REQUEST_CODE: {
                break;
            }
            default:
                break;
        }
        IntentResult result = IntentIntegrator.parseActivityResult(resultCode, data);
        //KLog.e();
        Toast.makeText(mContext, result.getContents(), Toast.LENGTH_LONG).show();
    }
}
