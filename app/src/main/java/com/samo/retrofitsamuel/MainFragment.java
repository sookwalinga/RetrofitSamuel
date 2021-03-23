package com.samo.retrofitsamuel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.*;

/**
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    //Declaring classes and widgets.
//    private List<PostModel> postModelList;
//    private PostListAdapter adapter;
//    private PostListViewModel viewModel;
//
//    public RecyclerView recyclerView;
//    public TextView textView;
//
    Button btnGetAll, btnQuery, btnPost, btnPut, btnDelete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
//        Initializing variables.
//        recyclerView = view.findViewById(R.id.recview);
//        textView = view.findViewById(R.id.noDataView);
        btnGetAll = view.findViewById(R.id.getAllPosts);
        btnQuery = view.findViewById(R.id.getByQuery);
        btnPost = view.findViewById(R.id.post);
        btnDelete = view.findViewById(R.id.post);

//        Onclick listeners.
        btnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new GetAllPostsFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_main, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                //        Set the LinearLayoutManager to the recyclerview. I am using the GridLayoutManager in this case.
//                LinearLayoutManager linearLayoutManager = new GridLayoutManager(MainActivity.this, 1);
//                recyclerView.setLayoutManager(linearLayoutManager);
////        Instantiating the Adapter class.
//                adapter = new PostListAdapter(MainActivity.this, postModelList);
//                recyclerView.setAdapter(adapter);
//
//                viewModel = ViewModelProviders.of(MainActivity.this).get(PostListViewModel.class);
////        listening to the live data.
//                viewModel.getPostListObserver().observe(MainActivity.this, new Observer<List<PostModel>>() {
//                    //            Onsuccess, display the data.
//                    @Override
//                    public void onChanged(List<PostModel> postModels) {
//                        if (postModels != null) {
//                            postModelList = postModels;
//                            adapter.setPostList(postModels);
////                    Set visibility for the textView for no data to gone when data is fetched successfully.
//                            textView.setVisibility(View.GONE);
//
//                        } else {
////                    On failure, display - SORRY, FAILED TO FETCH DATA.
//                            textView.setVisibility(View.VISIBLE);
//                        }
//                    }
//                });
//                viewModel.makeApiCall();
            }
        });

        return view;
    }
}