package com.samo.retrofitsamuel;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.samo.retrofitsamuel.adapter.GetListAdapter;
import com.samo.retrofitsamuel.adapter.PostAdapter;
import com.samo.retrofitsamuel.model.PostModel;
import com.samo.retrofitsamuel.viewmodel.PostListViewModel;

import java.util.List;

public class PostsRecyclerActivity extends AppCompatActivity {
    //Declaring classes and widgets.
    private PostModel postModel;
    private PostAdapter adapter;
    private PostListViewModel viewModel;

    public RecyclerView recyclerView;
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_recycler);
        //        Initializing variables.
        recyclerView = findViewById(R.id.recview);
        textView = findViewById(R.id.noDataView);
        //        Set the LinearLayoutManager to the recyclerview. I am using the GridLayoutManager in this case.
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(linearLayoutManager);
//        Instantiating the Adapter class.
        adapter = new PostAdapter(this, postModel);
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(PostListViewModel.class);
//        listening to the live data.
            //            Onsuccess, display the data.
//            @Override
//            public void onChanged(PostModel postModel){
//                if (postModel != null) {
//                    postModel = postModel;
//                    adapter.setPostResponse(postModel);
////                    Set visibility for the textView for no data to gone when data is fetched successfully.
//                    textView.setVisibility(View.GONE);
//
//                } else {
////                    On failure, display - SORRY, FAILED TO FETCH DATA.
//                    textView.setVisibility(View.VISIBLE);
//                }
//            }
//        });
        viewModel.createPost();
    }
}