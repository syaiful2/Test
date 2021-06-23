package com.dicoding.kumparantest2021.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PostModel implements Parcelable {
    int POST_ID;
    String POST_TITLE;
    String POST_BODY;
    int USER_ID;
    String USER_NAME;
    String USER_COMPANY_NAME;
    String USER_ADDRESS;
    String USER_EMAIL;


    public PostModel() {

    }

    protected PostModel(Parcel in) {
        POST_ID = in.readInt();
        POST_TITLE = in.readString();
        POST_BODY = in.readString();
        USER_ID = in.readInt();
        USER_NAME = in.readString();
        USER_COMPANY_NAME = in.readString();
        USER_ADDRESS = in.readString();
        USER_EMAIL = in.readString();
    }

    public static final Creator<PostModel> CREATOR = new Creator<PostModel>() {
        @Override
        public PostModel createFromParcel(Parcel in) {
            return new PostModel(in);
        }

        @Override
        public PostModel[] newArray(int size) {
            return new PostModel[size];
        }
    };

    public int getPOST_ID() {
        return POST_ID;
    }

    public void setPOST_ID(int POST_ID) {
        this.POST_ID = POST_ID;
    }

    public String getPOST_TITLE() {
        return POST_TITLE;
    }

    public void setPOST_TITLE(String POST_TITLE) {
        this.POST_TITLE = POST_TITLE;
    }

    public String getPOST_BODY() {
        return POST_BODY;
    }

    public void setPOST_BODY(String POST_BODY) {
        this.POST_BODY = POST_BODY;
    }

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getUSER_COMPANY_NAME() {
        return USER_COMPANY_NAME;
    }

    public void setUSER_COMPANY_NAME(String USER_COMPANY_NAME) {
        this.USER_COMPANY_NAME = USER_COMPANY_NAME;
    }

    public String getUSER_ADDRESS() {
        return USER_ADDRESS;
    }

    public void setUSER_ADDRESS(String USER_ADDRESS) {
        this.USER_ADDRESS = USER_ADDRESS;
    }

    public String getUSER_EMAIL() {
        return USER_EMAIL;
    }

    public void setUSER_EMAIL(String USER_EMAIL) {
        this.USER_EMAIL = USER_EMAIL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(POST_ID);
        dest.writeString(POST_TITLE);
        dest.writeString(POST_BODY);
        dest.writeInt(USER_ID);
        dest.writeString(USER_NAME);
        dest.writeString(USER_COMPANY_NAME);
        dest.writeString(USER_ADDRESS);
        dest.writeString(USER_EMAIL);
    }
}
