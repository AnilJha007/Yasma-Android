package com.talview.assignment.network;

import com.talview.assignment.database.entity.AlbumEntity;
import com.talview.assignment.database.entity.AlbumsDetailsEntity;
import com.talview.assignment.database.entity.CommentEntity;
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

    @GET("/albums")
    Observable<List<AlbumEntity>> getAlbums();

    @GET("/comments")
    Observable<List<CommentEntity>> getComments();

    @GET("/photos")
    Observable<List<AlbumsDetailsEntity>> getAlbumsDetails();
}
