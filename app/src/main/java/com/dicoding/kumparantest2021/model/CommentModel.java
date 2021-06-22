package com.dicoding.kumparantest2021.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CommentModel implements Parcelable {
    private int COMMENT_ID;
    private String COMMENT_NAME;
    private String COMMENT_BODY;
    private int POST_ID;

    public CommentModel(Parcel in) {
        COMMENT_ID = in.readInt();
        COMMENT_NAME = in.readString();
        COMMENT_BODY = in.readString();
        POST_ID = in.readInt();
    }

    public static final Creator<CommentModel> CREATOR = new Creator<CommentModel>() {
        @Override
        public CommentModel createFromParcel(Parcel in) {
            return new CommentModel(in);
        }

        @Override
        public CommentModel[] newArray(int size) {
            return new CommentModel[size];
        }
    };

    public CommentModel() {

    }

    public int getCOMMENT_ID() {
        return COMMENT_ID;
    }

    public void setCOMMENT_ID(int COMMENT_ID) {
        this.COMMENT_ID = COMMENT_ID;
    }

    public String getCOMMENT_NAME() {
        return COMMENT_NAME;
    }

    public void setCOMMENT_NAME(String COMMENT_NAME) {
        this.COMMENT_NAME = COMMENT_NAME;
    }

    public String getCOMMENT_BODY() {
        return COMMENT_BODY;
    }

    public void setCOMMENT_BODY(String COMMENT_BODY) {
        this.COMMENT_BODY = COMMENT_BODY;
    }

    public int getPOST_ID() {
        return POST_ID;
    }

    public void setPOST_ID(int POST_ID) {
        this.POST_ID = POST_ID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(COMMENT_ID);
        dest.writeString(COMMENT_NAME);
        dest.writeString(COMMENT_BODY);
        dest.writeInt(POST_ID);
    }
}
