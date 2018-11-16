package com.xiaojiu.studylibs.ReacyclerView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.test.myselfview.R;

/**
 * Created by Administrator on 2018/3/15 0015.
 */

public class RecyclerTest extends AppCompatActivity {
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        recyclerView = (RecyclerView) findViewById(R.id.guide_recyceler_view);
    }
}
