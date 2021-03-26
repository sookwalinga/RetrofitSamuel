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
import com.samo.retrofitsamuel.model.PostModel;

import java.util.List;

//Implementing the Adapter.
public class GetListAdapter extends RecyclerView.Adapter<GetListAdapter.MyViewHolder> {

    private Context context;
    private List<PostModel> postList;

    public GetListAdapter(Context context, List<PostModel> postList) {
        this.context = context;
        this.postList = postList;
    }

    //    Settind data explicitly when refreshing the recyclerview.
    public void setPostList(List<PostModel> postList) {
        this.postList = postList;
        notifyDataSetChanged();
    }

    // Inflating the recyclerview.    .
    @Nullable
    @Override
    public GetListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GetListAdapter.MyViewHolder holder, int position) {
//Setting values from the list.
        holder.tvId.setText("Id: " + String.valueOf(this.postList.get(position).getId()));
        holder.tvUserId.setText("UserId: " +  String.valueOf(this.postList.get(position).getUserId()));
        holder.tvTitle.setText("Title: " + this.postList.get(position).getTitle().toString());
        holder.tvBody.setText("Body: " + this.postList.get(position).getText().toString());
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
