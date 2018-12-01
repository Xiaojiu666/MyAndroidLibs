package com.xiaojiu.studylibs.CommanCode;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.test.myselfview.R;
import com.xiaojiu.studylibs.base.BaseAppCompatActivity;

import butterknife.BindView;


/**
 *  参考资料: https://www.jianshu.com/p/f862b73d07f7
 *            https://www.jianshu.com/p/b85812b6f7c1
 */
public class QRsacnActivity extends BaseAppCompatActivity {
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.zxing_barcode_scanner)
    DecoratedBarcodeView zxingBarcodeScanner;
    private CaptureManager capture;

    @Override
    protected void initOnClick() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        initToobar(myToolbar);
    }

    @Override
    public int getResLayout() {
        return R.layout.activity_scan_qr;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

        capture = new CaptureManager(this, zxingBarcodeScanner);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.decode();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        zxingBarcodeScanner.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        zxingBarcodeScanner.pause();
    }
}
