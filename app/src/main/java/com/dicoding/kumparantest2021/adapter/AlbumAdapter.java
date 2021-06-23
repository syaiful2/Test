package com.dicoding.kumparantest2021.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.kumparantest2021.R;
import com.dicoding.kumparantest2021.helper.AppHelper;
import com.dicoding.kumparantest2021.model.AlbumModel;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.albumViewHolder>{
    private Context mCtx;
    private List<AlbumModel> mList;

    public AlbumAdapter(Context mCtx, List<AlbumModel> list) {
        this.mCtx = mCtx;
        this.mList = list;
    }

    @NonNull
    @Override
    public AlbumAdapter.albumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_row_album, parent, false);
        return new albumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.albumViewHolder holder, int position) {
        final AlbumModel aModel = mList.get(position);
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

    public class albumViewHolder extends RecyclerView.ViewHolder {
        TextView tvAlbumTitle;
        LinearLayout divDetail;

        public albumViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAlbumTitle = itemView.findViewById(R.id.tvAlbumTitle);
            divDetail = itemView.findViewById(R.id.item_album);
        }

        private void bind(final AlbumModel aModel){
            tvAlbumTitle.setText(aModel.getALBUM_TITLE());

            divDetail.setOnClickListener(new View.OnClickListener() {
                private void doNothing() {

                }

                @Override
                public void onClick(View view) {
                    AppHelper.goToAlbumDetail(mCtx, aModel);
                }
            });
        }

    }
}

