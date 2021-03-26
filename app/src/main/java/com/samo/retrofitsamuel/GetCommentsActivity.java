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

import com.samo.retrofitsamuel.adapter.CommentListAdapter;
import com.samo.retrofitsamuel.adapter.CommentListAdapter;
import com.samo.retrofitsamuel.model.CommentModel;
import com.samo.retrofitsamuel.model.CommentModel;
import com.samo.retrofitsamuel.network.Retrofitinstance;
import com.samo.retrofitsamuel.viewmodel.QueryListViewModel;
import com.samo.retrofitsamuel.viewmodel.QueryListViewModel;

import java.util.List;

public class GetCommentsActivity extends AppCompatActivity {
    //Declaring classes and widgets.
    private List<CommentModel> commentModelList;
    private CommentListAdapter adapter;
    private QueryListViewModel viewModel;

    Retrofitinstance retrofitinstance = new Retrofitinstance();
    public RecyclerView recyclerView;
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_comments);
        //        Initializing variables.
        recyclerView = findViewById(R.id.recview);
        textView = findViewById(R.id.noDataView);
        //        Set the LinearLayoutManager to the recyclerview. I am using the GridLayoutManager in this case.
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(linearLayoutManager);
//        Instantiating the Adapter class.
        adapter = new CommentListAdapter(this, commentModelList);
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(QueryListViewModel.class);
//        listening to the live data.
        viewModel.getCommentListObserver().observe(this, new Observer<List<CommentModel>>() {
            //            Onsuccess, display the data.
            @Override
            public void onChanged(List<CommentModel> commentModels) {
                if (commentModels != null) {
                    commentModelList = commentModels;
                    adapter.setCommentList(commentModels);
//                    Set visibility for the textView for no data to gone when data is fetched successfully.
                    textView.setVisibility(View.GONE);

                } else {
//                    On failure, display - SORRY, FAILED TO FETCH DATA.
                    textView.setVisibility(View.VISIBLE);
                }
            }
        });
        viewModel.makeApiCall();
    }
}