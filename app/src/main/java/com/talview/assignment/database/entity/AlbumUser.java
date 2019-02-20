package com.talview.assignment.database.entity;

public class AlbumUser {

    private int id;
    private int user_id;
    private String title;
    private String name;

    public AlbumUser(int id, int user_id, String title, String name) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
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

    public String getName() {
        return name;
    }
}
