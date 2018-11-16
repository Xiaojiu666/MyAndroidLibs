package com.xiaojiu.studylibs.Html;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.socks.library.KLog;
import com.test.myselfview.R;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by Administrator on 2018/3/8 0008.
 */

public class WebViewActivity extends AppCompatActivity {

    private WebView mWebView;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web);
        mWebView = (WebView) findViewById(R.id.webview);
        //mWebView.loadUrl();
        WebSettings webSettings = mWebView.getSettings();
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        Android2JS();
        //mehtod1JS2Android();
       /* mehtod2JS2Android();
        mehtod3JS2Android();*/


    }

    private void mehtod3JS2Android() {

    }

    private void mehtod2JS2Android() {
        mWebView.loadUrl("file:///android_asset/hlsTest");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri uri = Uri.parse(url);
                KLog.e(url);
                KLog.e(uri.toString());
                KLog.e(uri.getScheme());
                KLog.e(uri.getAuthority());
                if (uri.getScheme().equals("js")) {
                    if (uri.getAuthority().equals("www.baidu.com")) {
                        HashMap<String, String> params = new HashMap<>();
                        Set<String> collection = uri.getQueryParameterNames();
                        KLog.e(collection.size());
                    }
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }

    private void mehtod1JS2Android() {
        mWebView.addJavascriptInterface(new AndroidtoJs(), "test");//AndroidtoJS类对象映射到js的test对象
        mWebView.loadUrl("file:///android_asset/Js2Android.html");
    }

    private void Android2JS() {
        mWebView.loadUrl("file:///android_asset/hlsTest.html");
        mWebView.getSettings().setBuiltInZoomControls(false);
       /* button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (version < 18) {
                    mWebView.loadUrl("javascript:callJS(" + "1111" + ")");
                    KLog.e("js 返回的结果" + "<18");
                } else {
                    mWebView.evaluateJavascript("javascript:callJS(" + "1111" + ")", new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {
                            //此处为 js 返回的结果
                            KLog.e("js 返回的结果" + value);
                        }
                    });
                }
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(WebViewActivity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }
        });*/


    }
}