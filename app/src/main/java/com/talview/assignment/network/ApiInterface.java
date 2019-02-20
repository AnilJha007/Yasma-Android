package com.talview.assignment.network;

import com.talview.assignment.database.entity.PostEntity;
import com.talview.assignment.database.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/posts")
    Observable<List<PostEntity>> getPosts();

    @GET("/users")
    Observable<List<UserEntity>> getUsers();
}
