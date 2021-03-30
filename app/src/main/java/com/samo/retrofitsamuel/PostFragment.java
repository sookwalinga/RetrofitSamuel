package com.samo.retrofitsamuel;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.samo.retrofitsamuel.model.PostModel;
import com.samo.retrofitsamuel.network.APIClient;
import com.samo.retrofitsamuel.network.Retrofitinstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostFragment extends Fragment {
    //Declaring classes and widgets.
    private PostModel postModelList;
    FloatingActionButton btnPost;
    TextInputLayout tilUserId, tilTitle, tilBody;
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post, container, false);

        //        Initializing variables.
        btnPost = view.findViewById(R.id.post_btn);
        tilUserId = view.findViewById(R.id.userIdPost);
        tilTitle = view.findViewById(R.id.titlePost);
        tilBody = view.findViewById(R.id.bodyPost);
        imageView = view.findViewById(R.id.retrofitImage);


        //        Return to the main fragment on image click.
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = new MainFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

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
                    Toast.makeText(getContext(),
                            "Please fill in the data to be posted. The UserId is required.", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    private void GetPostResult(int userIdEntered, String titleEntered, String bodyEntered) {
//Alert Dialog to show popup of results.
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
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

}