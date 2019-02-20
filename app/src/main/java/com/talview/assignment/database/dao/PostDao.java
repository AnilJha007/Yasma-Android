package com.talview.assignment.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.talview.assignment.database.entity.PostEntity;
import com.talview.assignment.database.entity.PostUser;

import java.util.List;

@Dao
public interface PostDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPosts(List<PostEntity> posts);

    @Query("select posts.id, posts.user_id, posts.title, posts.body, users.name from posts, users where posts.user_id = users.id")
    LiveData<List<PostUser>> getAllPostAndUser();

    @Query("select count(*) from posts, users where posts.user_id = users.id")
    int getRowsCount();
}
