package com.xiaojiu.studylibs.manager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class ImageCompressManager {

    private File mImagePath;

    public ImageCompressManager(File mImagePath) {
        this.mImagePath = mImagePath;
    }

    public static ImageCompressManager getInstance(File mImagePath) {
        ImageCompressManager mImageCompressManager = null;
        if (mImageCompressManager == null) {
            mImageCompressManager = new ImageCompressManager(mImagePath);
        }
        return mImageCompressManager;
    }

    public Bitmap getCompressBitmap() {
        //1.RGB介绍
        //1.1 A：透明度 R：红色 G：绿 B：蓝
        //1.2 Bitmap.Config ARGB_4444：每个像素占四位，即A=4，R=4，G=4，B=4，那么一个像素点占4+4+4+4=16位
        //
        //Bitmap.Config ARGB_8888：每个像素占四位，即A=8，R=8，G=8，B=8，那么一个像素点占8+8+8+8=32位
        //
        //Bitmap.Config RGB_565：每个像素占四位，即R=5，G=6，B=5，没有透明度，那么一个像素点占5+6+5=16位
        //
        //Bitmap.Config ALPHA_8：每个像素占四位，只有透明度，没有颜色。
        //------------------------------------------------------------------------------------------------
        //2. 内存计算方式
        //一张 1024 * 1024 像素，采用ARGB8888格式，一个像素32位，每个像素就是4字节，占有内存就是4M若采用RGB565，一个像素16位，每个像素就是2字节，占有内存就是2M。
        //Glide加载图片默认格式RGB565，Picasso为ARGB8888，默认情况下，Glide占用内存会比Picasso低，色彩不如Picasso鲜艳，自然清晰度就低。
        //通常我们优化Bitmap时，当需要做性能优化或者防止OOM（Out Of Memory），我们通常会使用Bitmap.Config.RGB_565这个配置，因为Bitmap.Config.ALPHA_8只有透明度，显示一般图片没有意义，Bitmap.Config.ARGB_4444显示图片不清楚，Bitmap.Config.ARGB_8888占用内存最多。
        //
        //图片加载
        //如果我们想要加载一张大图到内存中，如果不进行压缩的话，那么很显然就会出现OOM的崩溃，
        //------------------------------------------------------------------------------------------------
        //3.图片采样压缩
        // 步骤1：获取原始图片的宽、高的信息只用于采样的计算
        // 1.1: 创建Options给BitMapFactiry内部解码器传递参数
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 1.2设置inJuetDecodeBounds 来控制解码器。只进行图片高度和宽度的获取不会加载Bitmap
        // 不占用内存，当时用这个参数，代表BitmapFactory.decodeXxx类似的方法，不会返回Bitmap
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mImagePath.getAbsolutePath(), options);
        //BitmapFactory.decodeByteArray(data, 0, data.length, options);

        // 2：根据图片的真实的尺寸，和当前需要显示的尺寸，进行计算，生成图片的采样率。
        // 2.2 准备显示在手机上的尺寸 //尺寸是根据程序需要设置的。
        int size = calculateInSampleSize(options, 1440, 2560);
        //2. 3 根据图片宽高得到压缩比例 计算和设置采样率
        options.inJustDecodeBounds = false;
        options.inSampleSize = size;
        //2.4.1 要求对每一个采样的像素使用RGB_565存储方式
        // 一个像素占用两个字节比 ARGB_8888小了一半
        // 如果解码器不能使用指定的配置那么自动使用 ARGB_8888
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeFile(mImagePath.getAbsolutePath(), options);
        //Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        return bitmap;
    }


    /**
     * 官方计算代码
     * 根据用户所给出的宽度和高度计算出来压缩比例
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1; //当请求的宽度高度大域名的时候进行缩放
        if (reqWidth > 0 && reqHeight > 0) {
            //如果图片的宽高只要有一个大于屏幕的宽高，就需要对图片进行压缩 ，
            if (height > reqHeight || width > reqWidth) {
                final int halfHeight = height / 2;
                final int halfWidth = width / 2;
                // Calculate the largest inSampleSize value that is a power of 2 and keeps both
                // height and width larger than the requested height and width.
                // 当图片宽的一半/采样率任然大于屏幕尺寸，继续增加采样率 2的倍数，
                while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                    inSampleSize *= 2;
                }
            }
        }
        return inSampleSize;
    }

    /**
     * @param
     * @param
     * @return byte[]
     * @throws Exception
     */
    public static byte[] readStream(File file) {
        byte[] buffer = new byte[1024];
        int len = -1;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            while ((len = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            byte[] data = outStream.toByteArray();
            outStream.close();
            inputStream.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
