package com.samo.retrofitsamuel.model;

import com.google.gson.annotations.SerializedName;

public class PostModel {

    private int userId;
    private int id;
    private String title;
    // Anotating variable name with a serialized name since the JSON key and variable name differ.
    @SerializedName("body")
    private String text;

//    Constructor for the POST Request.
// Id is generated automatically by the REST API.
    public PostModel(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    //    Constructor for the GET Requests.
    public PostModel(int userId, int id, String title, String text) {
//Setting variables in the constructor.
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.text = text;
    }

    //    Getters and Setters.
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
