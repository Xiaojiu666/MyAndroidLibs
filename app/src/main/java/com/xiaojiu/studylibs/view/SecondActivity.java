package com.xiaojiu.studylibs.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.myselfview.R;

/**
 * Created by Administrator on 2018/3/12 0012.
 */

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Button btn = (Button) findViewById(R.id.button);
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText("SecondActivityID:"+this.getTaskId()+"------------对象:"+ this.toString());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, ThridActivity.class));
            }
        });
    }
}
