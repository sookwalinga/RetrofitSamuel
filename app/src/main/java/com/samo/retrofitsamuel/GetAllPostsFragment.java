package com.samo.retrofitsamuel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samo.retrofitsamuel.adapter.PostListAdapter;
import com.samo.retrofitsamuel.model.PostModel;
import com.samo.retrofitsamuel.viewmodel.PostListViewModel;

import java.util.List;

/**
 * create an instance of this fragment.
 */
public class GetAllPostsFragment extends Fragment {
    //Declaring classes and widgets.
    private List<PostModel> postModelList;
    private PostListAdapter adapter;
    private PostListViewModel viewModel;

    public RecyclerView recyclerView;
    public TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//   Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_get_all_posts, container, false);

//        Initializing variables.
        recyclerView = (RecyclerView) view.findViewById(R.id.recview);
        textView = (TextView) view.findViewById(R.id.noDataView);
        //        Set the LinearLayoutManager to the recyclerview. I am using the GridLayoutManager in this case.
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(linearLayoutManager);
//        Instantiating the Adapter class.
        adapter = new PostListAdapter(getContext(), postModelList);
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(getActivity()).get(PostListViewModel.class);
//        listening to the live data.
        viewModel.getPostListObserver().observe(getActivity(), new Observer<List<PostModel>>() {
            //            Onsuccess, display the data.
            @Override
            public void onChanged(List<PostModel> postModels) {
                if (postModels != null) {
                    postModelList = postModels;
                    adapter.setPostList(postModels);
//                    Set visibility for the textView for no data to gone when data is fetched successfully.
                    textView.setVisibility(View.GONE);

                } else {
//                    On failure, display - SORRY, FAILED TO FETCH DATA.
                    textView.setVisibility(View.VISIBLE);
                }
            }
        });
        viewModel.makeApiCall();
        return view;
    }
}