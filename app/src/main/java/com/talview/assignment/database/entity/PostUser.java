package com.talview.assignment.database.entity;

public class PostUser {

    private int id;
    private int user_id;
    private String title;
    private String body;
    private String name;

    public PostUser(int id, int user_id, String title, String body, String name) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.body = body;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getName() {
        return name;
    }
}