package com.samo.retrofitsamuel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.samo.retrofitsamuel.adapter.UpdateAdapter;
import com.samo.retrofitsamuel.model.PostModel;
import com.samo.retrofitsamuel.network.APIClient;
import com.samo.retrofitsamuel.network.Retrofitinstance;
import com.samo.retrofitsamuel.viewmodel.UpdateViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity {
    //Declaring classes and widgets.
    private ArrayList<PostModel> postModelList;
    private UpdateAdapter adapter;
    private UpdateViewModel viewModel;


    private TextInputLayout tiId;
    private TextView textView;
    private Toolbar tiSend;
    private APIClient apiClient;
    private Retrofitinstance retrofitinstance;
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        tiId = findViewById(R.id.idQuery);
        tiSend = findViewById(R.id.sendEntry);
        textView = findViewById(R.id.textResult);
        recyclerView = findViewById(R.id.recview);
        apiClient = Retrofitinstance.getRetroClient().create(APIClient.class);

        //        Initializing variables.
        recyclerView = findViewById(R.id.recview);
        textView = findViewById(R.id.noDataView);
        //        Set the LinearLayoutManager to the recyclerview. I am using the GridLayoutManager in this case.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
//        Instantiating the Adapter class.
        adapter = new UpdateAdapter(this, postModelList);
        recyclerView.setAdapter(adapter);

        tiSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer idEntered = Integer.valueOf(tiId.getEditText().getText().toString());
                if (tiId != null) {
                    Call<PostModel> call = apiClient.getEdit(idEntered);
                    call.enqueue(new Callback<PostModel>() {
                        @Override
                        public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                            if (!response.isSuccessful()) {
                                recyclerView.setAdapter(adapter);
                                textView.setText("Code: " + response.code());
                                return;
                            }

                            PostModel postModel = response.body();

                            String data = "";
                            data += "Response Code: " + response.code() + "\n";
                            data += "Id: " + postModel.getId() + "\n";
                            data += "User Id: " + postModel.getTitle() + "\n";
                            data += "Title: " + postModel.getTitle() + "\n";
                            data += "Body: " + postModel.getText() + "\n";

                            textView.setText(data);
                        }

                        @Override
                        public void onFailure(Call<PostModel> call, Throwable t) {
                            textView.setText(t.getMessage());
                        }
                    });

                } else {
//                    Toast.makeText(UpdateActivity.this, "Please enter id.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}