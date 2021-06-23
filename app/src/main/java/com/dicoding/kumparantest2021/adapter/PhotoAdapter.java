package com.dicoding.kumparantest2021.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.kumparantest2021.R;
import com.dicoding.kumparantest2021.model.PhotoModel;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.photoViewHolder>{

    private Context mCtx;
    private List<PhotoModel> mList;
    RequestOptions option;

    public PhotoAdapter(Context mCtx, List<PhotoModel> list) {
        this.mCtx = mCtx;
        this.mList = list;

    }

    @NonNull
    @Override
    public PhotoAdapter.photoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_row_photo, parent, false);
        return new photoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoAdapter.photoViewHolder holder, int position) {
        final PhotoModel aModel = mList.get(position);
        holder.bind(aModel);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void clearData() {
        int size = this.mList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.mList.remove(0);
            }
        }
    }

    public class photoViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;
        TextView tvTitle;
        LinearLayout divDetail;

        public photoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.ivPhoto);
            divDetail = itemView.findViewById(R.id.item_photo);
        }

        private void bind(final PhotoModel aModel){
//            tvTitle.setText(aModel.getPHOTO_TITLE());
//            Picasso.with(mCtx).load(aModel.getPHOTO_URL()).into(ivPhoto);
            Glide.with(mCtx)
                    .load(aModel.getPHOTO_URL())
                    .into(ivPhoto);

            divDetail.setOnClickListener(new View.OnClickListener() {
                private void doNothing() {

                }

                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
