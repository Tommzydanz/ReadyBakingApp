package com.example.readybakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Steps implements Parcelable {

    private  String mId;
    private  String mShortDescription;
    private  String mLongDescription;
    private  String mVideoDescription;

    public Steps(String mId, String mShortDescription, String mLongDescription, String mVideoDescription){
        this.mId = mId;
        this.mShortDescription = mShortDescription;
        this.mVideoDescription = mVideoDescription;
        this.mLongDescription = mLongDescription;
    }

    protected Steps(Parcel in) {
        mId = in.readString();
        mShortDescription = in.readString();
        mLongDescription = in.readString();
        mVideoDescription = in.readString();
    }

    public static final Creator<Steps> CREATOR = new Creator<Steps>() {
        @Override
        public Steps createFromParcel(Parcel in) {
            return new Steps(in);
        }

        @Override
        public Steps[] newArray(int size) {
            return new Steps[size];
        }
    };

    public String getMId() {
        return mId;
    }

    public String getShortDescription(){
        return mShortDescription;
    }

    public String getDescription(){
        return mLongDescription;
    }

    public String getVideoDescription(){
        return mVideoDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mShortDescription);
        dest.writeString(mLongDescription);
        dest.writeString(mVideoDescription);
    }
}
