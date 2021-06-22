package com.dicoding.kumparantest2021.helper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dicoding.kumparantest2021.model.CommentModel;
import com.dicoding.kumparantest2021.model.PostModel;

import org.json.JSONObject;

public final class AppHelper {
    public static PostModel mapPostModel(JSONObject rowData) {
        PostModel item = new PostModel();
        item.setPOST_ID(rowData.optInt("id"));
        item.setPOST_TITLE(rowData.optString("title"));
        item.setPOST_BODY(rowData.optString("body"));
        item.setUSER_ID(rowData.optInt("userId"));
        item.setUSER_NAME(rowData.optString("username"));
        item.setUSER_COMPANY_NAME(rowData.optString("name"));

        return item;
    }

    public static PostModel mapUserModel(JSONObject rowData) {
        PostModel item = new PostModel();
        item.setUSER_NAME(rowData.optString("name"));
//        item.setUSER_COMPANY_NAME(rowData.optString("name"));

        return item;
    }

    public static void goToPostDetail(Context context, PostModel rowData) {
//        Bundle bundle = new Bundle();
//
//        bundle.putString("id", String.valueOf(rowData.getPOST_ID()));
//        bundle.putString("CUST_NAME", rowData.getPOST_TITLE().toUpperCase());
//        bundle.putString("CUST_CP", rowData.getPOST_BODY().toUpperCase());
//        bundle.putString("CUST_PHONE", rowData.getCUST_PHONE());
//        bundle.putString("CUST_EMAIL", rowData.getCUST_EMAIL());
//        bundle.putString("CUST_ADDRESS", rowData.getCUST_ADDRESS().toUpperCase());
//        bundle.putString("CUST_CITY", rowData.getCUST_CITY().toUpperCase());
//        bundle.putString("CUST_NPWP", rowData.getCUST_NPWP().toUpperCase());
//
//        Intent i = new Intent(context, CustomerDetailActivity.class);
//        i.putExtra("extra_customer", rowData);
//        context.startActivity(i);
    }

    public static CommentModel mapCommentModel(JSONObject rowData) {
        CommentModel item = new CommentModel();
        item.setCOMMENT_ID(rowData.optInt("id"));
        item.setCOMMENT_NAME(rowData.optString("name"));
        item.setCOMMENT_BODY(rowData.optString("body"));
        item.setPOST_ID(rowData.optInt("postId"));

        return item;
    }
}
