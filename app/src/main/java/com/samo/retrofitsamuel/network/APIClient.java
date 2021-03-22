package com.samo.retrofitsamuel.network;

import com.samo.retrofitsamuel.model.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIClient{

//    Implenting the GET method so as to return the list of posts from the API.
//Posts are generated from the JSONPlaceholder API where the BASE_URL is declared in the Retrofitinstance class.
    @GET("posts")
    Call<List<PostModel>> getPostList();
}
