package com.samo.retrofitsamuel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.samo.retrofitsamuel.R;
import com.samo.retrofitsamuel.model.CommentModel;

import java.util.List;

//Implementing the Adapter.
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {

    private Context context;
    private List<CommentModel> commentList;

    public CommentAdapter(Context context, List<CommentModel> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    //    Settind data explicitly when refreshing the recyclerview.
    public void setCommentList(List<CommentModel> commentList) {
        this.commentList = commentList;
        notifyDataSetChanged();
    }

    // Inflating the recyclerview.    .
    @Nullable
    @Override
    public CommentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.query_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.MyViewHolder holder, int position) {
//Setting values from the list.
        holder.tvId.setText(String.valueOf(this.commentList.get(position).getId()));
        holder.tvPostId.setText(String.valueOf(this.commentList.get(position).getPostId()));
        holder.tvName.setText(this.commentList.get(position).getName().toString());
        holder.tvEmail.setText(this.commentList.get(position).getEmail().toString());
        holder.tvBody.setText(this.commentList.get(position).getText().toString());
    }

    @Override
    public int getItemCount() {

//        Returning items being displayed in the recyclerview.
        if (this.commentList != null) {
            return this.commentList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //        Calling views.
        TextView tvId, tvPostId, tvName, tvEmail, tvBody;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvId = (TextView) itemView.findViewById(R.id.idComments);
            tvPostId = (TextView) itemView.findViewById(R.id.postIdComments);
            tvName = (TextView) itemView.findViewById(R.id.nameComments);
            tvEmail = (TextView) itemView.findViewById(R.id.emailComments);
            tvBody = (TextView) itemView.findViewById(R.id.bodyComments);
        }
    }
}
