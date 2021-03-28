package com.samo.retrofitsamuel.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.samo.retrofitsamuel.R;
import com.samo.retrofitsamuel.model.PostModel;

import java.util.ArrayList;
import java.util.List;

//Implementing the Adapter.
public class UpdateAdapter extends RecyclerView.Adapter<UpdateAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<PostModel> postList;

    public UpdateAdapter(Context context, ArrayList<PostModel> postList) {
        this.context = context;
        this.postList = postList;
    }

    //    Settind data explicitly when refreshing the recyclerview.
    public void setPostList(ArrayList<PostModel> postList) {
        this.postList = postList;
        notifyDataSetChanged();
    }

    // Inflating the recyclerview.    .
    @Nullable
    @Override
    public UpdateAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.edit_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpdateAdapter.MyViewHolder holder, int position) {
//Setting values from the list.
        holder.tvId.setText(String.valueOf(this.postList.get(position).getId()));
        holder.tvUserId.setText(String.valueOf(this.postList.get(position).getUserId()));
        holder.tvTitle.setText(this.postList.get(position).getTitle().toString());
        holder.tvBody.setText(this.postList.get(position).getText().toString());
    }

    @Override
    public int getItemCount() {

//        Returning items being displayed in the recyclerview.
        if (this.postList != null) {
            return this.postList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //        Calling views.
        TextView tvId, tvUserId, tvTitle, tvBody;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvId = (TextView) itemView.findViewById(R.id.idView);
            tvUserId = (TextView) itemView.findViewById(R.id.userIdView);
            tvTitle = (TextView) itemView.findViewById(R.id.titleView);
            tvBody = (TextView) itemView.findViewById(R.id.bodyView);
        }
    }
}