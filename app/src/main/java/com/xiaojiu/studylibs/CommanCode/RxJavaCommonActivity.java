package com.xiaojiu.studylibs.CommanCode;

import android.support.v7.widget.Toolbar;

import com.socks.library.KLog;
import com.test.myselfview.R;
import com.xiaojiu.studylibs.base.BaseAppCompatActivity;

import butterknife.BindView;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

public class RxJavaCommonActivity extends BaseAppCompatActivity {
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;

    @Override
    protected void initOnClick() {

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("1111");
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                KLog.e(s);
            }
        });
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
        return R.layout.activity_command_code_rxjava;
    }

}
