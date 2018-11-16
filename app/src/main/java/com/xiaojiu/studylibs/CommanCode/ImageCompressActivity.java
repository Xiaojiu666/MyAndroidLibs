package com.xiaojiu.studylibs.CommanCode;

import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.awen.photo.photopick.bean.PhotoResultBean;
import com.awen.photo.photopick.controller.PhotoPickConfig;
import com.test.myselfview.R;
import com.xiaojiu.studylibs.base.BaseAppCompatActivity;
import com.xiaojiu.studylibs.manager.ImageCompressManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import butterknife.BindView;

public class ImageCompressActivity extends BaseAppCompatActivity {
    @BindView(R.id.image_compress_btn_selector)
    Button mImageCompressBtnSelector;
    @BindView(R.id.image_compress_iv_after)
    ImageView mImageCompressIvAfter;


    @Override
    protected void initOnClick() {
        //
        //PermissionGen.needPermission(this, 200, "android.permission.WRITE_EXTERNAL_STORAGE");

        mImageCompressBtnSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PhotoPickConfig.Builder(ImageCompressActivity.this).pickMode(PhotoPickConfig.MODE_SINGLE_PICK).maxPickSize(15).showCamera(false).clipPhoto(false).spanCount(4).showGif(true).setOnPhotoResultCallback(new PhotoPickConfig.Builder.OnPhotoResultCallback() {
                    @Override
                    public void onResult(PhotoResultBean photoResultBean) {
                        File file = new File(photoResultBean.getPhotoLists().get(0));
                        //1.获取bitmap压缩前大小
                       /* try {
                            FileInputStream fis = new FileInputStream(file);
                            KLog.e(fis.available());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }*/
                        Bitmap compressBitmap = ImageCompressManager.getInstance(file).getCompressBitmap();
                        //2.保存bitmap 到本地
                        saveBitmapTofile(compressBitmap, "compress_after.png");
                        mImageCompressIvAfter.setImageBitmap(compressBitmap);
                    }
                }).build();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {


    }

    @Override
    public int getResLayout() {
        return R.layout.activity_iamge_compress;
    }


    //将bitmap写入到文件中
    private boolean saveBitmapTofile(Bitmap bmp, String filename) {
        if (bmp == null || filename == null)
            return false;
        Bitmap.CompressFormat format = Bitmap.CompressFormat.JPEG;
        int quality = 100;
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/picAAAA/2018年11月06日" + filename);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bmp.compress(format, quality, stream);
    }

}
