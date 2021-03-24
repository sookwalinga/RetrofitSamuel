package com.samo.retrofitsamuel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.nfc.TagLostException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import com.samo.retrofitsamuel.model.PostModel;
import com.samo.retrofitsamuel.network.APIClient;
import com.samo.retrofitsamuel.network.Retrofitinstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdateActivity extends AppCompatActivity {
    private TextView textView;
    private APIClient apiClient;
    private Retrofitinstance retrofitinstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        textView = findViewById(R.id.textResult);
        apiClient = Retrofitinstance.getRetroClient().create(APIClient.class);

        PostModel post = new PostModel(12, null, "New Text");

        Call<PostModel> call = apiClient.putPost(5, post);

        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                if (!response.isSuccessful()) {
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
                Toast.makeText(UpdateActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }
}