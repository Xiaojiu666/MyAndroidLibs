package com.xiaojiu.studylibs;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/3/7 0007.
 */

public class Pen implements Parcelable {
    private String mColor;
    private String mPenType;

    protected Pen(Parcel in) {
        mColor = in.readString();
        mPenType = in.readString();
    }

    public static final Creator<Pen> CREATOR = new Creator<Pen>() {
        @Override
        public Pen createFromParcel(Parcel in) {
            return new Pen(in);
        }

        @Override
        public Pen[] newArray(int size) {
            return new Pen[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mColor);
        dest.writeString(mPenType);
    }
}
