package com.samo.retrofitsamuel.model;

import com.google.gson.annotations.SerializedName;

public class CommentModel {

    private int postId;
    private int id;
    private String name;
    private String email;
    // Anotating variable name with a serialized name since the JSON key and variable name differ.
    @SerializedName("body")
    private String text;

    //    Constructor for this Class.
    public CommentModel(int postId, int id, String name, String email, String text) {
//Setting variables in the constructor.
        this.postId = postId;
        this.id = id;
        this.name = name;
        this.email = email;
        this.text = text;
    }

    //    Getters and Setters.

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
