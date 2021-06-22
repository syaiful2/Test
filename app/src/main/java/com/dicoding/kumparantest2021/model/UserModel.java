package com.dicoding.kumparantest2021.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable {
    private int USER_ID;
    private String USER_NAME;
    private String USER_EMAIL;
    private String USER_ADDRESS;
    private String USER_COMPANY;

    public UserModel(Parcel in) {
        USER_ID = in.readInt();
        USER_NAME = in.readString();
        USER_EMAIL = in.readString();
        USER_ADDRESS = in.readString();
        USER_COMPANY = in.readString();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    public UserModel() {

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

    public String getUSER_EMAIL() {
        return USER_EMAIL;
    }

    public void setUSER_EMAIL(String USER_EMAIL) {
        this.USER_EMAIL = USER_EMAIL;
    }

    public String getUSER_ADDRESS() {
        return USER_ADDRESS;
    }

    public void setUSER_ADDRESS(String USER_ADDRESS) {
        this.USER_ADDRESS = USER_ADDRESS;
    }

    public String getUSER_COMPANY() {
        return USER_COMPANY;
    }

    public void setUSER_COMPANY(String USER_COMPANY) {
        this.USER_COMPANY = USER_COMPANY;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(USER_ID);
        dest.writeString(USER_NAME);
        dest.writeString(USER_EMAIL);
        dest.writeString(USER_ADDRESS);
        dest.writeString(USER_COMPANY);
    }
}
