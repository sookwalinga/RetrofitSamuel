package com.samo.retrofitsamuel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.samo.retrofitsamuel.adapter.GetListAdapter;
import com.samo.retrofitsamuel.model.PostModel;
import com.samo.retrofitsamuel.viewmodel.PostListViewModel;

import java.util.List;

public class GetPostsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Declaring classes and widgets.
    private List<PostModel> postModelList;
    private GetListAdapter adapter;
    private PostListViewModel viewModel;

    public RecyclerView recyclerView;
    public TextView textView;

    private DrawerLayout drawer_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_posts);

        //        Finding reference to the toolbar.
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Retrofit");

        drawer_layout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();

        //        Initializing variables.
        recyclerView = findViewById(R.id.recview);
        textView = findViewById(R.id.noDataView);
        //        Set the LinearLayoutManager to the recyclerview. I am using the GridLayoutManager in this case.
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(linearLayoutManager);
//        Instantiating the Adapter class.
        adapter = new GetListAdapter(this, postModelList);
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(PostListViewModel.class);
//        listening to the live data.
        viewModel.getPostListObserver().observe(this, new Observer<List<PostModel>>() {
            //            Onsuccess, display the data.
            @Override
            public void onChanged(List<PostModel> postModels) {
                if (postModels != null) {
                    postModelList = postModels;
                    adapter.setPostList(postModels);
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

    @Override
    public void onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fragment_main:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MainFragment()).addToBackStack(MainFragment.class.getSimpleName()).commit();
                break;
            case R.id.activity_learn_more:
                Intent intent = new Intent(GetPostsActivity.this, LearnMoreActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(GetPostsActivity.this, "Sorry, nothing Selected.", Toast.LENGTH_SHORT).show();
        }
        drawer_layout.closeDrawer(GravityCompat.START);
        return false;
    }
}