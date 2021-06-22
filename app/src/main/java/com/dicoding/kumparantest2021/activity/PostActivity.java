package com.dicoding.kumparantest2021.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.dicoding.kumparantest2021.Http;
import com.dicoding.kumparantest2021.R;
import com.dicoding.kumparantest2021.adapter.PostAdapter;
import com.dicoding.kumparantest2021.helper.Config;
import com.dicoding.kumparantest2021.model.PostModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PostActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView rvListPost;

    private ArrayList<PostModel> mList = new ArrayList<>();
    private PostAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        binding();

        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.post(new Runnable() {
            private void doNothing(){

            }
            @Override
            public void run() {
                getPostList();

            }
        });
        rvListPost.setHasFixedSize(true);
        rvListPost.setLayoutManager(new LinearLayoutManager(this));

        AndroidNetworking.get("https://jsonplaceholder.typicode.com/users")
                .setPriority(Priority.LOW)
                .setOkHttpClient(((Http) getApplication()).getOkHttpClient())
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        swipeRefresh.setRefreshing(false);
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                item.setUSER_NAME(jsonObject.optString("username"));
                                JSONObject company = jsonObject.getJSONObject("company");
                                item.setUSER_COMPANY_NAME(company.optString("name"));
                                mList.add(item);
                                show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        swipeRefresh.setRefreshing(false);
                        Toast.makeText(PostActivity.this, Config.TOAST_AN_ERROR, Toast.LENGTH_SHORT).show();
                        Log.d("ZEE", "onError: " + anError.getErrorBody());
                        Log.d("ZEE", "onError: " + anError.getLocalizedMessage());
                        Log.d("ZEE", "onError: " + anError.getErrorDetail());
                        Log.d("ZEE", "onError: " + anError.getResponse());
                        Log.d("ZEE", "onError: " + anError.getErrorCode());
                    }
                });

    }

    public void show(){
        mAdapter = new PostAdapter(PostActivity.this, mList);
        rvListPost.setAdapter(mAdapter);
    }

    final PostModel item = new PostModel();
    public void getPostList(){
        swipeRefresh.setRefreshing(true);
        AndroidNetworking.get(Config.BASE_URL + "posts")
                .setPriority(Priority.LOW)
                .setOkHttpClient(((Http) getApplication()).getOkHttpClient())
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        swipeRefresh.setRefreshing(false);
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                item.setPOST_ID(jsonObject.optInt("id"));
                                item.setPOST_TITLE(jsonObject.optString("title"));
                                item.setPOST_BODY(jsonObject.optString("body"));
//                                item.setUSER_ID(jsonObject.optInt("userId"));
                                mList.add(item);
                                show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        swipeRefresh.setRefreshing(false);
                        Toast.makeText(PostActivity.this, Config.TOAST_AN_ERROR, Toast.LENGTH_SHORT).show();
                        Log.d("ZEE", "onError: " + anError.getErrorBody());
                        Log.d("ZEE", "onError: " + anError.getLocalizedMessage());
                        Log.d("ZEE", "onError: " + anError.getErrorDetail());
                        Log.d("ZEE", "onError: " + anError.getResponse());
                        Log.d("ZEE", "onError: " + anError.getErrorCode());
                    }
                });



    }


    @Override
    public void onRefresh() {
        getPostList();

    }

    private void binding(){
        rvListPost = findViewById(R.id.rvListPost);
        swipeRefresh = findViewById(R.id.swipeRefresh);
    }
}