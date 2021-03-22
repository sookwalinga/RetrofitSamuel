package com.samo.retrofitsamuel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.samo.retrofitsamuel.adapter.PostListAdapter;
import com.samo.retrofitsamuel.viewmodel.PostListViewModel;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Initializing the recyclerview.
        recyclerView = findViewById(R.id.recview);

//        Set the LinearLayoutManager to the recyclerview. I am using the GridLayoutManager in this case.
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(linearLayoutManager);
//        Instantiating the Adapter class.
        PostListAdapter postListAdapter = new PostListAdapter();
        recyclerView.setAdapter(postListAdapter);
    }
}