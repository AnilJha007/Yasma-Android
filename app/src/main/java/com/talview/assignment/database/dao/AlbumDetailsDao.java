package com.talview.assignment.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.talview.assignment.database.entity.AlbumsDetailsEntity;
import com.talview.assignment.database.entity.CommentEntity;

import java.util.List;

@Dao
public interface AlbumDetailsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAlbumsDetails(List<AlbumsDetailsEntity> comments);

    @Query("select * from albums_details where album_id = :albumId")
    LiveData<List<AlbumsDetailsEntity>> getAlbumsDetails(int albumId);

    @Query("select count(*) from albums_details")
    int getAlbumDetailsCount();

}
