package com.samo.retrofitsamuel;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;

/**
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    //Declaring classes and widgets.
    Button btnGetAll, btnQuery, btnPost, btnPut;
    TextInputLayout textInputLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
//        Initializing variables.
        btnGetAll = view.findViewById(R.id.getAllPosts);
        btnQuery = view.findViewById(R.id.getByQuery);
        btnPost = view.findViewById(R.id.post);
        btnPut = view.findViewById(R.id.edit);

//        Onclick listeners.
//       Intent to the GET posts.
        btnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GetPostsActivity.class);
                startActivity(intent);
            }
        });

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), QueryActivity.class);
                startActivity(intent);
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), GetPostsActivity.class);
                startActivity(intent);
            }
        });

        btnPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), UpdateActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}