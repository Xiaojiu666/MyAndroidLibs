package com.xiaojiu.studylibs.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.socks.library.KLog;
import com.xiaojiu.studylibs.service.DownloadService;

import static android.content.Context.BIND_AUTO_CREATE;

public class DownLoadManager {

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DownloadService.DownloadBinder binder = (DownloadService.DownloadBinder) service;
            DownloadService downloadService = binder.getService();

            //接口回调，下载进度
            downloadService.setOnProgressListener(new DownloadService.OnProgressListener() {
                @Override
                public void onProgress(float fraction) {
                    KLog.e("fraction" + fraction);
                    if (fraction == DownloadService.UNBIND_SERVICE) {
                        // unbindService(conn);d
                        //isBindService = false;
                        // ToastUtils.showToast((), "下载完成！");
                    }
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public DownLoadManager(Context mContext, String apkUrl) {
        Intent intent = new Intent(mContext, DownloadService.class);
        intent.putExtra(DownloadService.BUNDLE_KEY_DOWNLOAD_URL, apkUrl);
        mContext.bindService(intent, conn, BIND_AUTO_CREATE);
    }
}
