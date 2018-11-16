package com.xiaojiu.studylibs.Html;

import android.webkit.JavascriptInterface;

import com.socks.library.KLog;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class AndroidtoJs extends Object {

    // 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    public void hello(String msg) {
        KLog.e(msg);
        KLog.e("JS调用了Android的hello方法");
        //   System.out.println("JS调用了Android的hello方法");
    }
}
