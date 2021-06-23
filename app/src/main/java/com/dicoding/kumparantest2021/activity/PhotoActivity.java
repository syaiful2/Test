package com.dicoding.kumparantest2021.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.dicoding.kumparantest2021.adapter.PhotoAdapter;
import com.dicoding.kumparantest2021.helper.AppHelper;
import com.dicoding.kumparantest2021.helper.Config;
import com.dicoding.kumparantest2021.model.AlbumModel;
import com.dicoding.kumparantest2021.model.PhotoModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PhotoActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView rvListPhoto;

    private ArrayList<PhotoModel> mList = new ArrayList<>();
    private PhotoAdapter mAdapter;

    private AlbumModel aModel;
    private int A_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        binding();

        aModel = getIntent().getExtras().getParcelable("extra_album");
        if (/*bundle*/ aModel != null) {
            A_ID = aModel.getALBUM_ID();
        }

        swipeRefresh.setOnRefreshListener(this);
        swipeRefresh.post(new Runnable() {
            private void doNothing(){

            }
            @Override
            public void run() {
                getPhotoList();

            }
        });
        rvListPhoto.setHasFixedSize(true);
        rvListPhoto.setLayoutManager(new GridLayoutManager(this, 3));

    }

    public void show(){
        mAdapter = new PhotoAdapter(PhotoActivity.this, mList);
        rvListPhoto.setAdapter(mAdapter);
    }


    public void getPhotoList(){
        swipeRefresh.setRefreshing(true);
        AndroidNetworking.get(Config.BASE_URL + "photos?albumId=" + A_ID)
                .setPriority(Priority.LOW)
                .setOkHttpClient(((Http) getApplication()).getOkHttpClient())
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("TAG", "coba: "+response);
                        swipeRefresh.setRefreshing(false);
                        if (mAdapter != null) {
                            mAdapter.clearData();
                            mAdapter.notifyDataSetChanged();
                        }
                        if (mList != null)  mList.clear();

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                PhotoModel item = AppHelper.mapPhotoModel(jsonObject);
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
                        Toast.makeText(PhotoActivity.this, Config.TOAST_AN_ERROR, Toast.LENGTH_SHORT).show();
                        Log.d("ZEE", "onError: " + anError.getErrorBody());
                        Log.d("ZEE", "onError: " + anError.getLocalizedMessage());
                        Log.d("ZEE", "onError: " + anError.getErrorDetail());
                        Log.d("ZEE", "onError: " + anError.getResponse());
                        Log.d("ZEE", "onError: " + anError.getErrorCode());
                    }
                });



    }

    private void binding(){
        rvListPhoto = findViewById(R.id.rvListPhoto);
        swipeRefresh = findViewById(R.id.swipeRefresh);
    }

    @Override
    public void onRefresh() {
        getPhotoList();
    }
}