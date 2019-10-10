package com.zhengsr.cusbottomlib;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;


public class SavedState extends View.BaseSavedState {

    //记录上一次的记录
    public int preIndex;

    public SavedState(Parcel source) {
        super(source);
        preIndex = source.readInt();
    }

    public SavedState(Parcelable superState) {
        super(superState);
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeInt(preIndex);
    }

    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>(){

        @Override
        public SavedState createFromParcel(Parcel source) {
            return new SavedState(source);
        }

        @Override
        public SavedState[] newArray(int size) {
            return new SavedState[size];
        }
    };
}