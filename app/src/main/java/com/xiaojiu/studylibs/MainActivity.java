package com.xiaojiu.studylibs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.socks.library.KLog;
import com.test.myselfview.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int TAKE_PICTURE = 1;
    public String imageLocalPath;
    private FileInputStream is;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String s1 = "123";
        String s2 = "123";
        String s5 = "123";
        String s3 = new String("123");
        String s4 = new String("123");
        KLog.e(s1 == s5);
        KLog.e(s2.hashCode());
        KLog.e(s3.hashCode());
        KLog.e(s4.hashCode());
        KLog.e(s1 == s3);
        KLog.e(s3 == s4);
        KLog.e(s1.equals(s3));
        KLog.e(s3.equals(s4));

   /*    ArrayList<? super Slef> arrayList = new ArrayList<>();
        arrayList.add(new Slef());
        arrayList.add(new Son());
        arrayList.add(new Father());
        arrayList.add(null);
        Slef son= arrayList.get(0);
        Son son1 = arrayList.get(0);
        Father son2 = arrayList.get(0);*/

       /* Point point1 = new Point(0, 0);
        Point point2 = new Point(300, 300);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), point1, point2);
        anim.setDuration(5000);
        anim.start();*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            is = new FileInputStream(imageLocalPath);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            iv.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        KLog.e("dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       /* switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                KLog.e("ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                KLog.e("ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                KLog.e("ACTION_MOVE");
                break;
        }*/
        KLog.e("onTouchEvent");
        return super.onTouchEvent(event);
    }
}
