package com.samo.retrofitsamuel.viewmodel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.samo.retrofitsamuel.model.CommentModel;
import com.samo.retrofitsamuel.model.CommentModel;
import com.samo.retrofitsamuel.network.APIClient;
import com.samo.retrofitsamuel.network.Retrofitinstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//Implementing the ViewModel
public class QueryListViewModel extends ViewModel {

//    Observer to watch calling of data and updating the recyclerview.
    private MutableLiveData<List<CommentModel>> commentList;
//    Defining a constructor for this class.
    public QueryListViewModel(){
//        Initializing the Observer.
        commentList = new MutableLiveData<>();

    }

//    Function to return the live data.
    public MutableLiveData<List<CommentModel>> getCommentListObserver(){
        return  commentList;
    }

//    Instatiating Retrofit in order to make the API Call.
    public void makeApiCall(){
        APIClient apiClient = Retrofitinstance.getRetroClient().create(APIClient.class);
        Call<List<CommentModel>> call = apiClient.getComments();

//        For the id passed in dynamically.
//        Call<List<CommentModel>> call = apiClient.getComments(1);

        //        Executing the request in the background (asynchronously) using the enqueue retrofit method.
        call.enqueue(new Callback<List<CommentModel>>() {
            @Override
            public void onResponse(Call<List<CommentModel>> call, Response<List<CommentModel>> response) {
//                Instatiating the postList on success - on HTTP code 200 - 300.
                commentList.postValue(response.body());
                return;
            }

            @Override
            public void onFailure(Call<List<CommentModel>> call, Throwable throwable) {
//                On failure e.g. HTTP error code 400, return null - nothing will be displayed in the recyclerview.
                commentList.postValue(null);
            }
        });
    }
}