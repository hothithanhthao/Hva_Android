package com.example.risa.recipeapp;



import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;
import android.os.Parcel;
import android.os.Parcelable;

public class Recipe implements Parcelable{

    @SerializedName("title")
    @Expose
    private String name;
    @SerializedName("image_url")
    @Expose
    private String image;

    public Recipe( String name, String image) {
        this.name = name;
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.image);
    }


    public Recipe(Parcel in) {
        this.name = in.readString();
        this.image = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
