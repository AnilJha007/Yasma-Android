package com.talview.assignment.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.talview.assignment.database.entity.CommentEntity;
import com.talview.assignment.database.entity.UserEntity;

import java.util.List;

@Dao
public interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertComments(List<CommentEntity> comments);

    @Query("select * from comments where post_id = :postId")
    LiveData<List<CommentEntity>> getComments(int postId);

    @Query("select count(*) from comments")
    int getCommentsCount();

}
