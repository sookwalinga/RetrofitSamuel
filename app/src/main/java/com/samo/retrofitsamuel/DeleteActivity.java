package com.samo.retrofitsamuel;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.samo.retrofitsamuel.network.APIClient;
import com.samo.retrofitsamuel.network.Retrofitinstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteActivity extends AppCompatActivity {
    private TextView textView;
    private APIClient apiClient;
    private Retrofitinstance retrofitinstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        textView = findViewById(R.id.textResult);
        apiClient = Retrofitinstance.getRetroClient().create(APIClient.class);

//        AlertDialog.Builder builder = new AlertDialog.Builder(DeleteActivity.this);
//        builder.setTitle("Delete Data?");
//        builder.setMessage("Are you sure you want to delete this data?");
////        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
                Call<Void> call = apiClient.deletePost(5);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        textView.setText("Code: " + response.code());
                        Toast.makeText(DeleteActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        textView.setText(t.getMessage());
                    }
                });
//                finish();
//            }
//        });

    }
}