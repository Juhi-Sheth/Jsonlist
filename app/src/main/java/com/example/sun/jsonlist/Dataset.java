package com.example.sun.jsonlist;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sun on 2/6/2018.
 */

public class Dataset implements Parcelable {
    String image;
    String name;
    String description;
    String price;
    String chef;

    public Dataset(String image,String name,String description,String price,String chef) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
        this.chef = chef;
    }

    public Dataset() {


    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getChef() {
        return chef;
    }

    public void setChef(String chef) {
        this.chef = chef;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.image);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.price);
        dest.writeString(this.chef);
    }

    protected Dataset(Parcel in) {
        this.image = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.price = in.readString();
        this.chef = in.readString();
    }

    public static final Parcelable.Creator<Dataset> CREATOR = new Parcelable.Creator<Dataset>() {
        @Override
        public Dataset createFromParcel(Parcel source) {
            return new Dataset(source);
        }

        @Override
        public Dataset[] newArray(int size) {
            return new Dataset[size];
        }
    };
}

