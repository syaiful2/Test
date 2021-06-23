package com.dicoding.kumparantest2021.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AlbumModel implements Parcelable {
    private int ALBUM_ID;
    private String ALBUM_TITLE;
    private int PHOTO_ID;

    public AlbumModel(Parcel in) {
        ALBUM_ID = in.readInt();
        ALBUM_TITLE = in.readString();
        PHOTO_ID = in.readInt();
    }

    public static final Creator<AlbumModel> CREATOR = new Creator<AlbumModel>() {
        @Override
        public AlbumModel createFromParcel(Parcel in) {
            return new AlbumModel(in);
        }

        @Override
        public AlbumModel[] newArray(int size) {
            return new AlbumModel[size];
        }
    };

    public AlbumModel() {

    }

    public int getALBUM_ID() {
        return ALBUM_ID;
    }

    public void setALBUM_ID(int ALBUM_ID) {
        this.ALBUM_ID = ALBUM_ID;
    }

    public String getALBUM_TITLE() {
        return ALBUM_TITLE;
    }

    public void setALBUM_TITLE(String ALBUM_TITLE) {
        this.ALBUM_TITLE = ALBUM_TITLE;
    }



    public int getPHOTO_ID() {
        return PHOTO_ID;
    }

    public void setPHOTO_ID(int PHOTO_ID) {
        this.PHOTO_ID = PHOTO_ID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ALBUM_ID);
        dest.writeString(ALBUM_TITLE);
        dest.writeInt(PHOTO_ID);
    }
}
