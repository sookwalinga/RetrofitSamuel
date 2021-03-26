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
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    private Context context;
    private PostModel postResponse;

    public PostAdapter(Context context, PostModel postResponse) {
        this.context = context;
        this.postResponse = postResponse;
    }

    //    Settind data explicitly when refreshing the recyclerview.
    public void setPostResponse(PostModel postResponse) {
        this.postResponse = postResponse;
        notifyDataSetChanged();
    }

    // Inflating the recyclerview.    .
    @Nullable
    @Override
    public PostAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_row_post, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.MyViewHolder holder, int position) {
//Setting values from the list.
        holder.tvId.setText("Id: " + String.valueOf(this.postResponse.getId()));
        holder.tvUserId.setText("UserId: " +  String.valueOf(this.postResponse.getUserId()));
        holder.tvTitle.setText("Title: " + this.postResponse.getTitle().toString());
        holder.tvBody.setText("Body: " + this.postResponse.getText().toString());
    }

    @Override
    public int getItemCount() {

//        Returning items being displayed in the recyclerview.
        if (this.postResponse != null) {
            return this.postResponse.getId();
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
