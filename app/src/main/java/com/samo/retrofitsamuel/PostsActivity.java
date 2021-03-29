package com.samo.retrofitsamuel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.samo.retrofitsamuel.model.PostModel;
import com.samo.retrofitsamuel.network.APIClient;
import com.samo.retrofitsamuel.network.Retrofitinstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Declaring classes and widgets.
    private PostModel postModelList;

    private DrawerLayout drawer_layout;
    Button btnPost;
    TextInputLayout tilUserId, tilTitle, tilBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        tilUserId = findViewById(R.id.userIdPost);
        tilTitle = findViewById(R.id.titlePost);
        tilBody = findViewById(R.id.bodyPost);
        //        Finding reference to the toolbar.
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Post");

        drawer_layout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();

        //        Initializing variables.
        btnPost = findViewById(R.id.post_btn);

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUserId = tilUserId.getEditText().getText().toString();
                String titleEntered = tilTitle.getEditText().getText().toString();
                String bodyEntered = tilBody.getEditText().getText().toString();
                if (strUserId.length() != 0) {
                    Integer userIdEntered = Integer.valueOf(strUserId);
                    GetPostResult(userIdEntered, titleEntered, bodyEntered);
//                    Clearing inputs after post.
                    tilUserId.getEditText().setText("");
                    tilTitle.getEditText().setText("");
                    tilBody.getEditText().setText("");
                } else {
                    Toast.makeText(PostsActivity.this,
                            "Please fill in the data to be posted. The UserId is required.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void GetPostResult(int userIdEntered, String titleEntered, String bodyEntered) {
//Alert Dialog to show popup of results.
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(this.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.popup, null);
        dialogBuilder.setView(dialogView);

        //referencing my views
        final ImageView cancelButton = dialogView.findViewById(R.id.cancel);
        final TextView tvResult = dialogView.findViewById(R.id.textResult);
        //create and show the dialog
        final AlertDialog b = dialogBuilder.create();
        b.show();

        APIClient apiClient = Retrofitinstance.getRetroClient().create(APIClient.class);

        PostModel postModel = new PostModel(userIdEntered, titleEntered, bodyEntered);
        Call<PostModel> call = apiClient.createPost(postModel);

        //        Executing the request in the background (asynchronously) using the enqueue retrofit method.
        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
//                Instatiating the postList on success - on HTTP code 200 - 300.
                if (!response.isSuccessful()) {
                    tvResult.setText("Response Code: " + response.code());
                    return;
                }
                postModelList = response.body();

                String result = "";
                result += "Response Code: " + response.code() + "\n";
                result += "ID: " + postModelList.getId() + "\n";
                result += "User Id: " + postModelList.getUserId() + "\n";
                result += "Title: " + postModelList.getTitle() + "\n";
                result += "Body: " + postModelList.getText();

                tvResult.setText(result);

            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable throwable) {
//                On failure e.g. HTTP error code 400, return null - nothing will be displayed in the recyclerview.
                tvResult.setText(throwable.getMessage());
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.dismiss();
            }
        });
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
                Intent intent = new Intent(PostsActivity.this, LearnMoreActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(PostsActivity.this, "Sorry, nothing Selected.", Toast.LENGTH_SHORT).show();
        }
        drawer_layout.closeDrawer(GravityCompat.START);
        return false;
    }
}