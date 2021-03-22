package com.samo.retrofitsamuel.viewmodel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.samo.retrofitsamuel.model.PostModel;
import com.samo.retrofitsamuel.network.APIClient;
import com.samo.retrofitsamuel.network.Retrofitinstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Implementing the ViewModel
public class PostListViewModel extends ViewModel {

//    Observer to watch calling of data and updating the recyclerview.
    private MutableLiveData<List<PostModel>> postList;
//    Defining a constructor for this class.
    public PostListViewModel(){
//        Initializing the Observer.
        postList = new MutableLiveData<>();

    }

//    Function to return the live data.
    public MutableLiveData<List<PostModel>> getPostListObserver(){
        return  postList;
    }

//    Instatiating Retrofit in order to make the API Call.
    public void makeApiCall(){
        APIClient apiClient = Retrofitinstance.getRetroClient().create(APIClient.class);
        Call<List<PostModel>> call = apiClient.getPostList();

        //        Executing the request in the background (asynchronously) using the enqueue retrofit method.
        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
//                Instatiating the postList on success - on HTTP code 200 - 300.
                postList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable throwable) {
//                On failure e.g. HTTP error code 400, return null - nothing will be displayed in the recyclerview.
                postList.postValue(null);
            }
        });
    }
}