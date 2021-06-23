package com.dicoding.kumparantest2021.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.kumparantest2021.R;
import com.dicoding.kumparantest2021.model.CommentModel;
import com.dicoding.kumparantest2021.model.PostModel;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ItemViewHolder>{
    private Context mCtx;
    private List<CommentModel> mList;

    public CommentAdapter(Context mCtx, List<CommentModel> list) {
        this.mCtx = mCtx;
        this.mList = list;
    }

    @NonNull
    @Override
    public CommentAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_row_comment, parent, false);
        return new CommentAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ItemViewHolder holder, int position) {
        final CommentModel aModel = mList.get(position);
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

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvCommentName, tvCommentBody;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCommentName = itemView.findViewById(R.id.tvCommentName);
            tvCommentBody = itemView.findViewById(R.id.tvCommentBody);
        }

        private void bind(final CommentModel aModel){
            tvCommentName.setText(aModel.getCOMMENT_NAME());
            tvCommentBody.setText(aModel.getCOMMENT_BODY());

//            divDetail.setOnClickListener(new View.OnClickListener() {
//                private void doNothing() {
//
//                }
//
//                @Override
//                public void onClick(View view) {
//                    AppHelper.goToOrderDetail(mCtx, aModel);
//                }
//            });
        }
    }
}
