package com.xiaojiu.studylibs;

import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by Administrator on 2018/2/1 0001.
 */
public class FileUtils {

    public static String picPath = Environment.getExternalStorageDirectory()
            + "/Android/data/" + MyApp.getInstance().getPackageName() + "/pic" + "/" + DateUtils.getCurDate();

    public static void creatPicFile() {
        File picFile = new File(picPath);
        Log.e("requestCode", picFile.exists() + "");
        Log.e("picPath", picPath + "");

        if (!picFile.exists()) {
            picFile.mkdirs();
        }
    }
}
