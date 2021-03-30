package com.samo.retrofitsamuel;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.samo.retrofitsamuel.model.PostModel;
import com.samo.retrofitsamuel.network.APIClient;
import com.samo.retrofitsamuel.network.Retrofitinstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    //Declaring classes and widgets.
    private PostModel postModelList;

    private DrawerLayout drawer_layout;
    FloatingActionButton btnUpdate, btnDelete;
    TextInputLayout tilUserId, tilTitle, tilBody;
    ImageView imageView;
    Spinner spinner;
    public String selectedItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update, container, false);

        //        Initializing variables.
        btnUpdate = view.findViewById(R.id.update_btn);
        btnDelete = view.findViewById(R.id.delete_btn);
        tilUserId = view.findViewById(R.id.userIdPost);
        tilTitle = view.findViewById(R.id.titlePost);
        tilBody = view.findViewById(R.id.bodyPost);
        imageView = view.findViewById(R.id.retrofitImage);
        spinner = (Spinner) view.findViewById(R.id.idList);
        spinner.setOnItemSelectedListener(this);

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

        // Creating an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.id_values, android.R.layout.simple_spinner_item);
        // Specifying the layout to use when the list of choices appears.
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Applying the adapter to the spinner.
        spinner.setAdapter(arrayAdapter);


        //Update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUserId = tilUserId.getEditText().getText().toString();
                String titleEntered = tilTitle.getEditText().getText().toString();
                String bodyEntered = tilBody.getEditText().getText().toString();
                if (strUserId.length() != 0) {
                    Integer userIdEntered = Integer.valueOf(strUserId);
                    GetUpdateResult(userIdEntered, titleEntered, bodyEntered);
//                    Clearing inputs after post.
                    tilUserId.getEditText().setText("");
                    tilTitle.getEditText().setText("");
                    tilBody.getEditText().setText("");
                } else {
                    Toast.makeText(getContext(),
                            "Please enter updates. The UserId is required.", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteAlertDialog();
            }
        });
        return view;
    }

    //Selecting spinner items.
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedItem = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void GetUpdateResult(int userIdEntered, String titleEntered, String bodyEntered) {
//Alert Dialog to show popup of results.
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
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
        Integer selectedId = Integer.parseInt(selectedItem);
        Call<PostModel> call = apiClient.patchPost(selectedId, postModel);

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

    //    Delete alertdialog.
    private void DeleteAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete item " + selectedItem + "?");
        builder.setMessage("Are you sure you want to delete item with id " + selectedItem + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                APIClient apiClient = Retrofitinstance.getRetroClient().create(APIClient.class);

                Call<Void> call = apiClient.deletePost(5);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(getContext(), "Successfully deleted item " + selectedItem + "\n" + " Response code: " + response.code(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();

    }
}