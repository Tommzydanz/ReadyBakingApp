package com.example.readybakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;



public class Ingredients implements Parcelable {


    private String mIngredient;

    private String mQuantity;

    private String mMeasure;

    public Ingredients(String mIngredient, String mQuantity, String mMeasure){
        this.mQuantity = mQuantity;
        this.mIngredient = mIngredient;
        this.mMeasure = mMeasure;
    }

    public String getIngredient() {
        return mIngredient;
    }

    public String getQuantity() {
        return mQuantity;
    }

    public String getMeasure() {
        return mMeasure;
    }

    protected Ingredients(Parcel in) {
        mIngredient = in.readString();
        mQuantity = in.readString();
        mMeasure = in.readString();
    }

    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mIngredient);
        dest.writeString(mQuantity);
        dest.writeString(mMeasure);
    }
}
