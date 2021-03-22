package com.samo.retrofitsamuel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.samo.retrofitsamuel.R;
import com.samo.retrofitsamuel.model.PostModel;

import java.security.PublicKey;
import java.util.List;

//Implementing the Adapter.
public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.MyViewHolder> {

    private Context context;
    private List<PostModel> postList;

    public PostListAdapter(Context context, List<PostModel> postList) {
        this.context = context;
        this.postList = postList;
    }

    //    Settind data explicitly when refreshing the recyclerview.
    public void setPostList(List<PostModel> postList) {
        this.postList = postList;
        notifyDataSetChanged();
    }

    //    Implementing functions in our class.
    @Nullable
    @Override
    public PostListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Inflating  the recycler.
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
//        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PostListAdapter.MyViewHolder holder, int position) {
//Setting values from the list.
//        holder.tvId.setText(String.valueOf(this.postList.get(position).getId()));
//        holder.tvUserId.setText(String.valueOf(this.postList.get(position).getUserId()));
        holder.tvTitle.setText(this.postList.get(position).getTitle().toString());
//        holder.tvBody.setText(this.postList.get(position).getText().toString());
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
