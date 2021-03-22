package com.samo.retrofitsamuel.model;

public class PostModel {

    userId": 1,
            "id":1,
            "title":"sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
            "body

    private int userId;
    private int id;
    private String title;

    @SerializedName("body")
    private String text;

    //    Constructor for this Class.
public PostModel(int userId, int id, String title, String text){
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
