package com.example.readybakingapp.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Recipe implements Parcelable {
    private int mId;
    private String mName;
    private ArrayList<Steps> mStep;
    private ArrayList<Ingredients> mIngredients;
    private String mServing;

    public Recipe(int mId, String mName, String mServing, ArrayList<Steps> mStep, ArrayList<Ingredients> mIngredients){
        this.mId = mId;
        this.mName = mName;
        this.mStep = mStep;
        this.mIngredients = mIngredients;
        this.mServing = mServing;
    }


    protected Recipe(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mStep = in.createTypedArrayList(Steps.CREATOR);
        mIngredients = in.createTypedArrayList(Ingredients.CREATOR);
        mServing = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public int getMId(){
        return mId;
    }

    public String getName() {
        return mName;
    }

    public ArrayList<Steps> getRecipeSteps() {
        return mStep;
    }

    public ArrayList<Ingredients> getIngredients() {
        return mIngredients;
    }


    public String getServing() {
        return mServing;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeTypedList(mStep);
        dest.writeTypedList(mIngredients);
        dest.writeString(mServing);
    }
}