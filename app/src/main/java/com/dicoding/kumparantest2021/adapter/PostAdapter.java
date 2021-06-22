package com.dicoding.kumparantest2021.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.kumparantest2021.R;
import com.dicoding.kumparantest2021.model.PostModel;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ItemViewHolder>{

    private Context mCtx;
    private List<PostModel> mList;

    public PostAdapter(Context mCtx, List<PostModel> list) {
        this.mCtx = mCtx;
        this.mList = list;
    }

    @NonNull
    @Override
    public PostAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_row_post, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ItemViewHolder holder, int position) {
        final PostModel aModel = mList.get(position);
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
        TextView tvUserName, tvUserCompany, tvPostTitle, tvPostBody;
        LinearLayout divDetail;
        CardView getPost;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.txUserName);
            tvUserCompany = itemView.findViewById(R.id.txUserCompany);
            tvPostTitle = itemView.findViewById(R.id.txPostTitle);
            tvPostBody = itemView.findViewById(R.id.txPostBody);
            divDetail = itemView.findViewById(R.id.item_post);
        }

        private void bind(final PostModel aModel){
            tvPostTitle.setText(aModel.getPOST_TITLE());
            tvPostBody.setText(aModel.getPOST_BODY());
            tvUserName.setText(aModel.getUSER_NAME());
            tvUserCompany.setText(aModel.getUSER_COMPANY_NAME());

            divDetail.setOnClickListener(new View.OnClickListener() {
                private void doNothing() {

                }

                @Override
                public void onClick(View view) {
//                    AppHelper.goToOrderDetail(mCtx, aModel);
                }
            });
        }
    }
}
