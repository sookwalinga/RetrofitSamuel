package com.samo.retrofitsamuel.model;

public class PostModel {

    private int userId;
    private int id;
    private String title;
    private String body;

    //    Constructor for this Class.
public PostModel(int userId, int id, String title, String body){
//Setting variables in the constructor.
    this.userId = userId;
    this.id = id;
    this.title = title;
    this.body = body;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
