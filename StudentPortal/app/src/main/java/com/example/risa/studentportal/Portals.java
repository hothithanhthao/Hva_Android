package com.example.risa.studentportal;

import android.os.Parcel;
import android.os.Parcelable;

public class Portals implements Parcelable{
    private String mPortalsText;
    private String URLText;

    public String getmPortalsText() {
        return mPortalsText;
    }
    public String getURLText() {
        return URLText;
    }

    public void setmPortalsText(String mPortalsText) {
        this.mPortalsText = mPortalsText;
    }
    public void setURLText(String URLText) {
        this.URLText = URLText;
    }

    public Portals(String mPortalsText, String URLText) {

        this.mPortalsText = mPortalsText;
        this.URLText = URLText;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mPortalsText);
        dest.writeString(this.URLText);
    }

    protected Portals(Parcel in) {
        this.mPortalsText = in.readString();
        this.URLText = in.readString();
    }

    public static final Parcelable.Creator<Portals> CREATOR = new Parcelable.Creator<Portals>() {
        @Override
        public Portals createFromParcel(Parcel source) {
            return new Portals(source);
        }

        @Override
        public Portals[] newArray(int size) {
            return new Portals[size];
        }
    };
}
