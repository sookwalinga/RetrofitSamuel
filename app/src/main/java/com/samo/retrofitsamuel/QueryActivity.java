package com.samo.retrofitsamuel;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.samo.retrofitsamuel.adapter.CommentAdapter;
import com.samo.retrofitsamuel.model.CommentModel;
import com.samo.retrofitsamuel.viewmodel.CommentViewModel;

import java.util.List;

public class QueryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Declaring classes and widgets.
    private List<CommentModel> commentModel;
    private CommentAdapter commentAdapter;
    private CommentViewModel commentViewModel;

    Toolbar queryBtn;
    TextInputLayout tilQueryId;

    public RecyclerView recyclerView;
    public TextView textView;

    private DrawerLayout drawer_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queried);

        //        Finding reference to the toolbar.
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Comments By Query");

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
        queryBtn = findViewById(R.id.queryPostId);
        tilQueryId = findViewById(R.id.idQuery);

        //        Set the LinearLayoutManager to the recyclerview. I am using the GridLayoutManager in this case.
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(linearLayoutManager);
        //        Instantiating the Adapter class.
        commentAdapter = new CommentAdapter(this, commentModel);
        recyclerView.setAdapter(commentAdapter);

        queryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strIdEntered = tilQueryId.getEditText().getText().toString();
                if (strIdEntered.length() != 0) {
                    Integer idEntered = Integer.valueOf(strIdEntered);
                    GetComments(idEntered);

                    //Clearing input after querry.
                    tilQueryId.getEditText().setText("");
                } else {
                    Toast.makeText(QueryActivity.this, "Please enter Id (1 - 100).", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void GetComments(int idEntered) {

        commentViewModel = ViewModelProviders.of(this).get(CommentViewModel.class);

//        listening to the live data.
        commentViewModel.getPostListObserver().observe(this, new Observer<List<CommentModel>>() {
            //            Onsuccess, display the data.
            @Override
            public void onChanged(List<CommentModel> commentModels) {
                if (commentModels != null) {
                    commentModel = commentModels;
                    commentAdapter.setCommentList(commentModels);
//                    Set visibility for the textView for no data to gone when data is fetched successfully.
                    textView.setVisibility(View.GONE);

                } else {
//                    On failure, display - SORRY, FAILED TO FETCH DATA.
                    textView.setVisibility(View.VISIBLE);
                }
            }
        });
        commentViewModel.makeApiCall(idEntered);
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
                super.onBackPressed();
                break;
            case R.id.activity_learn_more:
                Intent intent = new Intent(QueryActivity.this, LearnMoreActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(QueryActivity.this, "Sorry, nothing Selected.", Toast.LENGTH_SHORT).show();
        }
        drawer_layout.closeDrawer(GravityCompat.START);
        return false;
    }
}