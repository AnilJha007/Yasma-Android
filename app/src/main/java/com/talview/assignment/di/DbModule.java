package com.talview.assignment.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.talview.assignment.database.DBManager;
import com.talview.assignment.utils.ConstantUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    @Provides
    @Singleton
    DBManager provideDatabase(Application context) {
        return Room.databaseBuilder(context, DBManager.class, ConstantUtil.DB_NAME).
                fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }
}
