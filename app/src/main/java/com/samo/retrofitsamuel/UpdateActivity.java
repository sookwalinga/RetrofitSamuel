package com.samo.retrofitsamuel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.samo.retrofitsamuel.adapter.CommentAdapter;
import com.samo.retrofitsamuel.adapter.GetListAdapter;
import com.samo.retrofitsamuel.adapter.UpdateAdapter;
import com.samo.retrofitsamuel.model.CommentModel;
import com.samo.retrofitsamuel.model.PostModel;
import com.samo.retrofitsamuel.network.APIClient;
import com.samo.retrofitsamuel.network.Retrofitinstance;
import com.samo.retrofitsamuel.viewmodel.CommentViewModel;
import com.samo.retrofitsamuel.viewmodel.UpdateViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdateActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Declaring classes and widgets.
    private List<PostModel> postModel;
    private GetListAdapter getListAdapter;
    private UpdateViewModel updateViewModel;

    Toolbar toolbarBtn;
    TextInputLayout tilPostId;

    public RecyclerView recyclerView;
    public TextView textView;

    private DrawerLayout drawer_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        //        Finding reference to the toolbar.
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Update/Delete");

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
        toolbarBtn = findViewById(R.id.queryPostId);
        tilPostId = findViewById(R.id.idPost);

        //        Set the LinearLayoutManager to the recyclerview. I am using the GridLayoutManager in this case.
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(linearLayoutManager);
        //        Instantiating the Adapter class.
        getListAdapter = new GetListAdapter(this, postModel);
        recyclerView.setAdapter(getListAdapter);
        GetPost();

        toolbarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer idEntered = Integer.valueOf(tilPostId.getEditText().getText().toString());
//                if (idEntered != null){
//                }else{
//                    Toast.makeText(UpdateActivity.this, "Please enter Id.", Toast.LENGTH_SHORT).show();
//                }
            }
        });

    }

    private void GetPost(){

        updateViewModel = ViewModelProviders.of(this).get(UpdateViewModel.class);

//        listening to the live data.
        updateViewModel.getPostListObserver().observe(this, new Observer<List<PostModel>>() {
            //            Onsuccess, display the data.
            @Override
            public void onChanged(List<PostModel> postModels) {
                if (postModels != null) {
                    postModel = postModels;
                    getListAdapter.setPostList(postModels);
//                    Set visibility for the textView for no data to gone when data is fetched successfully.
                    textView.setVisibility(View.GONE);

                } else {
//                    On failure, display - SORRY, FAILED TO FETCH DATA.
                    textView.setVisibility(View.VISIBLE);
                }
            }
        });
        updateViewModel.makeApiCall();
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
                Intent intent = new Intent(UpdateActivity.this, LearnMoreActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(UpdateActivity.this, "Sorry, nothing Selected.", Toast.LENGTH_SHORT).show();
        }
        drawer_layout.closeDrawer(GravityCompat.START);
        return false;
    }
}