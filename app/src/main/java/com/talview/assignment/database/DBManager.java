package com.talview.assignment.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.talview.assignment.database.dao.AlbumDao;
import com.talview.assignment.database.dao.AlbumDetailsDao;
import com.talview.assignment.database.dao.CommentDao;
import com.talview.assignment.database.dao.PostDao;
import com.talview.assignment.database.dao.UserDao;
import com.talview.assignment.database.entity.AlbumEntity;
import com.talview.assignment.database.entity.AlbumsDetailsEntity;
import com.talview.assignment.database.entity.CommentEntity;
import com.talview.assignment.database.entity.PostEntity;
import com.talview.assignment.database.entity.UserEntity;

@Database(entities = {PostEntity.class, UserEntity.class, AlbumEntity.class, CommentEntity.class, AlbumsDetailsEntity.class}, version = 1, exportSchema = false)
public abstract class DBManager extends RoomDatabase {

    public abstract PostDao getPostDao();

    public abstract UserDao getUserDao();

    public abstract AlbumDao getAlbumDao();

    public abstract CommentDao getCommentDao();

    public abstract AlbumDetailsDao getAlbumDetailsDao();


}
