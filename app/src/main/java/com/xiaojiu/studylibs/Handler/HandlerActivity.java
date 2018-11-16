package com.xiaojiu.studylibs.Handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.socks.library.KLog;
import com.test.myselfview.R;


/**
 * Created by Administrator on 2018/3/15 0015.
 */

public class HandlerActivity extends AppCompatActivity implements Runnable {
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            KLog.e(msg.what);
            switch (msg.what) {
                case 1:
                    mText.setText("执行了线程1的UI操作");
                    break;
                case 2:
                    mText.setText("执行了线程2的UI操作");
                    break;
            }
        }
    };

    private TextView mText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        mText = (TextView) findViewById(R.id.textView);
        HandlerThread mHandlerThread = new HandlerThread("handlerThread");
        //new Handler(mHandlerThread.getLooper())




        new Thread(this).start();
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = Message.obtain();
                msg.what = 2; //消息的标识
                msg.obj = "B"; // 消息的存放
                mHandler.sendMessage(msg);
            }
        }.start();

    }

    @Override
    public void run() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Message msg = Message.obtain();
        msg.what = 1; // 消息标识
        msg.obj = "A"; // 消息内存存放
        mHandler.sendMessage(msg);
    }
}
