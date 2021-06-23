package com.dicoding.kumparantest2021.helper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dicoding.kumparantest2021.activity.PhotoActivity;
import com.dicoding.kumparantest2021.activity.PostDetailActivity;
import com.dicoding.kumparantest2021.activity.UserDetailActivity;
import com.dicoding.kumparantest2021.model.AlbumModel;
import com.dicoding.kumparantest2021.model.CommentModel;
import com.dicoding.kumparantest2021.model.PhotoModel;
import com.dicoding.kumparantest2021.model.PostModel;

import org.json.JSONObject;

public final class AppHelper {
    public static void goToPostDetail(Context context, PostModel rowData) {
        Bundle bundle = new Bundle();

        bundle.putString("id", String.valueOf(rowData.getPOST_ID()));
        bundle.putString("title", rowData.getPOST_TITLE().toUpperCase());
        bundle.putString("body", rowData.getPOST_BODY().toUpperCase());
        bundle.putString("id", String.valueOf(rowData.getUSER_ID()));
        bundle.putString("name", rowData.getUSER_NAME().toUpperCase());
        bundle.putString("companyName", rowData.getUSER_COMPANY_NAME().toUpperCase());

        Intent i = new Intent(context, PostDetailActivity.class);
        i.putExtra("extra_post", rowData);
        context.startActivity(i);
    }

    public static void goToUserDetail(Context context, PostModel rowData) {
        Bundle bundle = new Bundle();

        bundle.putString("id", String.valueOf(rowData.getPOST_ID()));
        bundle.putString("id", String.valueOf(rowData.getUSER_ID()));
        bundle.putString("email", rowData.getUSER_EMAIL().toUpperCase());
        bundle.putString("address", rowData.getUSER_ADDRESS());
        bundle.putString("name", rowData.getUSER_NAME().toUpperCase());
        bundle.putString("companyName", rowData.getUSER_COMPANY_NAME().toUpperCase());

        Intent i = new Intent(context, UserDetailActivity.class);
        i.putExtra("extra_user", rowData);
        context.startActivity(i);
    }

    public static CommentModel mapCommentModel(JSONObject rowData) {
        CommentModel item = new CommentModel();
        item.setCOMMENT_ID(rowData.optInt("id"));
        item.setCOMMENT_NAME(rowData.optString("name"));
        item.setCOMMENT_BODY(rowData.optString("body"));
        item.setPOST_ID(rowData.optInt("postId"));

        return item;
    }

    public static AlbumModel mapAlbumModel(JSONObject rowData) {
        AlbumModel item = new AlbumModel();
        item.setALBUM_ID(rowData.optInt("id"));
        item.setALBUM_TITLE(rowData.optString("title"));

        return item;
    }

    public static void goToAlbumDetail(Context context, AlbumModel rowData) {
        Bundle bundle = new Bundle();

        bundle.putString("id", String.valueOf(rowData.getALBUM_ID()));
        bundle.putString("email", rowData.getALBUM_TITLE().toUpperCase());

        Intent i = new Intent(context, PhotoActivity.class);
        i.putExtra("extra_album", rowData);
        context.startActivity(i);
    }


    public static PhotoModel mapPhotoModel(JSONObject rowData) {
        PhotoModel item = new PhotoModel();
        item.setPHOTO_ID(rowData.optInt("id"));
        item.setALBUM_ID(rowData.optInt("albumId"));
        item.setPHOTO_TITLE(rowData.optString("title"));
        item.setPHOTO_URL(rowData.optString("thumbnailUrl"));

        return item;
    }
}
