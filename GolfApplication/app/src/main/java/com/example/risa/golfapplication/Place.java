package com.example.risa.golfapplication;


import android.os.Parcel;
import android.os.Parcelable;


public class Place implements Parcelable{
    private int mImageResource;
    private String mTitle;
    private String mURL;

    public Place(int imageResource, String mTitle, String mURL) {
        mImageResource = imageResource;
        this.mTitle = mTitle;
        this.mURL = mURL;
    }

    protected Place(Parcel in) {
        mImageResource = in.readInt();
        mTitle = in.readString();
        mURL = in.readString();
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    public int getImageResource() {
        return mImageResource;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getText2() {
        return mURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mImageResource);
        dest.writeString(mTitle);
        dest.writeString(mURL);
    }


}

