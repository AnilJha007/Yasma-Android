package com.talview.assignment.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.talview.assignment.database.entity.AlbumEntity;
import com.talview.assignment.database.entity.AlbumUser;

import java.util.List;

@Dao
public interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAlbums(List<AlbumEntity> posts);

    @Query("select albums.id, albums.user_id, albums.title, users.name from albums, users where albums.user_id = users.id")
    LiveData<List<AlbumUser>> getAllAlbumAndUser();
}
