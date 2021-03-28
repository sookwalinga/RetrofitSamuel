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
public class UpdateViewModel extends ViewModel {

//    Observer to watch calling of data and updating the recyclerview.
    protected MutableLiveData<List<PostModel>> postList;
//    Defining a constructor for this class.
    public UpdateViewModel(){
//        Initializing the Observer.
        postList = new MutableLiveData<>();

    }

//    Function to return the live data.
    public MutableLiveData<List<PostModel>> getPostListObserver(){
        return  postList;
    }

//   API Call to retrieve data.
    public void makeApiCall(int idEntry){
        APIClient apiClient = Retrofitinstance.getRetroClient().create(APIClient.class);
        Call<PostModel> call = apiClient.getEdit(idEntry);

        //        Executing the request in the background (asynchronously) using the enqueue retrofit method.
        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
//                Instatiating the postList on success - on HTTP code 200 - 300.
//                postList.postValue(response.body());
                return;
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable throwable) {
//                On failure e.g. HTTP error code 400, return null - nothing will be displayed in the recyclerview.
//                postList.postValue(null);
            }
        });
    }


    //   Posting to the server.
    public void createPost(){
        APIClient apiClient = Retrofitinstance.getRetroClient().create(APIClient.class);

        PostModel postModel = new PostModel(23, "New Title", "New Text");
        Call<PostModel> call = apiClient.createPost(postModel);

        //        Executing the request in the background (asynchronously) using the enqueue retrofit method.
        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
//                Instatiating the postList on success - on HTTP code 200 - 300.
                response.body();
                return;
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable throwable) {
//                On failure e.g. HTTP error code 400, return null - nothing will be displayed in the recyclerview.
                postList.postValue(null);
            }
        });
    }

}