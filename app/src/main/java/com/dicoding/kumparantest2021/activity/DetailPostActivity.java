package com.dicoding.kumparantest2021.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.dicoding.kumparantest2021.Http;
import com.dicoding.kumparantest2021.R;
import com.dicoding.kumparantest2021.adapter.CommentAdapter;
import com.dicoding.kumparantest2021.adapter.PostAdapter;
import com.dicoding.kumparantest2021.helper.AppHelper;
import com.dicoding.kumparantest2021.helper.Config;
import com.dicoding.kumparantest2021.model.CommentModel;
import com.dicoding.kumparantest2021.model.PostModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailPostActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private ImageView ivBack;
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView rvListComment;

    private ArrayList<CommentModel> mList = new ArrayList<>();
    private CommentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);
        binding();
        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.post(new Runnable() {
            private void doNothing(){

            }
            @Override
            public void run() {
                getCommentList();
            }
        });
        rvListComment.setHasFixedSize(true);
        rvListComment.setLayoutManager(new LinearLayoutManager(this));

    }

    public void show(){
        mAdapter = new CommentAdapter(DetailPostActivity.this, mList);
        rvListComment.setAdapter(mAdapter);
    }

    public void getCommentList(){
        swipeRefresh.setRefreshing(true);
        AndroidNetworking.get("https://jsonplaceholder.typicode.com/comments")
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
                                CommentModel item = AppHelper.mapCommentModel(jsonObject);
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
                        Toast.makeText(DetailPostActivity.this, Config.TOAST_AN_ERROR, Toast.LENGTH_SHORT).show();
                        Log.d("ZEE", "onError: " + anError.getErrorBody());
                        Log.d("ZEE", "onError: " + anError.getLocalizedMessage());
                        Log.d("ZEE", "onError: " + anError.getErrorDetail());
                        Log.d("ZEE", "onError: " + anError.getResponse());
                        Log.d("ZEE", "onError: " + anError.getErrorCode());
                    }
                });



    }

    private void binding(){
        ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            private void doNothing() {

            }
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rvListComment = findViewById(R.id.rvListComment);
        swipeRefresh = findViewById(R.id.swipeRefresh);
    }

    @Override
    public void onRefresh() {
        getCommentList();
    }
}