package com.dicoding.kumparantest2021.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.dicoding.kumparantest2021.Http;
import com.dicoding.kumparantest2021.R;
import com.dicoding.kumparantest2021.adapter.CommentAdapter;
import com.dicoding.kumparantest2021.helper.AppHelper;
import com.dicoding.kumparantest2021.helper.Config;
import com.dicoding.kumparantest2021.model.CommentModel;
import com.dicoding.kumparantest2021.model.PostModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PostDetailActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private ImageView ivBack;
    private TextView tvUserName, tvUserCompany, tvPostTitle, tvPostBody;
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView rvListComment;

    private ArrayList<CommentModel> mList = new ArrayList<>();
    private CommentAdapter mAdapter;

    private int U_ID;
    private PostModel model;
    private int P_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        binding();

        model = getIntent().getExtras().getParcelable("extra_post");
        if (/*bundle*/ model != null){
            P_ID = model.getPOST_ID();

            tvPostTitle.setText(model.getPOST_TITLE());
            tvPostBody.setText(model.getPOST_BODY());
            tvUserName.setText(model.getUSER_NAME());
            tvUserCompany.setText(model.getUSER_COMPANY_NAME());
        }
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
        mAdapter = new CommentAdapter(PostDetailActivity.this, mList);
        rvListComment.setAdapter(mAdapter);
    }

    public void getCommentList(){
        swipeRefresh.setRefreshing(true);
        AndroidNetworking.get(Config.BASE_URL + "comments?postId=" + P_ID)
                .setPriority(Priority.LOW)
                .setOkHttpClient(((Http) getApplication()).getOkHttpClient())
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        swipeRefresh.setRefreshing(false);
                        if (mAdapter != null) {
                            mAdapter.clearData();
                            mAdapter.notifyDataSetChanged();
                        }
                        if (mList != null)  mList.clear();

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
                        Toast.makeText(PostDetailActivity.this, Config.TOAST_AN_ERROR, Toast.LENGTH_SHORT).show();
                        Log.d("ZEE", "onError: " + anError.getErrorBody());
                        Log.d("ZEE", "onError: " + anError.getLocalizedMessage());
                        Log.d("ZEE", "onError: " + anError.getErrorDetail());
                        Log.d("ZEE", "onError: " + anError.getResponse());
                        Log.d("ZEE", "onError: " + anError.getErrorCode());
                    }
                });



    }

    private void binding(){
        tvPostTitle = findViewById(R.id.tvPostTitle);
        tvPostBody = findViewById(R.id.tvPostBody);
        tvUserCompany = findViewById(R.id.tvUserCompany);
        tvUserName = findViewById(R.id.tvUserName);
        tvUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppHelper.goToUserDetail(PostDetailActivity.this, model);
            }
        });
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