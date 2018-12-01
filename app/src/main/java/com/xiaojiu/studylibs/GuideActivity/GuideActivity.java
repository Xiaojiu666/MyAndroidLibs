package com.xiaojiu.studylibs.GuideActivity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.socks.library.KLog;
import com.test.myselfview.R;
import com.xiaojiu.studylibs.GuideItemBean;
import com.xiaojiu.studylibs.adapter.GuideRecyclerAdapter;
import com.xiaojiu.studylibs.base.BaseAppCompatActivity;

import java.util.ArrayList;

import butterknife.BindView;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

/**
 * Created by xiaojiu on 2018/11/7 0006.
 */

public class GuideActivity extends BaseAppCompatActivity implements GuideRecyclerAdapter.OnItemCliclkListener {
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.guide_recyceler_view)
    RecyclerView mGuideRecycelerView;
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    private String[] titleNames = new String[]{"View", "设计模式", "开发常用模块", "数据结构"};
    private String[] titleDescs = new String[]{"包含View的介绍,使用,自定义View分析,以及收集的自定义ViewLibs", "包含了26类设计模式的介绍已经使用", "一些开发中常用的模块", "一些面试中常见的算法和数据结构"};
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    private ArrayList<GuideItemBean> guideItemBeans;
    private GuideRecyclerAdapter mGuideRecyclerAdapter;


    @Override
    protected void initOnClick() {
        mGuideRecyclerAdapter.setOnItemClickListener(this);
    }

    @Override
    public void initView() {
        setToolbar(myToolbar);
        toCheckPermission();

        mGuideRecyclerAdapter = new GuideRecyclerAdapter(this, guideItemBeans);
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

 /*       A a = new A();
        SoftReference softReference = new SoftReference(a);
        A o = (A) softReference.get();
         ReferenceQueue*/
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


    private boolean toCheckPermission() {
        int write_external_storage_granted = ActivityCompat.checkSelfPermission(this, permissions[0]);
        int camera_granted = ActivityCompat.checkSelfPermission(this, permissions[1]);
        if (PERMISSION_GRANTED != write_external_storage_granted || camera_granted != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, RP_WRITE);
            return false;
        }
        return true;
    }

    private static final int RP_WRITE = 2;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (RP_WRITE == requestCode) {
            KLog.e(grantResults.length);
            KLog.e(permissions.length);
            if (grantResults[0] == PERMISSION_GRANTED && grantResults[1] == PERMISSION_GRANTED) {
                //TODO continue
                // toStartAnmi();
            } else {
                //TODO show dialog to user
                //判断用户是否勾选 不再询问的选项，未勾选可以 说明权限作用，重新授权。
                boolean writePermission = ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0]);
                boolean cameraPermission = ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[1]);
                if (writePermission && cameraPermission) {
                    showPermissionsDialog(true);
                } else {
                    showPermissionsDialog(false);
                }
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void showPermissionsDialog(boolean isReTry) {
        AlertDialog.Builder builder = new AlertDialog
                .Builder(this)
                .setTitle("权限缺少")
                .setMessage("您有权限未进行授权，请重新授权，否则应用将无法正常使用本应用。\n 可通过'设置' -> '应用程序'->'权限设置'，重新设置应用权限。")
                .setNegativeButton("退出应用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
        if (isReTry) {
            builder.setPositiveButton("重新授权", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    toCheckPermission();
                }
            });
        } else {
            builder.setPositiveButton("打开权限", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    toSelfSetting(mContext);
                }
            });
        }
        builder.create().show();
    }


    /**
     * 跳转到应用程序的设置中心
     *
     * @param context
     */
    public static void toSelfSetting(Context context) {
        Intent mIntent = new Intent();
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            mIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            mIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            mIntent.setAction(Intent.ACTION_VIEW);
            mIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
            mIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        context.startActivity(mIntent);
    }
}
