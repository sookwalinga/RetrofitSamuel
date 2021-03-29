package com.samo.retrofitsamuel.network;

import com.samo.retrofitsamuel.model.CommentModel;
import com.samo.retrofitsamuel.model.PostModel;

import java.util.List;

import com.samo.retrofitsamuel.model.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIClient {

    //    Implenting the GET method so as to return the list of posts from the API.
//Posts are generated from the JSONPlaceholder API where the BASE_URL is declared in the Retrofitinstance class.
    @GET("posts")
//    Call to encapsulate the request and response made with the GET method.
    Call<List<PostModel>> getPostList();

//    @GET("posts/{id}")
//    Call<List<PostModel>> getItem(@Path("id") int postId);
//
    @GET("posts/1")
    Call<List<PostModel>> getItem();


    //    //GET request for the comments
//    @GET("posts/1/comments")
//    Call<List<CommentModel>> getComments();
//
//    Passing in ID dynamically.
    @GET("posts/{id}/comments")
    Call<List<CommentModel>> getComments(@Path("id") int postId);

    //    GET request with querry parameter.
//    @GET("posts")
//    Call<List<PostModel>> getItem(@Query("postId") int postId);


    /*
     * POST request.
     * We provide the java object that is automatically deserialized before being sent to the server.
     * */

    @POST("posts")
    Call<PostModel> createPost(@Body PostModel post);

    //    Getting posts to be updated.
    @GET("posts/{id}")
    Call<PostModel> getEdit(@Path("id") int postId);

    //    Updating posts. Put replaces all the body updated.
    @PUT("posts/{id}")
    Call<PostModel> putPost(@Path("id") int id, @Body PostModel post);

    //    Patch updates only specified fields.
    @PATCH("posts/{id}")
    Call<PostModel> patchPost(@Path("id") int id, @Body PostModel post);

    //    Delete. Set the body to void.
    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);
}