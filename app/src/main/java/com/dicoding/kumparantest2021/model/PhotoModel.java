package com.dicoding.kumparantest2021.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PhotoModel implements Parcelable {
    private int PHOTO_ID;
    private String PHOTO_TITLE;
    private String PHOTO_URL;
    private int ALBUM_ID;

    public PhotoModel(Parcel in) {
        PHOTO_ID = in.readInt();
        PHOTO_TITLE = in.readString();
        PHOTO_URL = in.readString();
        ALBUM_ID = in.readInt();
    }

    public static final Creator<PhotoModel> CREATOR = new Creator<PhotoModel>() {
        @Override
        public PhotoModel createFromParcel(Parcel in) {
            return new PhotoModel(in);
        }

        @Override
        public PhotoModel[] newArray(int size) {
            return new PhotoModel[size];
        }
    };

    public PhotoModel() {

    }

    public int getPHOTO_ID() {
        return PHOTO_ID;
    }

    public void setPHOTO_ID(int PHOTO_ID) {
        this.PHOTO_ID = PHOTO_ID;
    }

    public String getPHOTO_TITLE() {
        return PHOTO_TITLE;
    }

    public void setPHOTO_TITLE(String PHOTO_TITLE) {
        this.PHOTO_TITLE = PHOTO_TITLE;
    }

    public String getPHOTO_URL() {
        return PHOTO_URL;
    }

    public void setPHOTO_URL(String PHOTO_URL) {
        this.PHOTO_URL = PHOTO_URL;
    }

    public int getALBUM_ID() {
        return ALBUM_ID;
    }

    public void setALBUM_ID(int ALBUM_ID) {
        this.ALBUM_ID = ALBUM_ID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(PHOTO_ID);
        dest.writeString(PHOTO_TITLE);
        dest.writeString(PHOTO_URL);
        dest.writeInt(ALBUM_ID);
    }
}
